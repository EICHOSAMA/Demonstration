package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1359. Count All Valid Pickup and Delivery Options 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/">
 *   1359. Count All Valid Pickup and Delivery Options</a>
 */
public final class Q1359 {
    public int countOrders(int n) {
        // 1. 1 <= n <= 500
        final long MODULO = 1000_000_000 + 7;
        long current = 1;
        long next = 1;
        for (int i = 2; i <= n; i++) {
            next = (current * i * (2 * i - 1)) % MODULO;
            current = next;
        }
        return (int)current;
    }

    public static void main(String[] args) {
        Q1359 q1359 = new Q1359();
        System.out.println(q1359.countOrders(500));
    }
}
