package per.eicho.demo.jdk.java.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * <p>ByteBuffer抽象类的样例类</p>
 * <pre>
 *  {@link java.nio.ByteBuffer}抽象类是{@link java.nio.Buffer}抽象类的7个原生类型实现之一（除开布尔类型没有）。
 *  通过演示ByteBuffer就能大致掌握其他几种原生类型buffer类的使用。
 * </pre>
 * 
 * <h2>ByteBuffer实例的创建</h2>
 * <pre>
 *  ByteBuffer是抽象类，其创建不能通过new来，而是通过工厂方法实现。
 *    - {@link ByteBuffer#allocate(int)}
 *    - {@link ByteBuffer#allocateDirect(int)}
 *    - {@link ByteBuffer#wrap(byte[])}: 等效调用wrap(byte[], 0, byte[].length);
 *    - {@link ByteBuffer#wrap(byte[], int, int)}
 * </pre>
 * 
 * <h2>Buffer的四个基本index属性</h2>
 * <pre>
 *   对Buffer的操作抽象为了变更以下4个基本index属性的变更。4个属性分别是：
 *    - mark     : 通过{@link Buffer#mark()}能设置mark到当前position的位置
 *    - position : Buffer当前使用到的位置，这个很简单。
 *    - limit    : “an index to stop read or write”，类似读写操作的屏障或者终点。
 *    - capacity : 容器类都有的容量属性。是缓冲区最大的容量。
 *   Buffer抽象类提供的操作会保持这几个index的基本关系是
 *   mark <= position <= limit <= capacity
 * </pre>
 */
final class ByteBufferSample {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 << 2); // 8kb buffer
        System.out.println("初始状态：" + byteBuffer);
        System.out.println("mark后：" + byteBuffer.mark());
        System.out.println("加入1直接后：" + byteBuffer.put((byte) 0b11111111));
        System.out.println("重置位置后：" + byteBuffer.reset());
        System.out.println("模拟写入8字节数据：" + put8BytesData(byteBuffer));
        System.out.println("模拟取数据：" + getData(byteBuffer));
    }

    private static ByteBuffer put8BytesData(ByteBuffer byteBuffer) {
        for (int i = 0; i < 8; i++) {
            byteBuffer.put((byte) ('a' + i));
        }
        byteBuffer.limit(8);
        return byteBuffer;
    }

    private static ByteBuffer getData(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        byte[] data = new byte[byteBuffer.limit() - byteBuffer.position()];
        byteBuffer.get(data);
        System.out.println(new String(data));
        return byteBuffer;
    }

    private ByteBufferSample() {}
}
