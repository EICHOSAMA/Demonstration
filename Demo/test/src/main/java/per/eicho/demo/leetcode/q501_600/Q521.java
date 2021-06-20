package per.eicho.demo.leetcode.q501_600;

final public class Q521 {
    public int findLUSlength(String a, String b) {
        return a.equals(b)? -1: Math.max(a.length(), b.length());
    }
}
