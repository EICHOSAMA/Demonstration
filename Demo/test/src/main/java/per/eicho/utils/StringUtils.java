package per.eicho.utils;

import java.util.Random;
import java.util.UUID;

public final class StringUtils {

    private static final int[] CJK_CHARACTER_RANGE = new int[]{0x4E00, 0x9FFF};
    
    /**
     * <p>随机生成CJK（中日韩）字符串</p>
     * 
     * <pre>
     *  随机生成一个指定长度（len）的字符串返回给调用方。
     *  
     *  随机生成的字符串满足以下条件：
     *    1. {@see String#length()}的返回值与 {@code len} 一致
     *    2. 字符串里每一个字符不包含超过 0xFFFF的部分，
     *       即不支持所有的Unicode Code Points(0x000000 - 0x10FFFF),
     *       仅支持Java里单char所对应的字符(2bytes, 0x0000 - 0xFFFF)
     * </pre>
     * 
     * @param len 生成目标字符串的长度，正整数(>0)
     * @return 被生成的随机字符串
     */
    public static String generateCJKStringRandomly(int len) {
        // 1. Check User Input 
        if (len <= 0) throw new IllegalAccessError("parameter 'len' should be a non-negative integer.");

        /*
         * [Relation Information]: 相关情报, memo
         *   1. java char size: 2bytes (0000 - FFFF)
         *   2. In Java, a character (char) is an unsigned 16 bit value; i.e 0 to FFFF.
         *   3. Java uses UTF-16 for the internal text representation
         *      See: [https://docs.oracle.com/javase/8/docs/technotes/guides/intl/overview.html]
         *   4. [Unicode scalar value]
         *      The range of legal code points is now U+0000 to U+10FFFF, known as Unicode scalar value.
         *      See: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Character.html#unicode 
         *   5. High-surrogate code point: A Unicode code point in the range U+D800 to U+DBFF.    
         *   6. Unicode plane.
         *      See: https://en.wikipedia.org/wiki/Plane_(Unicode)
         */
    
        // 2. 随机生成CodePoints数组
        final int[] codePoints = new int[len]; 
        final Random random = new Random();
        final int bound = 0xFFFF + 1; // exclusive
        for (int i = 0; i < len; i++) {
            int codePoint = random.nextInt(bound);
            while (codePoint < CJK_CHARACTER_RANGE[0] && CJK_CHARACTER_RANGE[1] < codePoint) codePoint = random.nextInt(bound); 

            System.out.println(codePoint);
            codePoints[i] = codePoint;
        }

        // 3. 生成字符串
        final String result = new String(codePoints, 0, len); 

        return result;
    }

    /**
     * <p>生成UUID字符串</p>
     * <pre>
     *  利用{@link UUID#randomUUID()}随机生成一个UUID并根据参数转换其至对应格式的UUID字符串。
     *  
     *  UUID: "e00e08c8-de2f-4cf9-ac07-ad98ae0fab83"，可根据参数生成四种结果。
     *    1. "e00e08c8de2f4cf9ac07ad98ae0fab83"
     *    2. "e00e08c8-de2f-4cf9-ac07-ad98ae0fab83"
     *    3. "E00E08C8DE2F4CF9AC07AD98AE0FAB83"
     *    4. "E00E08C8-DE2F-4CF9-AC07-AD98AE0FAB83"
     * </pre>
     * 
     * @param shouldUseLowerCase 是否使用小写
     * @param shouldRemoveDash 是否去掉连接符 '-'
     * @return 生成后的UUID字符串（根据是否去掉连接符长度分别为32和36）
     */
    public static String genUUIDStr(boolean shouldUseLowerCase, boolean shouldRemoveDash) {
        final UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        if (shouldRemoveDash) {
            uuidStr = uuidStr.replaceAll("-", "");
        }
        return shouldUseLowerCase ? uuidStr.toLowerCase() : uuidStr.toUpperCase();
    }
    
    /** 工具类, 隐藏构造方法 */
    private StringUtils() {}
}
