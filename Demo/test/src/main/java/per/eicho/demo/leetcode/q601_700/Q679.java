package per.eicho.demo.leetcode.q601_700;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>679. 24 Game 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/24-game/">
 *   679. 24 Game</a>
 */
public final class Q679 {

    private static final class FractionNum {
        
        /** 分子（ぶんし） */
        final int numerator;

        /** 分母（ぶんぼ） */
        final int denominator; //

        FractionNum(int num) { 
            this.numerator = num;
            this.denominator = 1;
        }

        FractionNum(int numerator, int denominator) {
            if ((numerator | denominator) < 0) {
                if (numerator > 0) {
                    numerator *= -1;
                    denominator *= -1;
                }
            } else {
                numerator = Math.abs(numerator);
                denominator = Math.abs(denominator);
            }

            final int gcd = gcd(Math.abs(numerator), Math.abs(denominator));

            if (numerator != 0) {
                this.numerator = numerator / gcd;
                this.denominator = denominator / gcd;
            } else {
                this.numerator = 0;
                this.denominator = 1;
            }

        }

        FractionNum plus(FractionNum num) {
            final int newNumerator = 
                this.numerator * num.denominator + 
                num.numerator * this.denominator;
            final int newDenominator = this.denominator * num.denominator;
            return new FractionNum(newNumerator, newDenominator);
        }

        FractionNum minus(FractionNum num) {
            final int newNumerator = 
                this.numerator * num.denominator - 
                num.numerator * this.denominator;
            final int newDenominator = this.denominator * num.denominator;
            return new FractionNum(newNumerator, newDenominator);
        }

        FractionNum product(FractionNum num) {
            final int newNumerator = 
                this.numerator * num.numerator;
            final int newDenominator = this.denominator * num.denominator;
            return new FractionNum(newNumerator, newDenominator);
        }

        FractionNum division(FractionNum num) {
            final int newNumerator = 
                this.numerator * num.denominator;
            final int newDenominator = this.denominator * num.numerator;
            return new FractionNum(newNumerator, newDenominator);
        }

        static int gcd(int a, int b) {
            int temp;
            while (b != 0) {
                temp = b;
                b = a % b;
                a = temp;
            }
            return a;            
        }

        @Override
        public boolean equals(Object arg0) {
            final FractionNum num = (FractionNum)arg0;
            if (num == this) return true;
            final boolean isEqual = 
                this.denominator * num.numerator == 
                this.numerator * num.denominator;
            return isEqual;
        }

        public boolean isEqual(int num) {
            return this.numerator == num * this.denominator;
        }
    }

    private static final FractionNum target = new FractionNum(24);
    int[] cards;
    boolean[] marks;

    public boolean judgePoint24(int[] cards) {
        // 1. cards.length == 4
        // 2. 1 <= cards[i] <= 9
        this.cards = cards;
        Arrays.sort(cards);
        marks = new boolean[4];
        FractionNum[] nums = new FractionNum[4];
        for (int i = 0; i < 4; i++)  nums[i] = new FractionNum(cards[i]);

        final Map<Integer, Set<FractionNum>> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            map.put(cards[i], new HashSet<>());
            map.get(cards[i]).add(nums[i]);

            for (int j = i + 1; j < 4; j++) {
                final int id = cards[i] * 10 + cards[j];
                if (map.containsKey(id)) continue;
                map.put(id, generateNums(nums[i], nums[j]));
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = j + 1; k < 4; k++) {
                    final Set<FractionNum> set = new HashSet<>();
                    final int id = cards[i] * 100 + cards[j] * 10 + cards[k]; 
                    set.addAll(generateNums(map.get(genID(i, j)), nums[k]));
                    set.addAll(generateNums(map.get(genID(i, k)), nums[j]));
                    set.addAll(generateNums(map.get(genID(j, k)), nums[i]));
                    map.put(id, set);
                }
            }
        }

        // check 2 - 2 pattern.
        for (int i = 0; i < 4; i++) {
            marks[i] = true;
            final int a = cards[i];
            for (int j = i + 1; j < 4; j++) {
                marks[j] = true;
                final int b = cards[j];
                final int id = a * 10 + b;
                final int otherId = getOtherId();
                if (valid(map.get(id), map.get(otherId), target)) return true;
                marks[j] = false;
            }

            marks[i] = false;
        }

        // check 3 -1 pattern
        for (int i = 0; i < 4; i++) {
            marks[i] = true;
            for (int j = i + 1; j < 4; j++) {
                marks[j] = true;
                for (int k = j + 1; k < 4; k++) {
                    marks[k] = true;
                    final int id = cards[i] * 100 + cards[j] * 10 + cards[k];
                    final int otherId = getOtherId();
                    if (valid(map.get(id), map.get(otherId), target)) return true;
                    marks[k] = false;
                }
                marks[j] = false;
            }
            marks[i] = false;
        }

        return false;
    }
    
    private boolean valid(Set<FractionNum> set1, Set<FractionNum> set2, FractionNum target) {
        for (FractionNum num1 : set1) {
            for (FractionNum num2 : set2) {
                if (target.equals(num1.plus(num2))) return true;
                if (target.equals(num1.minus(num2))) return true;
                if (target.equals(num2.minus(num1))) return true;
                if (target.equals(num1.product(num2))) return true;
                if (!num2.isEqual(0) ) {
                    if (target.equals(num1.division(num2))) return true;
                }

                if (!num1.isEqual(0)) {
                    if (target.equals(num2.division(num1))) return true;
                }
            }
        }

        return false;
    }

    private Set<FractionNum> generateNums(FractionNum a, FractionNum b) {
        final Set<FractionNum> nums = new HashSet<>();
        
        nums.add(a.plus(b));
        nums.add(a.minus(b));
        nums.add(b.minus(a));
        nums.add(a.product(b));
        nums.add(b.product(a));
        if (!b.isEqual(0)) nums.add(a.division(b));
        if (!a.isEqual(0)) nums.add(b.division(a));

        return nums;
    }

    private Set<FractionNum> generateNums(Set<FractionNum> aSet, FractionNum b) {
        final Set<FractionNum> nums = new HashSet<>();
        for (FractionNum num : aSet) {
            nums.addAll(generateNums(num, b));
        }
        return nums;
    }

    private int getOtherId() {
        int id = 0;
        for (int i = 0; i < 4; i++) {
            if (marks[i]) continue;
            id *= 10;
            id += cards[i];
        }
        return id;
    }

    private int genID(int i, int j) {
        return cards[i] * 10 + cards[j];
    }

    public static void main(String[] args) {
        Q679 q679 = new Q679();
        System.out.println(q679.judgePoint24(new int[]{4, 1, 7, 8})); // true
        System.out.println(q679.judgePoint24(new int[]{4, 1, 5, 6})); // true
        System.out.println(q679.judgePoint24(new int[]{1, 2, 3, 6})); // true
        System.out.println(q679.judgePoint24(new int[]{3, 7, 7, 9})); // true
        System.out.println(q679.judgePoint24(new int[]{1, 5, 5, 5})); // true
    
    }
}
