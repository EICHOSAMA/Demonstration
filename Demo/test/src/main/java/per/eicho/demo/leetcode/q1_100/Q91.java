package per.eicho.demo.leetcode.q1_100;

/**
 * <p>91. Decode Ways 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/decode-ways/">91. Decode Ways</a>
 */
public class Q91 {

    private static final class ProcessingContext {
        final String s;
        final int n;
        
        final int[] f;

        ProcessingContext(String s) {
            this.s = s;
            n = s.length();

            f = new int[n];

            final char c1 = s.charAt(0);
            if ('1' <= c1 && c1 <= '9') {
                f[0] = 1;
            }

            if (n < 2) {
                return;
            }
            assert n >= 2;
            final char c2 = s.charAt(1);
            if ((c1 == '1' && '0' <= c2 && c2 <= '9') ||
                (c1 == '2' && '0' <= c2 && c2 <= '6')) {
                f[1] = 1;
            }

            if ('1' <= c2 && c2 <= '9') {
                f[1] += f[0];
            }
        }
    }

    public int numDecodings(String s) {
        final ProcessingContext context = new ProcessingContext(s);
        
        for (int i = 2; i < context.n; i++) {
            int fi = 0;

            final char c = context.s.charAt(i);
            if ('1' <= c && c <= '9') {
                fi += context.f[i - 1];
            }

            final char c2 = context.s.charAt(i - 1);
            
            if ((c2 == '1' && '0' <= c && c <= '9') ||
                (c2 == '2' && '0' <= c && c <= '6')) {
                fi += context.f[i - 2];
            }

            context.f[i] = fi; 
        }

        return context.f[context.n - 1];
    }

    public static void main(String[] args) {
        Q91 q91 = new Q91();
        System.out.print(q91.numDecodings("123123213"));
    }
}
