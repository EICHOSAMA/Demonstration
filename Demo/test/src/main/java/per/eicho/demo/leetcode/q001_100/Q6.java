package per.eicho.demo.leetcode.q001_100;

/**
 * <p>6. Zigzag Conversion 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/zigzag-conversion/">6. Zigzag Conversion</a>
 */
public final class Q6 {
    private char[] input;
    private char[] output;

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0)
            return "";
        if (numRows == 1)
            return s;

        input = s.toCharArray();
        final int len = s.length();
        output = new char[len];

        int outputCursor = 0;

        final int interval = 2 * numRows - 2;

        /**
         * Process First Line.
         */
        int inputCursor = 0;
        while (inputCursor < len) {
            output[outputCursor++] = input[inputCursor];
            inputCursor += interval;
        }

        /**
         * Process Middle Lines.
         */
        final int[] intervals = new int[2];
        int xxx;
        for (int row = 1; row < numRows - 1; row++) {
            inputCursor = row;
            if (inputCursor >= len) {
                return String.valueOf(output);
            }

            xxx = 0;
            intervals[1] = 2 * row;
            intervals[0] = interval - intervals[1];
            do {
                output[outputCursor++] = input[inputCursor];
                inputCursor += intervals[xxx];
                xxx ^= 1; // change to next interval.
            } while (inputCursor < len);

            // i - i + interval
            // i - End - 2  - End:i + interval
        }

        /**
         * Process Last Line.
         */
        inputCursor = numRows - 1;
        while (inputCursor < len) {
            output[outputCursor++] = input[inputCursor];
            inputCursor += interval;
        }

        return String.valueOf(output);
    }
}
