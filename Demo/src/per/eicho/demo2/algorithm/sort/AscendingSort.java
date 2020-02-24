package per.eicho.demo2.algorithm.sort;

/**
 * 升序排序
 * @author toranekojp
 */
public interface AscendingSort {
    /**
     * 对给定数组进行升序排序，破坏性排序，会改变给定数组内的数据。</br>
     * 
     * <b>不对线程安全性做要求。</b>
     * @param nums 目标数组，non-null。
     * @throws NullPointerException 如果nums为null。则抛出此异常。
     */
    void sort(int[] nums);
}
