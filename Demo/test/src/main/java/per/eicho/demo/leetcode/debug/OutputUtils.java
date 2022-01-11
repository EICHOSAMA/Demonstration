package per.eicho.demo.leetcode.debug;

public final class OutputUtils {
    
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
}
