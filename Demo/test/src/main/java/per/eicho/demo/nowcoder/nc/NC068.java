package per.eicho.demo.nowcoder.nc;

public final class NC068 {
    public int jumpFloor(int target) {
        final int[] memo = new int[target + 1];
        memo[0] = 1;
        return search(target, memo);
    }

    private int search(int target, int[] memo) {
        if (target < 0) return 0;
        if (memo[target] != 0) return memo[target];
        return memo[target] = (search(target - 1, memo) + search(target - 2, memo));
    }

    public static void main(String[] args) {
        NC068 nc068 = new NC068();
        System.out.println(nc068.jumpFloor(1)); 
        System.out.println(nc068.jumpFloor(2)); 
        System.out.println(nc068.jumpFloor(7));
        System.out.println(nc068.jumpFloor(40));
    }
}
