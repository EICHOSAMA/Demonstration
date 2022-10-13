package per.eicho.demo.designpattern.structural;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>结构型模式 - 代理模式</p>
 * <pre>
 *  代理，Proxy。代理代理，代为处理。代理的例子有很多：
 *    - 公司股东可能会选取代理人帮他们在重要决议上投票。
 *    - 大国之间通过扶持代理国发动对敌对国的战争，即代理人战争，避免直接卷入战争。
 *    - 普通平头老百姓，想购买他国产品，也有走代购渠道的。
 *  那么放到IT的世界里，代理是个什么东西呢？
 *    - 我有一个请求想发到终端服务器，但因为安全原因这个终端服务器并不想暴露自己的位置。
 *      那么终端服务器搞一个中间服务器转发客户端请求和服务器的回复，这个中间服务器就叫“代理服务器” （Proxy Server）
 *    - 日服DNF封禁海外IP登陆的账号，但我依然想玩日服。但直接用日本以外的IP地址直连会被封号。
 *      那么我们也可以搞一个中间服务器转发客户端请求和服务器的恢复，这个中间服务器也叫“代理服务器”
 *    - AOP，面向切片编程。Spring AOP利用了Java的Dynamic Proxy做接口代理以及CGLib做实体类代理。
 *    - RPC以及高消耗实例的占位代理（Placeholder）等等...
 * </pre>
 * 
 * <pre>
 *   代理模式与适配器模式、装饰模式有些类似。适配器模式主要用于不同接口之间适配用、
 *   装饰模式则是主要用于为原有类提供功能组合避免子类爆炸式增长。
 *   而代理模式则主要为被代理的类提供或是安全方面（参考代理服务器例）
 *   或是性能方面（网页图片加载的Placeholder例）等等的保障
 * </pre>
 */
@GoFDesignPattern
final class ProxyPatternSample {

    interface AddOperation {
        int add(int a, int b);
    }

    static class AddOperationImpl implements AddOperation {
        @Override
        public int add(int a, int b) { return a + b; }
    }

    static class AddOperationHandler implements InvocationHandler {

        private final AddOperation addOperation;
        public AddOperationHandler(AddOperation addOperation) { this.addOperation = addOperation; }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Add Operation Start.");
            Object result = method.invoke(addOperation, args);
            System.out.println("Add Operation End.");
            return result;
        }
    }

    public static void main(String[] args) {
        AddOperationHandler addOperationHandler = new AddOperationHandler(new AddOperationImpl());

        AddOperation addOperation = (AddOperation) Proxy.newProxyInstance(
            AddOperation.class.getClassLoader(), 
            new Class[] { AddOperation.class }, 
            addOperationHandler);

        System.out.println(addOperation.add(1, 2));
        System.out.println(addOperation.getClass());
    }
   
    private ProxyPatternSample() {}
}
