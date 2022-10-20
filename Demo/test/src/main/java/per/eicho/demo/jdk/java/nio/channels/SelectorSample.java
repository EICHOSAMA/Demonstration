package per.eicho.demo.jdk.java.nio.channels;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

final class SelectorSample {
    static volatile Selector selector;
    static volatile ExecutorService requestHandleExecutorService;

    /** TCP客户模拟器，
     *  用于启动单独一个线程模拟外部TCP客户端的一些行为 
     */
    static final class TCPClientSimulator {
        
        final int port;
        final AtomicInteger ctl = new AtomicInteger(STATUS_NEW);
        volatile ExecutorService executorService;
        private static int STATUS_NEW = 0;
        private static int STATUS_STARTING = 1;
        private static int STATUS_RUNNING = 2;
        private static int STATUS_SHUTDOWN = 3;

        TCPClientSimulator(int port) {
            this.port = port;
        }

        public void start() {
            if (ctl.get() != STATUS_NEW) throw new IllegalStateException("Already started or shutdown.");
            if(ctl.compareAndSet(STATUS_NEW, STATUS_STARTING)) {
                // win race
                executorService = Executors.newSingleThreadExecutor();
                executorService.execute(this::performSimulation);
                ctl.set(STATUS_RUNNING);
            } else {
                // lose race ... 
            }
        }

        private void performSimulation() {
            try (Socket socket = new Socket(InetAddress.getLocalHost(), port);
                 PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
                printWriter.print("测试");
                printWriter.print("aaaa");
                printWriter.flush();
                Thread.sleep(1_000); // 1s
                printWriter.print("测试1秒后发送数据");
                printWriter.print("aaaaa");
                printWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void shutdown() {
            if (ctl.get() != STATUS_RUNNING) throw new IllegalStateException("Error state");
            executorService.shutdown();
            try {
                executorService.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctl.compareAndSet(STATUS_RUNNING, STATUS_SHUTDOWN);
        }

        static TCPClientSimulator newInstance(int port) {
            return new TCPClientSimulator(port);
        }
    } 

    public static void main(String[] args) throws IOException, InterruptedException {
        requestHandleExecutorService = Executors.newFixedThreadPool(10);
        selector = newAReadyForUseSelector();
        // Selects and performs an action on the keys 
        // whose corresponding channels are ready for I/O operations.  
        final ExecutorService dataLoaderService = Executors.newSingleThreadExecutor();
        dataLoaderService.execute(SelectorSample::dataLoaderMain);

        Thread.sleep(1_000); // 1s后开启TCP Client
        final TCPClientSimulator simulator = TCPClientSimulator.newInstance(10086);
        simulator.start();

        dataLoaderService.shutdown();
        Thread.sleep(1_000);
        simulator.shutdown();
        Thread.sleep(5_000);
        dataLoaderService.shutdownNow();
        selector.close();
        dataLoaderService.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("dataLoaderService isShutdown:" + dataLoaderService.isShutdown());
    
        requestHandleExecutorService.shutdown();
        requestHandleExecutorService.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("requestHandleExecutorService isShutdown:" + requestHandleExecutorService.isShutdown());
    }

    private static void dataLoaderMain() {
        while (selector.isOpen()) {
            try {
                selector.select(SelectorSample::selectHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void selectHandler(SelectionKey selectionKey) {
        final  SelectableChannel channel = selectionKey.channel();
        if (selectionKey.isAcceptable() && channel instanceof ServerSocketChannel) {
            final int readyOps = selectionKey.readyOps();
            selectionKey.interestOpsAnd(~readyOps);
            final ServerSocketChannel ssc = (ServerSocketChannel)channel;
            try {
                final SocketChannel socketChannel = ssc.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
                System.out.println("新建了一个连接");
                selectionKey.interestOpsAnd(readyOps);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (selectionKey.isReadable() && channel instanceof SocketChannel) {
            final int readyOps = selectionKey.readyOps();
            selectionKey.interestOpsAnd(~readyOps);
            final SocketChannel sc = (SocketChannel)channel;
            // Delegate Request Handle task to Other thread. ()
            requestHandleExecutorService.execute(() -> {
                // New buffer instance for loading data from socket.
                final ByteBuffer byteBuffer = ByteBuffer.allocate(1024 << 2);
                final StringBuilder stringBuilder = new StringBuilder();
                try {
                    int readResult = 0;
                    while ((readResult = sc.read(byteBuffer)) != -1) {
                        if (readResult == 0) continue;
                        byteBuffer.flip();
                        System.out.println("读取到" + readResult + " bytes 数据");
                        byte[] bytes = new byte[readResult];
                        byteBuffer.get(bytes);
                        byteBuffer.clear();
                        final String message = new String(bytes);
                        stringBuilder.append(message);
                        System.out.println("message:" + message);
                    }
                    System.out.println("从连接接收到消息：[" + stringBuilder.toString() + "]");
                    selectionKey.interestOpsAnd(readyOps);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static Selector newAReadyForUseSelector() throws IOException, ClosedChannelException {
        // 通过SPI机制获取SelectorProvider,并用SelectorProvider打开一个Selector
        // Equal to Selector#open() method.
        final SelectorProvider selectorProvider = SelectorProvider.provider(); // SPI
        final Selector selector = selectorProvider.openSelector(); 
        
        // 用SelectorProvider打开并配置一个ServerSocketChannel
        final ServerSocketChannel serverSocketChannel = selectorProvider.openServerSocketChannel();
        serverSocketChannel.configureBlocking(false);
        final int ops = serverSocketChannel.validOps(); // == SelectionKey.OP_ACCEPT
        final int serverPort = 10086;
        final SocketAddress socketAddress = new InetSocketAddress(serverPort);
        serverSocketChannel.bind(socketAddress);

        // 注册 serverSocketChannel --> serverSocketChannel
        serverSocketChannel.register(selector, ops);
        return selector;
    }

    private SelectorSample() {}
}
