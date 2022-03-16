package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>946. Validate Stack Sequences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/validate-stack-sequences/">946. Validate Stack Sequences</a>
 */
public final class Q946 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 1. 1 <= pushed.length <= 1000
        // 2. 0 <= pushed[i] <= 1000
        // 3. All the elements of pushed are unique.
        // 4. popped.length == pushed.length
        // 5. popped is a permutation of pushed.        
        final int n = pushed.length;
        
        int p1 = 0;
        int p2 = 0;
        
        final int[] stack = new int[n];
        int stackSize = 0;
        while (p2 < n) {
            final int poppedNum = popped[p2++];

            // IF stack is empty or top element is not equal to popped num.
            // add elements into stack until poppedNum is finded or p1 arrived the bound.
            if (stackSize == 0 || stack[stackSize - 1] != poppedNum) {
                while (p1 < n && pushed[p1] != poppedNum) {
                    stack[stackSize++] = pushed[p1++]; // add to stack.
                }

                if (p1 == n) return false;
                // assert pushed[p1] == poppedNum;
                p1++; // push
                continue;
            }

            // assert stack[stackSize - 1] == poppedNum;
            stackSize--; // pop
        }

        return stackSize == 0;
    }
}
