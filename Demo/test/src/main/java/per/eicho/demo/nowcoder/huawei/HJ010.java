package per.eicho.demo.nowcoder.huawei;

import java.util.BitSet;
import java.util.Scanner;

public final class HJ010 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            final String line = in.nextLine();
            processInput(line);
        }

        in.close();
    }

    private static void processInput(String str) {
        final BitSet bitSet = new BitSet(128);
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            final int idx = str.charAt(i);
            if (idx < 0 || idx > 127) continue;
            if (bitSet.get(idx)) continue;
            bitSet.set(idx);
            count++;
        }
        System.out.println(count);
    }
}
