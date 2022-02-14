package per.eicho.demo.leetcode.q001_100;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>68. Text Justification 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/text-justification/">68. Text Justification</a>
 */
public final class Q68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        // 1. 1 <= words.length <= 300
        // 2. 1 <= words[i].length <= 20
        // 3. words[i] consists of only English letters and symbols.
        // 4. 1 <= maxWidth <= 100
        // 5. words[i].length <= maxWidth        
        final List<String> result = new LinkedList<>();
        // assert 0 < words[i].length <= maxWidth
        // assert words.length >= 1.
        /**
         * il: index left. inclusive
         * ir: index right. inclusive
         * cursor: current processing index.
         * nowLength: string in words'range [il,ir]'s length. Containing space between each two words.
         */
        int il = 0, ir = 0, cursor = 1, nowLength = words[0].length();
        final int count = words.length;

        /**
         * lenC: length of words[cursor]
         */
        int lenC;
        StringBuilder sb = new StringBuilder(maxWidth);
        while (cursor < count) {
            /**
             * judge if words[cursor] could be append into range [il, ir] or not.
             * how to judge?
             *  - nowLenght + 1 + words[cursor].length() <= maxWidth
             */
            lenC = words[cursor].length();
            if (nowLength + 1 + lenC <= maxWidth) {
                // append cursor into range[il, ir]
                ir++;
                nowLength = nowLength + 1 + lenC;
            } else {
                // construct a line.
                int interval = ir - il; // [1,2], interval is 1.
                if (interval != 0) {
                    // case 1: more than one elements.
                    int remainder =  (maxWidth - nowLength) % interval; //
                    int spaceCount = (maxWidth - nowLength) / interval; // extra space count for each slot
                    //System.out.println("interval:" + interval + " remainder:" + remainder + " spaceCount:" + spaceCount + " il:" + il + " ir:" + ir);
                    while (il < ir) {
                        sb.append(words[il++]).append(' ');
                        for (int i = 0; i < spaceCount ; i++) {
                            sb.append(' ');
                        }
                        if (remainder > 0) {
                            remainder--;
                            sb.append(' '); // extra space
                        }
                    }
                    sb.append(words[ir]);
                } else {
                    // case2: only one element.
                    sb.append(words[il]);
                    for (int i = 0; i < maxWidth - nowLength ; i++) {
                        sb.append(' ');
                    }
                }

                result.add(sb.toString());
                sb.delete(0, maxWidth); // clear string builder.

                // start a new line.
                il = cursor;
                ir = cursor;
                nowLength = lenC;
            }
            cursor++; // move to next.
        }
        // add last line.
        while (il <= ir) {
            sb.append(words[il++]).append(' ');
        }
        while (sb.length() < maxWidth) {
            sb.append(' ');
        }
        sb.setLength(maxWidth);
        result.add(sb.toString());

        return result;
    }
}
