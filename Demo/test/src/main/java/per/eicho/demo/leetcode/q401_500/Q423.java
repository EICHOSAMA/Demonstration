package per.eicho.demo.leetcode.q401_500;

/**
 * <p>423. Reconstruct Original Digits from English 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reconstruct-original-digits-from-english/">
 *   423. Reconstruct Original Digits from English</a>
 */
public final class Q423 {

    final int[][] enCounts = new int[][]{
        //        a,b,c,d,e,f,g,h,i,j, k,l,m,n,o,p,q,r,s,t, u,v,w,x,y,z
        new int[]{0,0,0,0,1,0,0,0,0,0, 0,0,0,0,1,0,0,1,0,0, 0,0,0,0,0,1}, // 0, zero
        new int[]{0,0,0,0,1,0,0,0,0,0, 0,0,0,1,1,0,0,0,0,0, 0,0,0,0,0,0}, // 1, one
        new int[]{0,0,0,0,0,0,0,0,0,0, 0,0,0,0,1,0,0,0,0,1, 0,0,1,0,0,0}, // 2, two
        new int[]{0,0,0,0,2,0,0,1,0,0, 0,0,0,0,0,0,0,1,0,1, 0,0,0,0,0,0}, // 3, three
        new int[]{0,0,0,0,0,1,0,0,0,0, 0,0,0,0,1,0,0,1,0,0, 1,0,0,0,0,0}, // 4, four
        new int[]{0,0,0,0,1,1,0,0,1,0, 0,0,0,0,0,0,0,0,0,0, 0,1,0,0,0,0}, // 5, five
        new int[]{0,0,0,0,0,0,0,0,1,0, 0,0,0,0,0,0,0,0,1,0, 0,0,0,1,0,0}, // 6, six
        new int[]{0,0,0,0,2,0,0,0,0,0, 0,0,0,1,0,0,0,0,1,0, 0,1,0,0,0,0}, // 7, seven
        new int[]{0,0,0,0,1,0,1,1,1,0, 0,0,0,0,0,0,0,0,0,1, 0,0,0,0,0,0}, // 8, eight
        new int[]{0,0,0,0,1,0,0,0,1,0, 0,0,0,2,0,0,0,0,0,0, 0,0,0,0,0,0}, // 9, nine
    };

    final int[][] order = new int[][]{
        new int[]{'z', 0},
        new int[]{'w', 2},
        new int[]{'x', 6},
        new int[]{'s', 7},
        new int[]{'v', 5},
        new int[]{'f', 4},
        new int[]{'o', 1},
        new int[]{'g', 8},
        new int[]{'n', 9},
        new int[]{'e', 3}
    };

    public String originalDigits(String s) {
        // 1. 1 <= s.length <= 10^5
        // 2. s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
        // 3. s is guaranteed to be valid.
        final int n = s.length();
        final int[] charCounts = new int[26];
        for (int i = 0; i < n; i++) charCounts[s.charAt(i) - 'a']++;
        final int[] counts = new int[10]; // [0, 9]

        for (int i = 0; i < order.length; i++) {
            final char ch = (char)order[i][0];
            final int idx = ch - 'a';
            final int digit = order[i][1];
            final int enCount = enCounts[digit][idx];
            
            final int count = charCounts[idx] / enCount;
            
            counts[digit] = count;
            for (int j = 0; j < 26; j++) charCounts[j] -= enCounts[digit][j] * count;
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) sb.append(i);
        }
        return sb.toString();
    }
}
