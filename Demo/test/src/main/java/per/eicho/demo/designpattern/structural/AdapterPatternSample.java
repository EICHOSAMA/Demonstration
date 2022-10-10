package per.eicho.demo.designpattern.structural;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

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
 *  JDK就有适配器模式的实际应用：
 *   - java.io.InputStreamReader(InputStream)
 *   - java.io.OutputStreamWriter(OutputStream)
 *   - java.util.Arrays#asList()
 * </pre>
 * 
 * <pre>
 *  总结就是对于现有的类，当其不满足客户类的接口需求但其又能满足客户类的实际计算需求时
 *  可以使用适配器模式对现有类进行一次包装，对客户隐藏低层具体实现类且包装成客户类希望
 *  使用的接口就可以使本不能兼容的两个类协同工作且不用新增重复的代码。
 * </pre>
 */
@GoFDesignPattern
final class AdapterPatternSample {
    
    /** 
     * 难以修改或者控制权不在自己手里的类
     */
    final static class ThirdPartyUtils {

        /**
         * <p>日语（加算・カサン・kasan）方法</p>
         * <pre>
         *  对给定的值a, b，返回其和
         * </pre>
         */
        public static int kasan(int a, int b) {
            return a + b;
        }
    }

    /** 
     * 客户类，
     * 客户类希望它能使用实现了AddOperation接口的组件来帮助其完成加法运算 
     */
    final static class Client {
        final AddOperation component;

        Client(AddOperation component) {
            this.component = component;
        }

        void doSomething() {
            System.out.println("计算结果:" + component.add(2, 5));
        }
    }

    final static class ThirdPartyUtilsWrapper implements AddOperation {

        @Override
        public int add(int a, int b) {
            return ThirdPartyUtils.kasan(a, b);
        }
        
    }    

    static interface AddOperation {
        /** 对给定的两个数返回其和 */
        public int add(int a, int b);
    }

    public static void main(String[] args) {
        final Client client = new Client(new ThirdPartyUtilsWrapper());
        client.doSomething();
    }

    private AdapterPatternSample() {}
}
