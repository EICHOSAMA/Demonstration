package per.eicho.demo.nowcoder.nc;

public final class NC052 {
    /**
     * 
     * @param s string字符串 
     * @return bool布尔型
     */
    public boolean isValid (String s) {
        // len: [0, 1_0000]
        final int len;
        if (s == null || (len = s.length()) == 0) return true;
        if (len % 2 != 0) return false;
        
        final int[] stack = new int[len];
        int stackP = 0;
        for (int i = 0; i < len; i++) {
            final int type = getType(s.charAt(i));

            if (type < 3) {
                stack[stackP++] = type;
            } else {
                final int targetType = type - 3;
                if (stackP == 0 || stack[--stackP] != targetType) return false;
            }
        }

        return stackP == 0;
    }

    private int getType(char ch) {
        switch (ch) {
            case '(': return 0;
            case ')': return 3;
            case '{': return 1;
            case '}': return 4;
            case '[': return 2;
            case ']': return 5;
            default: return -1; // impossible
        }
    }

    public static void main(String[] args) {
        NC052 nc052 = new NC052();
        System.out.println(nc052.isValid("[]"));
    }
}
