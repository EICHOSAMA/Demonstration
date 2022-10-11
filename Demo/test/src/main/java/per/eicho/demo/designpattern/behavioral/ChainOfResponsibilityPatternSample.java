package per.eicho.demo.designpattern.behavioral;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>行为模式 - 责任链</p>
 * <pre>
 *  责任链，Chain Of Responsibility，CoR是非常重要的一种设计模式。
 *  无论前后端都非常常见。后端的请求Filter、异常处理、前端的GUI事件响应链等等...
 *  是必须要掌握的设计模式之一。
 * </pre>
 * 
 * <pre>
 *  责任链主要思想是当一个“请求”（亦或是“事件”，“消息”...）需要被多个“部门”处理时，
 *  通过串联的方式把这些“部门”连接起来，每个“部门”都能在前一个“部门”处理后有机会能继续
 *  处理这个“请求”。
 *  
 *  当你不使用责任链时你可能面对这种代码
 *    <code>
 *      void bussinessProcess(Event event) {
 *        if (componentA.handle(event)) return;
 *        if (componentB.handle(event)) return;
 *        ...
 *        if (componentX.handle(event)) return;
 *        throw new IllegalArgumentException("无法处理的请求");
 *      }
 *    </code>
 *  
 *  当你使用责任链时你只需要
 *    <code>
 *      void bussinessProcess(Event event) {
 *        if (corHead.handle(event)) return;
 *        throw new IllegalArgumentException("无法处理的请求");
 *      }
 *    </code>
 * 
 *  耦合度以及代码可读性大幅提升，修改也变得容易，
 *  你可以很容易添加或删减责任链的节点。
 * </pre>
 */
@GoFDesignPattern
final class ChainOfResponsibilityPatternSample {

    /** 客人 */
    static final class Customer {
        final String name; // 姓名
        final int age; // 年龄
        final boolean gender; // 性别，false：男性，true：女性

        Customer(String name, int age, boolean gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }
    }

    static interface CustomerFilter {
        /** 检查客人是否复合某项要求 */
        boolean doFilter(Customer customer);
    }
    
    static abstract class AbstractFilter implements CustomerFilter {
        private AbstractFilter nextFilter;

        public void setNextFilter(AbstractFilter nextFilter) {
            this.nextFilter = nextFilter;
        }

        @Override
        public boolean doFilter(Customer customer) {
            if (!check(customer)) return false;
            if (null == nextFilter) return true;
            return nextFilter.doFilter(customer);
        }

        protected abstract boolean check(Customer customer);
    }

    static final class AgeFilter extends AbstractFilter {

        /** 年龄限制 */
        final int ageLimit;

        AgeFilter(int ageLimit) {
            this.ageLimit = ageLimit;
        }

        @Override
        protected boolean check(Customer customer) {
            return customer.age <= ageLimit;
        }
    }

    static final class NameFilter extends AbstractFilter {

        /** 姓名限制限制（黑名单） */
        final String name;

        NameFilter(String name) {
            this.name = name;
        }

        @Override
        protected boolean check(Customer customer) {
            return !name.equals(customer.name);
        }
    }

    public static void main(String[] args) {
        final AbstractFilter filterChain = new AgeFilter(25);
        filterChain.setNextFilter(new NameFilter("张三"));
        
        final Customer customer1 = new Customer("张三", 26, false);
        System.out.println(filterChain.doFilter(customer1));
        
        final Customer customer2 = new Customer("张三", 20, false);
        System.out.println(filterChain.doFilter(customer2));

        final Customer customer3 = new Customer("张四", 20, false);
        System.out.println(filterChain.doFilter(customer3));
        
    }

    private ChainOfResponsibilityPatternSample() {}
}
