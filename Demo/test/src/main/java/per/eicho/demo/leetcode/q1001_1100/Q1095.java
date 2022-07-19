package per.eicho.demo.leetcode.q1001_1100;

import java.util.Arrays;

/**
 * <p>1095. Find in Mountain Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-in-mountain-array/">
 *   1095. Find in Mountain Array</a>
 */
public final class Q1095 {

    private interface MountainArray {
        int get(int index);
        int length();
    }

    int len;
    private int[] cache;
    MountainArray mountainArr;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        // 1. 3 <= mountain_arr.length() <= 10^4
        // 2. 0 <= target <= 10^9
        // 3. 0 <= mountain_arr.get(index) <= 10^9
        len = mountainArr.length();
        cache = new int[len];
        Arrays.fill(cache, -1);
        this.mountainArr = mountainArr;
        return binarySearch0(0, len - 1, target);
    }

    private int get(int index) {
        if (cache[index] == -1) cache[index] = mountainArr.get(index);
        return cache[index];
    }

    private int binarySearchL(final int l, final int r, final int target) {
        if (l > r) return -1;
        final int numL = get(l);
        final int numR = get(r);

        if (l == r) return numL == target ? l : -1;
        if (target < numL || numR < target) return -1;

        if (r - l == 1) {
            return numR == target ? r : (numL == target ? l : -1);
        }

        // mono increasing
        // assert numL < numR
        final int mid = (l + r + 1) / 2;
        final int numMid = get(mid);        
        if (target == numMid) return mid;

        if (target < numMid) return binarySearchL(l, mid - 1, target);
        return binarySearchL(mid + 1, r, target);
    }

    private int binarySearchR(final int l, final int r, final int target) {
        if (l > r) return -1;
        final int numL = get(l);
        final int numR = get(r);

        if (l == r) return get(l) == target ? l : -1;
        if (target > numL || numR > target) return -1;
        if (r - l == 1) {
            return numR == target ? r : (numL == target ? l : -1);
        }

        // mono descreasing
        // assert numMid > numR
        final int mid = (l + r + 1) / 2;
        final int numMid = get(mid);         
        if (target == numMid) return mid;

        if (target > numMid) return binarySearchR(l, mid - 1, target);
        return binarySearchR(mid + 1, r, target);
    }

    private int binarySearch0(final int l, final int r, final int target) {
        final int numL = get(l);
        final int numR = get(r);

        if (l == r) return numL == target ? l : -1;
        if (target < numL && target < numR) return -1;

        // ^ array
        // assert r - l >= 2 (len >= 3)

        final int mid = (l + r + 1) / 2;
        final int numMid = get(mid);

        if (r - l == 2) {
            final int searchLeft = binarySearchL(l, mid, target);
            if (searchLeft != -1) return searchLeft;
            return binarySearchR(mid, r, target);
        }

        final int lMid = mid - 1;
        final int numLMid = get(lMid);

        if (numLMid < numMid) {
            if (numMid == target) return mid;
            final int searchLeft = binarySearchL(l, lMid, target);
            if (searchLeft != -1) return searchLeft;

            final int rMid = mid + 1;
            final int numRMid = get(rMid);
            if (numMid < numRMid) return binarySearch0(mid, r, target);
            return binarySearchR(mid, r, target);
        } else {
            // numLMid > numMid
            final int searchLeft = binarySearch0(l, mid, target);
            if (searchLeft != -1) return searchLeft;
            return binarySearchR(lMid, r, target);
        }
    }
}
