package per.eicho.demo.leetcode.debug;

import java.util.List;

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
}
