package per.eicho.demo.leetcode.q901_1000;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>929. Unique Email Addresses 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/unique-email-addresses/">929. Unique Email Addresses</a>
 */
public final class Q929 {
    public int numUniqueEmails(String[] emails) {
        // 1. 1 <= emails.length <= 100
        // 2. 1 <= emails[i].length <= 100
        // 3. emails[i] consist of lowercase English letters, '+', '.' and '@'.
        // 4. Each emails[i] contains exactly one '@' character.
        // 5. All local and domain names are non-empty.
        // 6. Local names do not start with a '+' character.
        // 7. Domain names end with the ".com" suffix.
        final Map<String, Set<String>> map = new HashMap<>();   
        for (String email : emails) {
            final String[] emailParts = email.split("@");

            final String localName = emailParts[0].split("\\+")[0].replaceAll("[.]", "");
            final String domain = emailParts[1];
            // System.out.println(localName + "@" + domain);

            if (!map.containsKey(domain)) {
                map.put(domain, new HashSet<>());
            }

            map.get(domain).add(localName);
        }

        int result = 0;
        for (Set<String> value : map.values()) {
            result += value.size();
        }
        return result;
    }

    public static void main(String[] args) {
        Q929 q929 = new Q929();
        System.out.println(q929.numUniqueEmails(new String[]{"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"}));
    }
}
