package per.eicho.demo.designpattern.structural;

/**
 * <p>结构型模式 - 适配器模式</p>
 * <pre>
 *  适配器其字面意思就是让两个不兼容的东西“兼容”，能够一起使用。
 *  
 *  一个现成的组件如果不能满足接口匹配的需求，如我们需要实现A接口的组件，
 *  但现在实际只有一个实现了B接口的现有组件，并且因为诸多外部因素这个组件
 *  我们不能轻易修改（归属权不归项目组、该组件已经公开修改会违反开闭原则等）
 *  而这个组件其实能够满足A接口的要求，只是接口不匹配。那么我们可以使用适配器模式
 *  对这个现有组件进行一次“包装”，包装成A接口就可以轻易使用了。因为加包装的行为，
 *  其也被称为包装器（Wrapper）
 * </pre>
 * 
 * <pre>
 *  JDK的io包里就有典型的适配器模式的实际应用：
 *   - java.io.InputStreamReader(InputStream)
 *   - java.io.OutputStreamWriter(OutputStream)
 * </pre>
 */
final class AdapterPatternSample {
    
    


    private AdapterPatternSample() {}
}
