package per.eicho.demo.leetcode.q801_900;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * <p>895. Maximum Frequency Stack 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-frequency-stack/">
 *   895. Maximum Frequency Stack</a>
 */
@SuppressWarnings("unused")
public final class Q895 {
    private static final class FreqStack {

        // <num, freq>
        final Map<Integer, Integer> num2FreqMap;

        // <freq, Stack<Same Freq Number>>
        final Map<Integer, Stack<Integer>> group;
        int maxFreq;

        /** constructs an empty frequency stack. */
        public FreqStack() {
            num2FreqMap = new HashMap<>();
            group = new HashMap<>();
        }
        
        /** pushes an integer val onto the top of the stack. */
        public void push(int val) {
            final int iFreq = num2FreqMap.getOrDefault(val, 0) + 1;
            num2FreqMap.put(val, iFreq);
            if (iFreq > maxFreq) maxFreq = iFreq;
    
            group.computeIfAbsent(iFreq, key -> new Stack<>()).push(val);
        }
        
        /** 
         * removes and returns the most frequent element in the stack.
         * If there is a tie for the most frequent element, 
         * the element closest to the stack's top is removed and returned. 
         */
        public int pop() {
            // It is guaranteed that there will 
            // be at least one element in the stack before calling pop.
            final int num = group.get(maxFreq).pop();
            num2FreqMap.put(num, num2FreqMap.get(num) - 1); // maintain freq info.
            if (group.get(maxFreq).size() == 0) maxFreq--; // maintain max freq info.
            return num;
        }
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(1);
        freqStack.push(0);
        freqStack.push(0);
        freqStack.push(1);
        freqStack.push(5);
        freqStack.push(4);
        freqStack.push(1);
        freqStack.push(5);
        freqStack.push(1);
        freqStack.push(6);
        
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}
