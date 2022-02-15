package per.eicho.demo.leetcode.q101_200;

/**
 * <p>165. Compare Version Numbers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/compare-version-numbers/">165. Compare Version Numbers</a>
 */
public final class Q165 {
    public int compareVersion(String version1, String version2) {
        // 1. 1 <= version1.length, version2.length <= 500
        // 2. version1 and version2 only contain digits and '.'.
        // 3. version1 and version2 are valid version numbers.
        // 4. All the given revisions in version1 and version2 can be stored in a 32-bit integer.        
        final String[] versionNums1 = version1.split("\\.");
        final String[] versionNums2 = version2.split("\\.");
        final int n1 = versionNums1.length;
        final int n2 = versionNums2.length;

        int c1 = 0, c2 = 0;
        while (c1 < n1 || c2 < n2) {
            final int vNum1 = (c1 < n1) ? Integer.valueOf(versionNums1[c1]) : 0;
            final int vNum2 = (c2 < n2) ? Integer.valueOf(versionNums2[c2]) : 0;
            if (vNum1 != vNum2) return Integer.compare(vNum1, vNum2);
            c1++;
            c2++;
        }
        return 0;
    }

    public static void main(String[] args) {
        Q165 q165 = new Q165();
        System.out.println(q165.compareVersion("0.1", "1.1"));
    }
}
