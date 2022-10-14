package per.eicho.demo.designpattern.structural;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>结构型模式 - 外观模式</p>
 * <pre>
 *  外观，Facade。外观外观就是从外部的视角来观察到的东西。
 * 
 *  好比庐山的“横看成岭侧成峰”，软件里对于“复杂系统”而言也可以横看成岭侧成峰。什么意思呢？
 *  对于老开发，IDE的perspective layout功能应该不陌生，这是集成开发环境（IDE）的基础特性。
 *  
 *  拿Eclipse举例就是你可以在你的界面里添加各种你想要的窗口，并随意指定这些窗口的大小、位置。
 *  Eclipse默认设置了几种perspective layout可以供你在开发、测试、部署时使用。你也可以自定义
 *  你自己的layout，这就和我们的facade模式很类似。
 * 
 *  外观模式，就是“复杂系统”对外提供的一种高层API来操作复杂系统内部的各大组件。这个高层API对其
 *  客户类（Client）是屏蔽了底层API的操作复杂性的。通常外观模式所包装的各大组件都是<b>公开的</b>
 *  <b>独立的</b>对外提供了复杂且功能丰富的底层API，它们能独立服务它们的客户类。外观模式也是它们服务的对象。
 * 
 *  GoF设计模式书里的编译器例子就很好，编译器系统使用了Scanner、Parser、CodeGenerator等等组件。
 *  这些组件是拥有独立服务它们客户类的能力，比如Parser可以单独服务于想获取语法树数据结构的客户、
 *  CodeGenerator可以单独服务于想生成代码的客户。而编译器系统就是对这些组件的一整个包装起来的“门面”。
 *  因为大部分客户他其实不关心底层组件，他们或许仅仅想编译Java文件到Class文件而已。因此提供一个
 *  高层的API是有必要的，这就是外观模式做的事。
 * 
 *  既然提供一个外观是提供，那么可不可以提供多个呢？可以试想Eclipse的perspective layout功能里
 *  一个个layout是不是类似多个外观呢？
 * </pre>
 * 
 * <pre>
 *   除开perspective layout和编译器的类比，在Spring框架、现代微服务所构成的分布式系统也可以窥见
 *   外观模式的影子。
 *    - Spring伞下拥有多个核心模块（Module），Core模块、AOP模块、Beans模块等等。
 *      这些核心模块的不同组合构成了如Spring Cloud、Sprint Data JPA等顶级项目（Spring Projects）。
 *      这些顶级项目、顶级项目的子项目都有Facade的影子在里面。
 *    - 企业系统使用微服务架构，旗下多个不同的数据服务的组合可以通过API网关暴露给外部客户
 *      （App调用、前端调用、三方开发者调用），这些个暴露的接口也有Facade模式的影子在里面。
 * </pre>
 */
@GoFDesignPattern
final class FacadePatternSample {

    static class Parser {
        String[] parse(String code) {
            return code.split(" ");
        }
    }

    static class CodeGenerator {
        String generate(String[] nodes) {
            final StringBuilder sb = new StringBuilder();

            int i = 0;
            for (final int n = nodes.length - 1; i < n; i++) {
                sb.append(nodes[i]).append('-');
            }
            sb.append(nodes[i]);

            return sb.toString();
        }
    }

    /** Compiler是Parser、CodeGenerator组的一个外观 */
    static class Compiler {

        final Parser parser;
        final CodeGenerator codeGenerator;

        Compiler() {
            parser = new Parser();
            codeGenerator = new CodeGenerator();
        }

        String compile(String code) {
            return codeGenerator.generate(parser.parse(code));
        }
    }

    public static void main(String[] args) {
        // 1. 懂行的客户可以分开单独使用Parser和CodeGenerator类
        final CodeGenerator codeGenerator = new CodeGenerator();
        System.out.println(codeGenerator.generate(new String[] {"测试", "单独使用", "CodeGenerator"}));

        // 2. 不懂行的客户则可以使用它们的外观Compiler开箱即用
        final Compiler compiler = new Compiler();
        System.out.println(compiler.compile("测试 Compiler"));
    }
    
    private FacadePatternSample() {}
}
