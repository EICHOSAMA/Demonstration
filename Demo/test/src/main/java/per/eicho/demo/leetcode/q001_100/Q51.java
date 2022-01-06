package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.List;

final public class Q51 {

    private static class Q51ProcessContext {
        
        final static int COL_USED = -1;
        final static int NO_COL_CHOOSED = -1;

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

        final int[] choosedCols;

        final List<List<String>> result;
        final String defaultRow;

        int currentRow;

        Q51ProcessContext(int n) {
            this.n = n;
            choosedCols = new int[n];
            for (int i = 0; i < n; i++) {
                choosedCols[i] = NO_COL_CHOOSED;
            }

            this.mark1 = new boolean[2*n - 1];
            this.mark2 = new boolean[2*n - 1];

            candidateCols = new int[n];
            for (int i = 0; i < n; i++) {
                candidateCols[i] = i;
            }

            currentRow = 0;

            result = new ArrayList<>();
            
            StringBuilder sb = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                sb.append('.');
            }
            defaultRow = sb.toString();
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
            choosedCols[currentRow] = candidateCol;
            currentRow++;
        }

        private void cancel(int candidateCol) {
            currentRow--;
            choosedCols[currentRow] = NO_COL_CHOOSED;

            final int mark1Index = coordinate2Mark1Index(currentRow, candidateCol);
            final int mark2Index = coordinate2Mark2Index(currentRow, candidateCol);
            
            mark1[mark1Index] = false;
            mark2[mark2Index] = false;
            candidateCols[candidateCol] = candidateCol;
        }

        private void takeSnapshot() {
            // assert currentRow == n;

            final List<String> snapShot = new ArrayList<>(n);
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {
                sb.append(defaultRow);

                sb.replace(choosedCols[i], choosedCols[i] + 1, "Q");

                snapShot.add(sb.toString());
                // clear content
                sb.delete(0, sb.length());
            }

            result.add(snapShot);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        final Q51ProcessContext processContext = new Q51ProcessContext(n);
        search(processContext);

        return processContext.result;
    }

    private void search(final Q51ProcessContext processContext) {
        if (processContext.currentRow == processContext.n) {
            processContext.takeSnapshot();
            return;
        }

        final int[] candidateCols = processContext.candidateCols;

        for (int i = 0; i < candidateCols.length; i++) {
            final int candidateCol = candidateCols[i];
            if (candidateCol == Q51ProcessContext.COL_USED) continue;

            if (processContext.tryChoose(candidateCol)) {
                processContext.choose(candidateCol);
                search(processContext);
                processContext.cancel(candidateCol);
            }
        }
    }

    public static void main(String[] args) {
        Q51 q51 = new Q51();
        q51.solveNQueens(4);
    }
}
