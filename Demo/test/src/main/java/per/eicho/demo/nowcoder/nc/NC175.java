package per.eicho.demo.nowcoder.nc;

import java.util.Stack;

public final class NC175 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * @param s string字符串 
     * @return bool布尔型
     */
    public boolean isValidString(String s) {
        // len: [1, 100],  字符串s只包含以下三种字符: (，*，)
        final int len = s.length();
        int p = 0;
        final Stack<Integer> stack = new Stack<>();
        while (p < len) {
            final char ch = s.charAt(p++);
            if (ch == '(') {
                if (stack.isEmpty()) {
                    stack.add(genDataWithCount('(', 1));
                } else {
                    if (getChar(stack.peek()) == '(') {
                        stack.add(stack.pop() + 1);
                    } else {
                        stack.add(genDataWithCount('(', 1));
                    }
                }
            } else if (ch == '*') {
                if (stack.isEmpty()) {
                    stack.add(genDataWithCount('*', 1));
                } else {
                    if (getChar(stack.peek()) == '*') {
                        stack.add(stack.pop() + 1);
                    } else {
                        stack.add(genDataWithCount('*', 1));
                    }
                }
            } else { /* ')' */
                if (stack.isEmpty()) return false;

                if (getChar(stack.peek()) == '(') {
                    int data = stack.pop();
                    int count = getCount(data);
                    if (--count == 0) {
                        continue;
                    } else {
                        stack.add(--data);
                    }
                } else {
                    // merge
                    int data = genDataWithCount('*', 0);
                    while (!stack.isEmpty() && getChar(stack.peek()) == '*') {
                        data += getCount(stack.pop());
                    }

                    if (!stack.isEmpty()) {
                        // 找到括号，消耗括号
                        int topData = stack.pop() - 1;
                        if (getCount(topData) != 0) stack.add(topData);
                        stack.add(data);
                    } else {
                        // 没找到括号，消耗*
                        data--;
                        if (getCount(data) != 0) stack.add(data);
                    }
                }

                // if (lv > 0) {
                //     lv--;
                // } else if (star > 0) {
                //     star--;
                // } else {
                //     return false;
                // }
            }
        }

        int star = 0;
        while (!stack.isEmpty()) {
            int topData = stack.pop();
            if (getChar(topData) == '(') {
                star -= getCount(topData);
                if (star < 0) return false;
            } else {
                star += getCount(topData);
            }
        }

        return true;
    }

    private int genDataWithCount(char ch, int count) {
        return (ch << 16) + count;
    }

    private char getChar(int data) {
        return (char)(data >> 16);
    }

    private int getCount(int data) {
        return data & 0xFFFF;
    }
}
