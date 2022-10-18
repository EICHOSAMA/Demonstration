package per.eicho.demo.jdk.java.io;

/**
 * <p>InputStream抽象类的使用例</p>
 * <pre>
 *  InputStream，输入流，如其名就是程序从程序外部资源流式（Stream）接收数据时使用的数据流。
 * 
 *  {@link java.io.InputStream}抽象类是字节输入流，是所有字节输入流的父类。（@see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/package-tree.html">Hierarchy For Package java.io</a>）
 *  如比较常见的：
 *    - {@link java.io.FileInputStream}：能指定路径从文件系统的某个文件读取数据。
 *    - {@link java.io.FilterInputStream}：input stream的装饰者模式的运用。能为字节输入流添加一些额外的功能。
 *         如果看源码可以知道FilterInputStream类本身转发所有请求到内部持有的输入流，使用这个类本身没有意义。
 *         但这个类是如BufferedInputStream等功能增强装饰类的父类，如果看到某个输入流的父类是FilterInputStream，
 *         那么这个类本身就是在所持有的输入流之上新增了一些额外功能的增强类。
 *         - 装饰者模式可以看笔者写的 {@link per.eicho.demo.designpattern.structural}下的DecoratorPatternSample类。
 *         - {@link java.io.BufferedInputStream}：为装饰的输入流添加缓冲功能。与FileInputStream经常一起出现。
 * </pre>
 * 
 * <pre>
 *  简单列举输入流比较重要的一些方法：
 *    - {@link java.io.InputStream#read()}：读取流中下一个字节[0, 255]，会阻塞线程直到取到数据返回、检测到流尾返回-1或抛出异常。
 *    - {@link java.io.InputStream#read(byte[], int, int)}：从给定字节数组第二个参数offset位置起开始存放读到的数据，最多读第三个参数len字节。
 *    - {@link java.io.InputStream#skip(long)}：跳过参数给定的字节数，负数返回0，余量不足返回实际跳过的字节数。
 * </pre>
 * 
 * <pre>
 *   InputStream是老IO包下四大基础类之一，有必要好好掌握。
 * </pre>
 */
final class InputStreamSample {

    /* 具体实现子类的使用例看具体实现子类的样例类 */
    
    private InputStreamSample() {}
}
