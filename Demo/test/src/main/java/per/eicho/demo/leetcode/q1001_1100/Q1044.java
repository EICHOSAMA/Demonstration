package per.eicho.demo.leetcode.q1001_1100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>1044. Longest Duplicate Substring 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-duplicate-substring/">
 *   1044. Longest Duplicate Substring</a>
 */
public final class Q1044 {

    public String longestDupSubstring(String s) {
        // 1. 2 <= s.length <= 3 * 10^4
        // 2. s consists of lowercase English letters.
        final int n = s.length();

        int l = 0, r = n - 1;
        int len = 0;
        int idx = -1;
        while (l < r) {
            final int mid = (l + r + 1) / 2;

            final int check = check(s, mid);
            if (check == -1) {
                // failed
                r = mid - 1;
            } else {
                l = mid;
                
                idx = check;
                len = mid;
                System.out.println(len);
            }
        }

        return idx == -1 ? "" : s.substring(idx, idx + len) ;
    }

    private int check(String s, final int len) {
        final int n = s.length();
        // <Hash, List<Idx>>
        final Map<Long, List<Integer>> map = new HashMap<>();
        
        long[] base = new long[len];
        base[len - 1] = 1;
        final long modulo = 1_000_000_007L;
        for (int i = len - 2; i >= 0; i--) {
            base[i] = (((base[i + 1] * 27L) % modulo) + modulo) % modulo;
        }

        int l = 0, r = 0; // [l, r)
        long hash = 0;
        while (r < n) {
            while (r - l < len) {
                final char ch = s.charAt(r++);
                hash = (((hash * 27L + (long)ch) % modulo) + modulo) % modulo;
            }

            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<>());
                map.get(hash).add(l);
            } else {
                final List<Integer> idxList = map.get(hash);
                for (int idx : idxList) {
                    if (isEqual(s, idx, l, len)) return idx;
                }
                idxList.add(l);
            }

            final char ch = s.charAt(l++);
            hash = (((hash - (base[0] * (long)ch)) % modulo) + modulo) % modulo;
        }

        return -1;
    }

    private boolean isEqual(String s, int p1, int p2, int len) {
        while (len-- >= 1) {
            if (s.charAt(p1++) != s.charAt(p2++)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Q1044 q1044 = new Q1044();
        System.out.println("[" + q1044.longestDupSubstring("okmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajvielokmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajviel") + "]");
        System.out.println("okmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajviel".length());
    }
}
