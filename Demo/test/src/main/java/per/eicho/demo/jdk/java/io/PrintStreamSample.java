package per.eicho.demo.jdk.java.io;

/**
 * <p>PrintStream类的使用例</p>
 * <pre>
 *  PrintStream，打印流。属于100% Java开发者都用过的一个类了。
 *   <coee>
 *     System.out.println("Hello world");
 *   </code>
 * 
 *  System.out给咱的就是一个{@link java.io.PrintStream}对象了。
 * </pre>
 * 
 * <pre>
 *  PrintStream本身没有什么好讲的，
 *  内部基于一个BufferedWriter和其装饰的OutputStreamWriter，两个字符流完成的。
 *  而System.out就比较有趣了，我们都知道命令行启动Java程序输出就回到命令行界面里显示，
 *  
 *    PG ----> System.out("Message") -----> CMD
 *  
 *  那么在Linux里有个东西叫Shell pipeline(Shell pipe、管道)
 *  这个管道呢可以把上一个程序的输出拼接到下一个程序的输入里。
 *  这就使得Linux下处理东西变得更容易，如果你需要用Java开发Linux小工具支持管道的话，
 *  可以试试处理{@link java.lang.System#in}里的数据。
 * </pre>
 */
final class PrintStreamSample {

    public static void main(String[] args) {
        System.out.println("PrintStream是堪称Java界使用率最高的类之一！");
    }

    private PrintStreamSample() {}
}
