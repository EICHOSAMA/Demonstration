package per.eicho.demo.leetcode.q101_200;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>187. Repeated DNA Sequences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/repeated-dna-sequences/">187. Repeated DNA Sequences</a>
 */
public final class Q187 {
    public List<String> findRepeatedDnaSequences(String s) {
        // 1. 0 <= s.length <= 10^5
        // 2. s[i]=='A'、'C'、'G' or 'T'        
        final int n = s.length();
        final List<String> result = new LinkedList<>();
        if (n <= 10) return result;

        // key, count
        final Map<Integer, Integer> map = new HashMap<>();
        
        int key = 0;
        int l = 0, r = 0;
        for (r = 0; r < 9; r++) {
            key <<= 2;
            key |= getCode(s.charAt(r));
        }

        while (r < n) {
            key = ((key << 2) | getCode(s.charAt(r++))) & 0b11111111111111111111;            
            // [l, r)
            final Integer iKey = key;
            if (!map.containsKey(iKey)) {
                map.put(iKey, 1);
            } else {
                if (map.get(iKey) == 1) {
                    map.put(iKey, 2);
                    result.add(s.substring(l, r));
                }
            }

            l++;
        }
        return result;
    }

    private int getCode(int ch) {
        switch (ch) {
            case 'A': return 0b00;
            case 'C': return 0b01;
            case 'G': return 0b10;
            default: return 0b11;
        }
    }
}
