package per.eicho.demo.leetcode.q1401_1500;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>1436. Destination City 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/destination-city/">
 *   1436. Destination City</a>
 */
public final class Q1436 {
    public String destCity(List<List<String>> paths) {
        // 1. 1 <= paths.length <= 100
        // 2. paths[i].length == 2
        // 3. 1 <= cityAi.length, cityBi.length <= 10
        // 4. cityAi != cityBi
        // 5. All strings consist of lowercase and uppercase English letters and the space character.        
        final Set<String> cities = new HashSet<>();
        for (List<String> path : paths) cities.add(path.get(1));
        for (List<String> path : paths) cities.remove(path.get(0));
        return cities.iterator().next();
    }
}
