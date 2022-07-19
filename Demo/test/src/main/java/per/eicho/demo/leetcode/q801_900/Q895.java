package per.eicho.demo.leetcode.q801_900;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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

        private static final class Info {
            final int num;
            final LinkedList<Integer> ids;

            Info(int num) {
                this.num = num;
                ids = new LinkedList<>();
            }

            void append(int idx) {
                ids.addLast(idx);
            }

            int size() {
                return ids.size();
            }
        }

        int id = 0; // sort key.
        int maxSize = 0;
        // <Len, TreeMap<Id, Info>>
        final Map<Integer, TreeMap<Integer, Info>> map; 
        // <Num, Info>
        final Map<Integer, Info> num2InfoMap;

        /** constructs an empty frequency stack. */
        public FreqStack() {
            map = new HashMap<>();
            num2InfoMap = new HashMap<>();
        }
        
        /** pushes an integer val onto the top of the stack. */
        public void push(int val) {
            final int newSize;
            final Info info;
            if (!num2InfoMap.containsKey(val)) {
                info = new Info(val);
                info.append(id++);
                num2InfoMap.put(val, info);
                
                newSize = info.size();
            } else {
                info = num2InfoMap.get(val);
                final int oldSize = info.size();
                map.get(oldSize).remove(info.ids.peekLast());
                
                info.append(id++);
                newSize = info.size();
            }

            if (!map.containsKey(newSize)) map.put(newSize, new TreeMap<>());
            final Map<Integer, Info> sameSizeId2InfoTreeMap = map.get(newSize);
            sameSizeId2InfoTreeMap.put(info.ids.peekLast(), info);
            if (newSize > maxSize) maxSize = newSize;
        }
        
        /** 
         * removes and returns the most frequent element in the stack.
         * If there is a tie for the most frequent element, 
         * the element closest to the stack's top is removed and returned. 
         */
        public int pop() {
            // It is guaranteed that there will 
            // be at least one element in the stack before calling pop.
            final TreeMap<Integer, Info> sameSizeId2InfoTreeMap = map.get(maxSize);

            final Integer id = sameSizeId2InfoTreeMap.lastKey();
            final Info info = sameSizeId2InfoTreeMap.pollLastEntry().getValue();

            final int num = info.num;
            final int oldSize = info.size();
            info.ids.pollLast();
            final int newSize = info.size();

            if (sameSizeId2InfoTreeMap.size() == 0) {
                map.remove(oldSize);
                maxSize = newSize;
            }

            System.out.println("pop:" + num + " MaxSize:" + maxSize);

            if (newSize == 0) {
                num2InfoMap.remove(num);
                return num;
            }

            // maintain new same size map.
            if (!map.containsKey(newSize)) map.put(newSize, new TreeMap<>());
            final Map<Integer, Info> newSameSizeId2InfoTreeMap = map.get(newSize);
            newSameSizeId2InfoTreeMap.put(info.ids.peekLast(), info);
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
