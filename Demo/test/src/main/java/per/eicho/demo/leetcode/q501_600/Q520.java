package per.eicho.demo.leetcode.q501_600;

/**
 * <p>520. Detect Capital 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/detect-capital/">520. Detect Capital</a>
 */
public final class Q520 {

    public boolean detectCapitalUse2(String word) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^((?<case1>[A-Z]*)|(?<case2>[a-z]*)|(?<case3>[A-Z][a-z]*))$");
        return pattern.matcher(word).find();      
    }

    public boolean detectCapitalUse(String word) {
        // 1. 1 <= word.length <= 100
        // 2. word consists of lowercase and uppercase English letters.
        if (word.length() == 1) return true;

        if (!isCapital(word.charAt(0))) {
            for (int i = 1; i < word.length(); i++) {
                if (isCapital(word.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        final boolean shouldBeCapital = isCapital(word.charAt(1));
        for (int i = 1; i < word.length(); i++) {
            if (isCapital(word.charAt(i)) != shouldBeCapital) {
                return false;
            }
        }
        return true;
    }

    private boolean isCapital(int ch) {
        return 'A' <= ch && ch <= 'Z';
    }
}
