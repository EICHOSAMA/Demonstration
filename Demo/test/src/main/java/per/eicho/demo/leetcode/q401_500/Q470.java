package per.eicho.demo.leetcode.q401_500;

/**
 * <p>470. Implement Rand10() Using Rand7() </p>
 * 
 * @see <a href="https://leetcode.com/problems/implement-rand10-using-rand7/">
 *   470. Implement Rand10() Using Rand7()</a>
 */
public final class Q470 {
    public int rand10() {
        int idx;
        do {
            int r71 = rand7() - 1;
            int r72 = rand7();
            idx = r71 * 7 + r72;
        } while (idx > 40); // random [1, 40] 40 Nums
        // 1,2,3,4

        return (--idx / 4) + 1; // random [1,10]
    }

    private int rand7() {
        // FAKE METHOD
        return -1;
    }
}
