package per.eicho.demo.leetcode_cn.offer;

/**
 * <p>剑指 Offer 20. 表示数值的字符串 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/">
 *   剑指 Offer 20. 表示数值的字符串</a>
 */
public final class Offer20 {
    public boolean isNumber(String s) {
        // 1. 1 <= s.length <= 20
        // 2. s 仅含
        //      英文字母（大写和小写），
        //      数字（0-9），
        //      加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.'
        s = s.trim();
        final int len = s.length();
        

        int eCount = 0;
        int ePos = -1;
        for (int i = 0; i < len; i++) {
            final char ch = s.charAt(i);

            if (isE(ch)) {
                eCount++;
                ePos = i;
                if (eCount > 1) return false;
            }
        }

        if (0 == eCount) {
            // 没有E, 判断是否是整数or小数
            return isDecimalOrInteger(s);
        } else {
            // 有E, 判断E前半是否是整数or小数，E后半是否是整数
            return isDecimalOrInteger(s.substring(0, ePos)) && 
                   isInteger(s.substring(ePos + 1));
        }
    }

    private boolean isE(char ch) {
        return ch == 'e' || ch == 'E';
    }

    private boolean isSign(char ch) {
        return ch == '+' || ch == '-';
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private boolean isDecimalOrInteger(String s) {
        return isDecimal(s) || isInteger(s);
    }

    private boolean isDecimal(String s) {
        // 判断是否是小数
        if (s == null || s.length() == 0) return false;
        final int len = s.length();

        /*
         * matching state:
         *  0b0000: init;
         *  0b0001: sign already matched;
         *  0b0010: matching numerical part before '.'; 
         *  0b0100: point '.' matched;
         *  0b1000: matching numerical part after '.';
         */
        int state = 0;
        int p = 0;
        while (p < len) {
            char ch = s.charAt(p++);
            // Fix Bug: ". 1" 应该返回 false...
            if (ch == ' ') return false; // 无视空格
            if (isSign(ch)) {
                if (state != 0) return false;
                state = 1;
                continue;
            }

            if (ch == '.') {
                if ((state & 0b0100) != 0) return false; // 已经匹配过点了
                state |= 0b0100;
                continue;
            }

            if (isDigit(ch)) {
                if ((state & 0b0100) != 0) {
                    // 已经匹配过点了
                    state |= 0b1000;
                    continue;   
                } else {
                    state |= 0b0010;
                }
                continue;
            }

            // 非法字符
            return false;
        }

        // 下述格式之一：
        //  pattern1: 至少一位数字，后面跟着一个点 '.'
        //  pattern2: 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
        //  pattern3: 一个点 '.' ，后面跟着至少一位数字        
        if ((state & 0b0110) == 0b0110 || // pattern 1
            (state & 0b1110) == 0b1110 || // pattern 2
            (state & 0b1100) == 0b1100)   // pattern 3
            return true;
        return false;
    }

    private boolean isInteger(String s) {
        // 判断是否是整数
        if (s == null || s.length() == 0) return false;        
        final int len = s.length();
        int p = 0;

        /*
         * matching state:
         *  0: init,
         *  1: sign already matched,
         *  2: matching numerical part.
         */
        int state = 0;
        int num = 0;
        while (p < len) {
            char ch = s.charAt(p++);
            if (ch == ' ') return false; // 无视空格
            if (isSign(ch)) {
                if (state != 0) return false;
                state = 1;
            } else if (isDigit(ch)) {
                state = 2;
                num = num * 10 + (ch - '0');
            } else return false; // 非法字符
        }
        return state == 2;
    }
}
