package per.eicho.demo.leetcode.q1601_1700;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>1656. Design an Ordered Stream 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-an-ordered-stream/">
 *   1656. Design an Ordered Stream</a>
 */
@SuppressWarnings("unused")
public final class Q1656 {
    private static final class OrderedStream {

        private int ptr = 0;
        private final String[] data;
        private final int n;

        /** Constructs the stream to take n values. */
        public OrderedStream(int n) {
            this.n = n;
            data = new String[n];            
        }
        
        /** 
         * Inserts the pair (idKey, value) into the stream, 
         * then returns the largest possible chunk of currently inserted values that appear next in the order. 
         */
        public List<String> insert(int idKey, String value) {
            idKey--;

            data[idKey] = value;
            final List<String> result = new ArrayList<>();
            while (ptr < n && data[ptr] != null) {
                result.add(data[ptr]);
                ptr++;
            }
            return result;
        }
    }
    
}
