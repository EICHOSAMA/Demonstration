package per.eicho.demo.leetcode.q1201_1300;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>1233. Remove Sub-Folders from the Filesystem 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/">
 *   1233. Remove Sub-Folders from the Filesystem</a>
 */
public final class Q1233 {

    private static final class Trie {

        Map<String, Trie> children;
        boolean isLeaf;
        String pathRef;

        Trie() {
            children = new HashMap<>();
        }

        void setAsLeaf(String pathRef) {
            isLeaf = true;
            children = null;
            this.pathRef = pathRef;
        }
    }

    public List<String> removeSubfolders(String[] folder) {
        // 1. 1 <= folder.length <= 4 * 10^4
        // 2. 2 <= folder[i].length <= 100
        // 3. folder[i] contains only lowercase letters and '/'.
        // 4. folder[i] always starts with the character '/'.
        // 5. Each folder name is unique.
        final Trie root = new Trie();
        final StringBuilder sb = new StringBuilder();
        for (String path : folder) {
            Trie cursor = root;

            final int len = path.length();
            int p = 0;
            while (p < len) {
                sb.delete(0, sb.length()); 

                char ch;
                p++;
                while (p < len && (ch = path.charAt(p)) != '/') {
                    sb.append(ch);
                    p++;
                }

                final String ph = sb.toString();
                if (!cursor.children.containsKey(ph)) cursor.children.put(ph, new Trie());
                cursor = cursor.children.get(ph);
                if (cursor.isLeaf) break;
                if (p == len) cursor.setAsLeaf(path);
            }
        }

        final Queue<Trie> queue = new LinkedList<>();
        queue.add(root);
        final List<String> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final Trie trie = queue.poll();
                if (trie.isLeaf) {
                    result.add(trie.pathRef);
                    continue;
                }

                for (Trie child : trie.children.values()) queue.add(child);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Q1233 q1233 = new Q1233();
        OutputUtils.println(q1233.removeSubfolders(new String[]{"/a","/a/b","/c/d","/c/d/e","/c/f"}));
    }
}
