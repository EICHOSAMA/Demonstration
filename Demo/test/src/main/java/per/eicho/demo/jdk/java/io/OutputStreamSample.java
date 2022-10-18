package per.eicho.demo.jdk.java.io;

/**
 * <p>OutputStream抽象类的使用例</p>
 * <pre>
 *  OUtputStream，输出流，如其名就是程序向程序外部资源流式（Stream）输出数据时使用的数据流。
 * 
 *  {@link java.io.OutputStream}抽象类是字节输出流，是所有字节输出流的父类。（@see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/package-tree.html">Hierarchy For Package java.io</a>）
 *  常见的有：
 *    - {@link java.io.FileOutputStream}：能指定路径向文件系统的某个文件写入数据。
 *    - {@link java.io.FilterOutputStream}：与InputStream类似的，这个类为OutputStream的装饰基底类。
 *      - 通常其子类{@link java.io.BufferedOutputStream}会与FileOutputStream配合使用。
 * </pre>
 * 
 * <pre>
 *  因为其过于和InputStream类似就不多赘述了，仅讲其相比InputStream多实现的一个接口{@link java.io.Flushable}
 *  
 *  Flushable接口仅包含一个方法flush()啊，flush啊就是冲马桶那个冲。意味着如果输出流缓存（或积攒）了一些
 *  数据准备留着一起发送给下游目标资源时，输出流的客户（咱们调用方）可以调用flush方法使其把积攒的
 *  数据丢给下游。
 * </pre>
 * 
 * @see {@link InputStreamSample}
 */
final class OutputStreamSample {

    /* 具体实现子类的使用例看具体实现子类的样例类 */
    
    private OutputStreamSample() {}
}
