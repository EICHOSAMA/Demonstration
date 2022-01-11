package per.eicho.demo.leetcode.q001_100;

/**
 * <p>https://leetcode.com/problems/n-queens-ii/ 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/n-queens-ii/">https://leetcode.com/problems/n-queens-ii/</a>
 */
public final class Q52 {

    private static class Q52ProcessContext {
        
        final static int COL_USED = -1;

        final int n;
        
        /**
         * 对角线  [方向:\]，↙开始是0，↗是2n-2
         */
        final boolean[] mark1;

        /**
         * 对角线2 [方向:/]，↖开始是0，↘是2n-2
         */
        final boolean[] mark2;

        final int[] candidateCols;

        int currentRow;

        Q52ProcessContext(int n) {
            this.n = n;

            this.mark1 = new boolean[2*n - 1];
            this.mark2 = new boolean[2*n - 1];

            candidateCols = new int[n];
            for (int i = 0; i < n; i++) {
                candidateCols[i] = i;
            }

            currentRow = 0;
        }

        private int coordinate2Mark1Index(int x, int y) {
            return y - x + n - 1;
        }

        private int coordinate2Mark2Index(int x, int y) {
            return x + y;
        }

        private boolean tryChoose(int candidateCol) {
            final int mark1Index = coordinate2Mark1Index(currentRow, candidateCol);
            final int mark2Index = coordinate2Mark2Index(currentRow, candidateCol);
            
            if (!mark1[mark1Index] && !mark2[mark2Index]) return true;
            return false;
        }

        private void choose(int candidateCol) {
            final int mark1Index = coordinate2Mark1Index(currentRow, candidateCol);
            final int mark2Index = coordinate2Mark2Index(currentRow, candidateCol);

            mark1[mark1Index] = true;
            mark2[mark2Index] = true;
            candidateCols[candidateCol] = COL_USED;
            currentRow++;
        }

        private void cancel(int candidateCol) {
            currentRow--;

            final int mark1Index = coordinate2Mark1Index(currentRow, candidateCol);
            final int mark2Index = coordinate2Mark2Index(currentRow, candidateCol);
            
            mark1[mark1Index] = false;
            mark2[mark2Index] = false;
            candidateCols[candidateCol] = candidateCol;
        }
    }

    public int totalNQueens(int n) {
        final Q52ProcessContext processContext = new Q52ProcessContext(n);

        return search(processContext);
    }

    private int search(final Q52ProcessContext processContext) {
        if (processContext.currentRow == processContext.n) return 1;

        final int[] candidateCols = processContext.candidateCols;
        int result = 0;

        for (int i = 0; i < candidateCols.length; i++) {
            final int candidateCol = candidateCols[i];
            if (candidateCol == Q52ProcessContext.COL_USED) continue;

            if (processContext.tryChoose(candidateCol)) {
                processContext.choose(candidateCol);
                result += search(processContext);
                processContext.cancel(candidateCol);
            }
        }
        return result;
    }
}
