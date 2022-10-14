package per.eicho.demo.designpattern.behavioral;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>行为模式 - 中介者模式</p>
 * <pre>
 *  中介者，Mediator。中介日常生活中基本就是多方人或势力中起协调作用的。
 *  
 *    Mediator is a behavioral design pattern that reduces coupling 
 *    between components of a program by making them communicate indirectly, 
 *    through a special mediator object.
 *  
 *  中介者模式提供了一种机制，通过这个机制使各个程序各个组件之间的不再显式依赖，
 *  这在后端里其实不算常见，
 *    
 *    The most popular usage of the Mediator pattern in Java code is 
 *    facilitating communications between GUI components of an app. 
 *    The synonym of the Mediator is the Controller part of MVC pattern.
 * 
 *  中介者模式的主要应用是协调GUI程序的各个GUI组件之间的交互，也就是MVC里的Controller。
 *  有人也认为项目中常用的MQ（消息队列）技术属于中介者模式，笔者不太认同这个观点。
 *  消息队列更加复合消费者和生产者模式，而这个模式不属于Gof的23种设计模式。
 * </pre>
 */
@GoFDesignPattern
final class MediatorPatternSample {
    
    /* 侧重GUI的设计模式，暂无样例代码 */

    private MediatorPatternSample() {}
}
