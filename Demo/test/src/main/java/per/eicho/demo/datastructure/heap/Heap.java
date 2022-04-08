package per.eicho.demo.datastructure.heap;

interface Heap {
    
    /**
     * <p>堆的插入操作</p>
     * 
     * <pre>向堆里新插入给定的一个数字</pre>
     * 
     * @param num 给定数字
     */
    void insert(int num);

    /**
     * <p>窥视堆顶元素</p>
     * 
     * <pre>
     *  和主流peek方法相同，窥视堆顶元素并返回给调用方，
     *  <b>不改变堆的结构</b>
     * </pre>
     */
    int peek();

    /**
     * <p>弹出栈顶元素</p>
     * 
     * <pre>
     *  弹出栈顶元素并返回给调用端。
     * </pre>
     */
    int pop();

    /**
     * <p>[Inspection] 查看堆的大小</p>
     * 
     * <pre>
     *  向调用方返回当前堆内元素个数。
     * </pre>
     * 
     * @return 堆内元素个数, 不小于0 (>= 0)
     */
    int size();

    /**
     * <p>[Inspection] 查看堆是否为空</p>
     * 
     * <pre>
     *  向调用方返回当前堆是否为空（堆内元素个数为0）。
     * </pre>
     * 
     * @return 元素个数为0时，返回true。除此以外false
     */
    boolean isEmpty();
}
