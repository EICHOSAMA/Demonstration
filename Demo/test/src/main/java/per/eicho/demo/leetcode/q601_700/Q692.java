package per.eicho.demo.leetcode.q601_700;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>692. Top K Frequent Words 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/top-k-frequent-words/">
 *   692. Top K Frequent Words</a>
 */
public final class Q692 {

    private static final class FrequenceInfo {

        final String word;
        final int frequence;

        FrequenceInfo(String word, int frequence) {
            this.word = word;
            this.frequence = frequence;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        // 1. 1 <= words.length <= 500
        // 2. 1 <= words[i] <= 10
        // 3. words[i] consists of lowercase English letters.
        // 4. k is in the range [1, The number of unique words[i]]        
        final List<String> result = new ArrayList<>(k);
        final Map<String, int[]> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word)) map.put(word, new int[]{0});
            map.get(word)[0]++;
        }

        final List<FrequenceInfo> infos = new ArrayList<>(map.size());
        for (String key : map.keySet()) {
            final int count = map.get(key)[0];
            infos.add(new FrequenceInfo(key, count));
        }

        infos.sort((f1, f2) -> {
            // 1.frequence descending order
            if (f1.frequence != f2.frequence) return Integer.compare(f2.frequence, f1.frequence);
            // with the same frequency by their lexicographical order.
            return f1.word.compareTo(f2.word);
        });

        for (int i = 0; i < k; i++) result.add(infos.get(i).word);
        return result;
    }
}
