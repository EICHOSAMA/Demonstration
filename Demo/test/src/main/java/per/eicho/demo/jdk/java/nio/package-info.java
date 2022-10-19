/**
 * <p>{@link java.nio}包相关的demo代码将被包含在此package里。</p>
 * <pre>
 *  既然提到NIO就不得不提两个初学者非常非常蛋疼的NIO vs NIO2，这俩的区别是啥？
 *  简单的来讲就是：
 *    - {@link java.nio}被认为是NIO，在Java1.4起就被引入了。
 *    - {@link java.nio.file}被认为是NIO2，在Java1.7被引入。是对NIO包整体功能性的一种增强（enhancement）
 *       - The java.nio.file package and its related package, java.nio.file.attribute, 
 *         provide comprehensive support for file I/O and for accessing the file system. 
 *         A zip file system provider is also available in JDK 7. etc...
 *       - @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/io/enhancements.html#jdk7">Enhancements in JDK 7 Release</a>
 * </pre>
 * 
 * <pre>
 *  NIO有几个关键的概念，摸清楚这些关键概念会加速你理解NIO的特性
 *    - {@link java.nio.Buffer}抽象类：
 *        Buffer本身可以理解为原生数据类型的容器，几个子类有如ByteBuffer、IntBuffer等。
 *        不难看出Buffer类本身基于原生数据类型的数组（int[]、byte[]）等，
 *        这个Buffer抽象类提供了对于操作缓冲区（int[]等）几个状态值（limit、mark、position）的一组公共的方法。
 *        如：
 *        - {@link java.nio.Buffer#mark()} 标记当前光标位置（覆盖式）
 *        - {@link java.nio.Buffer#reset()} 移动光标位置到之前标记过的位置，使用前必须至少使用过一次mark()否则抛异常
 *        - {@link java.nio.Buffer#clear()} 恢复出厂设置：光标位置、limit、mark都恢复为出厂设置。
 *        - ... etc
 *    - {@link java.nio.channels.Channel}接口：
 *        管道，其是我们PG与资源间的链接的一种抽象，这里的资源可以是硬件设备、文件、网络套接字（Network Socket）等等..
 *        其中比较重要的子接口和实现类我们简单列举一下。
 *        - {@link java.nio.channels.NetworkChannel}接口：网络编程相关
 *          - {@link java.nio.channels.ServerSocketChannel}类 和 {@link java.nio.channels.SocketChannel}类
 *          - {@link java.nio.channels.AsynchronousServerSocketChannel}类 和 {@link java.nio.channels.AsynchronousSocketChannel}类
 *        - {@link java.nio.channels.FileChannel}类：Memory-Mapped file，能达到和RandomAccessFile一样的效果且高效。
 *    - {@link java.nio.channels.Selector}抽象类：
 *        选择器，是一组{@link java.nio.channels.SelectableChannel}的多路复用器（multiplexor）
 *        像{@link java.nio.channels.ServerSocketChannel}就是SelectableChannel的子类之一。
 *        通过多路复用可以让单个线程处理来自多个客户端链接的数据。
 * </pre>
 * 
 * <pre>
 *  关于NIO确实是爱恨交织，JDK在NIO包下为开发者提供了众多开发工具，不过其高昂的学习成本、周期
 *  如果需要开发网络通信相关的程序、框架，建议使用<a href="https://github.com/netty/netty/">Netty</a>。
 * </pre>
 */
package per.eicho.demo.jdk.java.nio;