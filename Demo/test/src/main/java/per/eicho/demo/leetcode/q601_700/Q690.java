package per.eicho.demo.leetcode.q601_700;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>690. Employee Importance 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/employee-importance/">
 *   690. Employee Importance</a>
 */
public final class Q690 {
    private static final class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

    public int getImportance(List<Employee> employees, int id) {
        // 1. 1 <= employees.length <= 2000
        // 2. 1 <= employees[i].id <= 2000
        // 3. All employees[i].id are unique.
        // 4. -100 <= employees[i].importance <= 100
        // 5. One employee has at most one direct leader and may have several subordinates.
        // 6. The IDs in employees[i].subordinates are valid IDs.        
        final Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) map.put(employee.id, employee);
        return getImportance(id, map);
    }

    private int getImportance(int id, Map<Integer, Employee> map) {
        final Employee employee = map.get(id);
        int importance = employee.importance;
        for (int suborindateID : employee.subordinates) importance += getImportance(suborindateID, map);
        return importance;
    }
}
