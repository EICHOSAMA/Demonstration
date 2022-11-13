package per.eicho.demo.nowcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

public final class HJ068 {

    private static final class Record {
        final int id;
        final String name;
        final int source;

        Record(int id, String name, int source) {
            this.id = id;
            this.name = name;
            this.source = source;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            final int n = in.nextInt();
            final int sortMode = in.nextInt();
            final Record[] records = new Record[n];
            for (int i = 0; i < n; ) {
                final String recordStr = in.nextLine();
                final String[] parts = recordStr.split("\\s");
                if (parts.length != 2) continue;
                records[i++] = new Record(i, parts[0], Integer.valueOf(parts[1]));
            }

            processInput(n, records, sortMode);
        }

        in.close();
    }

    private static void processInput(int n, Record[] records, int sortMode) {
        // 0代表从高到低，1代表从低到高
        if (sortMode == 1) {
            Arrays.sort(records, (r1, r2) -> { // 低到高
                if (r1.source != r2.source) return Integer.compare(r1.source, r2.source);
                return Integer.compare(r1.id, r2.id);
            }); 
        } else {
            Arrays.sort(records, (r1, r2) -> { // 高到低
                if (r1.source != r2.source) return Integer.compare(r2.source, r1.source);
                return Integer.compare(r1.id, r2.id);
            }); 
        }

        for (Record record : records) {
            System.out.println(record.name + " " + record.source);
        }
    }
}
