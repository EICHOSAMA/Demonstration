package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1491. Average Salary Excluding the Minimum and Maximum Salary 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/">
 *   1491. Average Salary Excluding the Minimum and Maximum Salary</a>
 */
public final class Q1491 {
    public double average(int[] salary) {
        // 1. 3 <= salary.length <= 100
        // 2. 1000 <= salary[i] <= 10^6
        // 3. All the integers of salary are unique.        
        final int n = salary.length;
        int sum = 0;
        int min = salary[0];
        int max = salary[0];
        for (int s : salary) {
            sum += s;
            if (s < min) {
                min = s;
            } else if (s > max) {
                max = s;
            }
        }

        return ((double)(sum - min - max)) / (n - 2);
    }
}
