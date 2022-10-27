package per.eicho.demo.datastructure.unclassified.bloomfilter;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>布隆过滤器</p>
 * <pre>
 *  根据布隆过滤器的定义呢，其实一种空间高效的基于概率的数据结构，用于检测
 *  元素是否是集合里的一员。
 *    
 *    A Bloom filter is a space-efficient probabilistic data structure 
 *    that is used to test whether an element is a member of a set
 * 
 *  这么看依然有些抽象，简单举个例子就是比如我们想检测一个用户名是否用过，
 *  我们可以简单想到几个算法去检测： 
 *    1. 暴力算法就是直接线性扫描，这在数据规模庞大的系统里是不可行的。
 *    2. 维护一个有序列表，利用二分算法寻找，空间爆炸。内存无法支持维护一个大容量的有序列表。
 *    3. 维护一个单词树，相对1,2已经变得可行了一些，但几百万上千万甚至上亿用户仍然是个庞大的内存开销。
 *       用户名是唯一的，可以说对于单词树的总节点数一定是大于用户数的。这也是不可取的。
 *  那么有没有一种办法能让我们快速地，不使用那么多空间的方法能让我们检测用户名是否用过呢？
 *  有！那就是布隆过滤器。
 * </pre>
 * 
 * <h2>布隆过滤器的原理</h2>
 * <pre>
 *  布隆过滤器实现检测的原理用白话说就是多重哈希 + 比特位共用。
 *  我们都知道哈希值（也就是信息摘要Message digest）能很方便的让我们知道
 *  两个元素是否不一样（哈希值一样的元素不一定相等但哈希值不一样的元素一定不相等）
 * 
 *  布隆过滤器<b>判断一个元素不在集合里</b>就是多次计算该元素哈希值并看
 *  这些哈希值对应比特位是否<b>有任意一个未被标记</b>
 *  也就是：
 *    1. 任意比特位未被标记 --> 元素一定不在集合里
 *    2. 所有比特位都被标记 --> 元素有小概率在集合里
 *         （此时返回true表示元素在集合里存在意味着，有一定概率这个结论是错误的，也就是有误判的可能性）
 *  虽然布隆过滤器有误判的可能性，但是对元素不在集合里的判断确实没有误判的，因此
 *  可以通过巧妙的设计多重哈希方法和增大比特位来降低误判率。
 *  简单说如果能忍受误判率这个代价而换去超高效的空间利用率的话，可以选择布隆过滤器。
 * </pre>
 */
public class BloomFilter {
    
    final int capacity;
    final BitSet bitSet;
    /** 不可变的，储存HashFunction的列表  */
    final List<HashFunction> hashFunctionLists; // unmodifiable list

    BloomFilter(int nbits, HashFunction... hashFunctions) {
        Objects.requireNonNull(hashFunctions);
        if (0 == hashFunctions.length) throw new IllegalArgumentException("hashFunctions不能为空");
        // 例：10亿 bits ≈ 2^30 bits = 2^22 Bytes = 2^12 KBs = 2^2 MBs，仅约4MB的空间。
        // 你可以比较大胆地去为布隆过滤器设一个大值
        hashFunctionLists = Collections.unmodifiableList(Arrays.asList(hashFunctions));
        bitSet = new BitSet(nbits);
        this.capacity = nbits;
    }

    public boolean contains(String element) {
        // 1. 任意比特位未被标记 --> 元素一定不在集合里   --> return false
        // 2. 所有比特位都被标记 --> 元素有小概率在集合里 --> return true
        for (HashFunction hashFunction : hashFunctionLists) {
            final int idx = hashFunction.hash(element) % capacity;
            if (bitSet.get(idx) == false) return false;
        }
        return true;
    }

    public void add(String element) {
        for (HashFunction hashFunction : hashFunctionLists) {
            final int idx = hashFunction.hash(element) % capacity;
            bitSet.set(idx, true);
        }
    }

    /** 哈希方法 */
    public static interface HashFunction {
        /**
         * <p>计算哈希值</p>
         * <pre>
         *  计算给定的元素的哈希值。
         * </pre>
         * @param element 给定元素, NonNull
         * @throws NullPointerException 给定元素为Null时抛出NPE。
         */
        int hash(String element);
    }
}
