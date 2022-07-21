package per.eicho.demo.leetcode.debug;

import java.util.List;

import per.eicho.demo.leetcode.datastructure.ListNode;

public final class OutputUtils {

    public static void println(int[] nums) {
        if (nums == null) {
            System.out.println("null");
            return;
        }

        System.out.print("[");
        if (nums.length > 0) {
            for (int i = 0; i < nums.length - 1; i++) {
                System.out.print(nums[i]);
                System.out.print(",");
            }
            System.out.print(nums[nums.length - 1]);
        }
        System.out.println("]");
    }
    
    public static void println(String[] strings) {
        if (strings.length == 0) {
            System.out.println();
            return;
        }

        for (int i = 0; i < strings.length - 1; i++) {
            System.out.print(strings[i]);
            System.out.print(',');
        }
        System.out.println(strings[strings.length - 1]);
    }

    public static void println(double[] strings) {
        if (strings.length == 0) {
            System.out.println();
            return;
        }

        for (int i = 0; i < strings.length - 1; i++) {
            System.out.print(strings[i]);
            System.out.print(" ,");
        }
        System.out.println(strings[strings.length - 1]);
    }

    public static void println(List<String> strings) {
        if (strings == null) {
            System.out.println("null");
            return;
        }

        System.out.print('[');
        for (int i = 0; i < strings.size(); i++) {
            final String str = strings.get(i);
            System.out.print(str);
            if (i != strings.size() - 1) System.out.print(',');
        }
        System.out.println(']');
    }

    public static void println(ListNode node) {
        if (node == null) {
            System.out.println("null");
            return;
        }

        final StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (node != null) {
            sb.append(node.val);
            node = node.next;
            if (node != null) sb.append(',');
        }
        sb.append(']');
        System.out.println(sb.toString());
    }

    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

    /**
     * <p>16进制输出</p>
     * 
     * <pre>
     *  把给定的byte数组转换为16进制字符串(由0-9,A-F字符组成)进行输出。
     *  1byte = 8bits 会被转换成 2个16进制字符。
     *  如byte 0x0F 会被转换成 "0F" 输出。
     * </pre>
     * 
     * @param input
     */
    public static void printAsHexString(byte[] input) {
        if (input == null) {
            System.out.println("null");
            return;
        }

        final char[] hexChars = new char[input.length * 2];
        for (int j = 0; j < input.length; j++) {
            int v = input[j] & 0xFF; // & 0b1111_1111 保留低8位数据，清除高位数据到0。
            hexChars[j * 2] = HEX_CHARS[v >>> 4];      // 取高四位，映射
            hexChars[j * 2 + 1] = HEX_CHARS[v & 0x0F]; // 取低四位，映射
        }
        System.out.println(new String(hexChars));
    }
}
