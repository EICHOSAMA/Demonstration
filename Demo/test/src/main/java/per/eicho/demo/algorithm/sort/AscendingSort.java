package per.eicho.demo.algorithm.sort;

/**
 * 升序排序
 * @author toranekojp
 */
public interface AscendingSort {
    /**
     * <p>排序</p>
     * 
     * <pre>
     *  对给定数组进行<b>升序</b>排序，破坏性排序，会改变给定数组内的数据。
     * 
     *  <b>不对线程安全性做要求。</b>
     * </pre>
     * 
     * @param nums 目标数组，non-null。
     * @throws NullPointerException 如果nums为null。抛出此异常。
     */
    void sort(int[] nums);
}