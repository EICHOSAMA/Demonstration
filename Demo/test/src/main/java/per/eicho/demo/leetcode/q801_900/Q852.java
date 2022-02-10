package per.eicho.demo.leetcode.q801_900;

/**
 * <p>852. Peak Index in a Mountain Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/peak-index-in-a-mountain-array/">852. Peak Index in a Mountain Array</a>
 */
public final class Q852 {
    public int peakIndexInMountainArray(int[] arr) {
        // 3 <= arr.length <= 10^4
        // 0 <= arr[i] <= 10^6
        // arr is guaranteed to be a mountain array.        
        final int n = arr.length;

        int l = 0, r = n - 1;

        while (true) {
            final int mid = (l + r) / 2;
            final int numMid = arr[mid];
            
            // /
            if (arr[mid - 1] < numMid && numMid < arr[mid + 1]) {
                l = mid;
                continue;
            }

            // \
            if (arr[mid - 1] > numMid && numMid > arr[mid + 1]) {
                r = mid;
                continue;
            }

            return mid;
        }
    }

    public static void main(String[] args) {
        Q852 q852 = new Q852();
        System.out.println(q852.peakIndexInMountainArray(new int[]{0, 1, 0}));
        System.out.println(q852.peakIndexInMountainArray(new int[]{0, 2, 1, 0}));
        System.out.println(q852.peakIndexInMountainArray(new int[]{0, 10, 5, 2}));
        System.out.println(q852.peakIndexInMountainArray(new int[]{0, 10, 11, 10, 10, 10, 5, 2}));
        
    }
}
