package per.eicho.demo.nowcoder.huawei;

import java.util.LinkedList;
import java.util.Scanner;

public final class HJ017 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        in.useDelimiter(";");
        LinkedList<String> commands = new LinkedList<>();
        while (in.hasNext()) { // 注意 while 处理多个 case
            commands.add(in.next());
        }

        processInput(commands);
        
        in.close();
    }

    private static void processInput(LinkedList<String> commands) {
        final int[] coordinate = new int[]{0, 0};
        for (String command : commands) {
            final int[] commandInfo = parse(command);
            if (commandInfo == null) continue; // skip;
            
            final int step = commandInfo[1];
            final int[] direction = directions[commandInfo[0]];
            coordinate[0] += step * direction[0];
            coordinate[1] += step * direction[1];
        }

        System.out.println(coordinate[0] + "," + coordinate[1]);
    }

    private static final int[][] directions = new int[][]{
        new int[]{-1, 0}, new int[]{1, 0}, new int[]{0, 1}, new int[]{0, -1}
    };

    private static int[] parse(String command) {
        if (command.length() < 2 || command.length() > 3) return null;
        final int[] commandInfo = new int[]{-1, -1};
        commandInfo[0] = getOPIndex(command.charAt(0));
        if (commandInfo[0] == -1) return null;
        final String stepStr = command.substring(1);
        try {
            final int step = Integer.parseInt(stepStr);
            if (step <= 0 || step > 99) return null;
            commandInfo[1] = step;
            return commandInfo;
        } catch (Throwable e) {
            return null;
        }
    }

    private static int getOPIndex(char op) {
        switch (op) {
            case 'A': return 0; // left
            case 'D': return 1; // right
            case 'W': return 2; // up
            case 'S': return 3; // down
            default: return -1;
        }
    }
}
