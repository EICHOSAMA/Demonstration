package per.eicho.demo.leetcode.q701_800;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import per.eicho.demo.datastructure.tree.disjoint.DisjointSetForest;

/**
 * <p>721. Accounts Merge 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/accounts-merge/">
 *   721. Accounts Merge</a>
 */
public final class Q721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 1. 1 <= accounts.length <= 1000
        // 2. 2 <= accounts[i].length <= 10
        // 3. 1 <= accounts[i][j] <= 30
        // 4. accounts[i][0] consists of English letters.
        // 5. accounts[i][j] (for j > 0) is a valid email.
        final Map<String, Map<String, Integer>> name2MailIDMap = genName2MailMap(accounts);
        final Map<String, Map<Integer, String>> name2IDMailMap = genName2IDMailMap(name2MailIDMap);
        final Map<String, DisjointSetForest> name2DS = new HashMap<>();

        for (Map.Entry<String, Map<String, Integer>> entry : name2MailIDMap.entrySet()) {
            final DisjointSetForest ds = new DisjointSetForest(entry.getValue().size());
            name2DS.put(entry.getKey(), ds);
        }

        for (List<String> account : accounts) {
            final String name = account.get(0);
            final DisjointSetForest ds = name2DS.get(name);

            for (int i = 1; i < account.size() - 1; i++) {
                final int id1 = getID(name2MailIDMap, name, account.get(i));
                final int id2 = getID(name2MailIDMap, name, account.get(i + 1));
                ds.union(id1, id2);
            }
        }

        // result outputing
        final List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, DisjointSetForest> entry : name2DS.entrySet()) {
            final String name = entry.getKey();
            final DisjointSetForest ds = entry.getValue();
            final Map<Integer, String> id2Mail = name2IDMailMap.get(name);

            final Map<Integer, List<Integer>> grouping = new HashMap<>();
            final int size = id2Mail.size();

            for (int i = 0; i < size; i++) {
                final int rep = ds.find(i);
                
                if (!grouping.containsKey(rep)) {
                    grouping.put(rep, new ArrayList<>());
                }

                final List<Integer> group = grouping.get(rep);
                group.add(i);
            }
            
            for (List<Integer> ids : grouping.values()) {
                final List<String> group = new ArrayList<>(ids.size() + 1);
                group.add(name);
                for (Integer id : ids) group.add(id2Mail.get(id));
                result.add(group);
            }
        }
        return result;
    }

    private Map<String, Map<String, Integer>> genName2MailMap(List<List<String>> accounts) {
        final Map<String, Map<String, Integer>> name2Mail = new HashMap<>();
        final Map<String, Set<String>> name2MailTemp = new HashMap<>();

        for (List<String> account : accounts) {
            final String name = account.get(0);

            if (!name2MailTemp.containsKey(name)) name2MailTemp.put(name, new HashSet<>());
            final Set<String> mailSet = name2MailTemp.get(name);
            
            for (int i = 1; i < account.size(); i++) {
                final String mail = account.get(i);
                if (!mailSet.contains(mail)) mailSet.add(mail);
            }
        }

        for (Map.Entry<String, Set<String>> entry : name2MailTemp.entrySet()) {
            final List<String> sortedMails = new ArrayList<>(entry.getValue());
            sortedMails.sort(null); // natural ordering.
            final Map<String, Integer> mail2ID = new HashMap<>();
            for (int i = 0; i < sortedMails.size(); i++) mail2ID.put(sortedMails.get(i), i);
            name2Mail.put(entry.getKey(), mail2ID);
        }
        return name2Mail;
    }

    private Map<String, Map<Integer, String>> genName2IDMailMap(Map<String, Map<String, Integer>> name2MailIDMap) {
        final Map<String, Map<Integer, String>> name2IDMailMap = new HashMap<>();

        for (String name : name2MailIDMap.keySet()) {
            final Map<String, Integer> from = name2MailIDMap.get(name);
            final Map<Integer, String> to = new HashMap<>();

            for (Map.Entry<String, Integer> entry : from.entrySet()) {
                to.put(entry.getValue(), entry.getKey());
            }

            name2IDMailMap.put(name, to);
        }

        return name2IDMailMap;
    }

    private int getID(Map<String, Map<String, Integer>> name2MailIDMap, String name, String mail) {
        return name2MailIDMap.get(name).get(mail);
    }
}
