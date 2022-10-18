package per.eicho.demo.jdk.java.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * <p>RandomAccessFile类的使用例</p>
 * <pre>
 *  RandomAccessFile，随机访问文件。了解java collection框架的朋友应该能瞬间想
 *  起一个熟悉的接口{@link java.util.RandomAccess}，集合类实现RandomAccess表示
 *  支持快速随机访问，
 *    - {@link java.util.ArrayList}支持RandomAccess，访问任意元素是O(1)的。
 *    - {@link java.util.LinkedList}不支持RandomAccess，访问任意元素的复杂度从O(1)到O(n)不等。
 *  RandomAccessFile什么意思呢？文件在文件系统里本质就是大号的Byte数组。
 *  jdk提供的RandomAccessFile类让我们能够从文件的任意位置开始读写数据。
 * </pre>
 * 
 * <pre>
 *  RandomAccessFile应用场景，大文件上载下载都可以用到。
 *  大文件上载下载其实差不多是一个意思，上载端是分段上载、之于下载端就是分段下载。
 *  断点续传就是基于这种思想。
 *  举个例子就是许多年前的迅雷、快播，
 *    - 迅雷：当你开始下载一个文件时，文件系统上就会出现一个与目标文件一样大小的（PlaceHolder文件），
 *           每下载一部分数据就会填充进去。直到下载完成，修改后缀名前台提升用户下载完成一气呵成。
 *    - 快播：当你开始看一个在线视频是，文件系统也会出现一个临时地与目标文件一样大小的（PlaceHolder文件），
 *           你每看（或缓冲）一部分视频数据，这些数据就会被填充到这个文件里。
 *           你甚至可以用快播打开一个缓冲了一点的临时文件，拉动进度条到你曾经看过的部分，是能正常播放的。
 *           而你拖动进度条到你没缓冲（没下载完成）的部分时，这部分是不能被播放的。
 *   **需要注意：截止目前（2022/10/19）笔者已经许多年没用过这类软件了，当前版本特性不清楚。**
 * </pre>
 * 
 * @see {@link java.nio.channels.FileChannel} RandomAccessFile的NIO替代品（Memory-Mapped file）
 */
final class RandomAccessFileSample {

    public static void main(String[] args) throws IOException {
        final char placeHolder = '-';
        final File file = File.createTempFile("testFile", "RandomAccessFileSample");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            final int lenOfTestData = 16; // Unit: Character
            // 1. 写入占位用数据
            randomAccessFile.setLength(lenOfTestData * 2);
            for (int i = 0; i < lenOfTestData; i++) {
                randomAccessFile.writeChar(placeHolder);
            }
            
            // 2. 尝试Log出来看一下
            logFileLength(randomAccessFile);
            // randomAccessFile.
            logFileContent(randomAccessFile, lenOfTestData);
            
            // 3. 尝试改变第五号光标后的字符（第六个字符）
            randomAccessFile.seek(5 * 2); // 设置到第5个字符
            randomAccessFile.write("啊".getBytes());
            logFileContent(randomAccessFile, lenOfTestData);
            logFileLength(randomAccessFile);
        }

        // 通过良好地管理（持久化）巨大文件上传下载任务的状态（哪些部分已经下载或上传完成）
        // 就能设计一个简单的支持断点重开的上传或下载功能了。
    }

    private static void logFileLength(RandomAccessFile randomAccessFile) throws IOException {
        final int len = (int)randomAccessFile.length();
        System.out.println("File Length:" + len + " Bytes");
    }

    private static void logFileContent(RandomAccessFile randomAccessFile, final int len) throws IOException {
        final long originalPosition = randomAccessFile.getFilePointer();
        randomAccessFile.seek(0);
        final byte[] buffer = new byte[len];
        final int res = randomAccessFile.read(buffer);
        assert (res == len);
        System.out.println("Current Content:[" + new String(buffer) + "]");
        randomAccessFile.seek(originalPosition);
    }
    
    private RandomAccessFileSample() {}
}
