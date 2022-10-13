package per.eicho.demo.designpattern.structural;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>行为模式 - 装饰</p>
 * <pre>
 *  装饰，Decorator。装饰意味着在某样东西原有的基础上再额外点缀点其他的。
 *  同样的，装饰模式也是在某类原有的基础上<b>给这个类额外增加一些特性</b>的一种设计模式。
 *  
 *  哦，“给类添加新功能”，那问题来了，我干嘛不在原有类上直接添加呢？
 *  又或是为什么不添加子类去新增功能呢？
 * </pre>
 * 
 * <pre>
 *  Q：为何不在原有类基础上添加新功能？
 *  A：一个类的功能（职责）越多越不容易维护，且难以使用。类的客户使用这个类时可能会
 *     面对很多不需要的功能。
 * 
 *  Q：为什么不添加子类去新增功能？
 *  A：装饰模式是添加子类（继承机制）去新增功能的一种替代方案。相比继承机制装饰模式更加灵活。
 *     对于基础类A。Team1需要一些功能做了子类AB, Team2需要一些功能做了子类AC，Team3需要Team1和Team2的功能，
 *     做了子类ABC。这不是开玩笑，比如GUI应用做TextView，某个组做了脏话检测的DirtyWordDetectionTextView，
 *     某组做了敏感词检测的SensitiveWordDetectionTextView。
 *     某个组说我们都要，于是做了DirtyAndSensitiveWordDetectionTextView。
 *     好嘛，叠buff呢，这么长。可以预见的是一旦功能集合的数量增加，
 *     从这些功能集合中能组合出的子类数量将指数级暴增。届时系统将变得无比复杂。
 *     而使用装饰模式则能解决这个问题，通过一层层类似AOP的“透明”包装式的可以很轻易
 *     得增加新功能。
 * </pre>
 */
@GoFDesignPattern
final class DecoratorPatternSample {
    
    static abstract class AbstractTextView {        
        abstract void setText(String text);
        abstract void showText(); 
    }

    static class ConcreteTextView extends AbstractTextView {
        String text;

        @Override
        public void setText(String text) {
            this.text = text;
        }

        @Override
        public void showText() {
            System.out.println("当前文本：[" + text + "]");
        }
    }

    static abstract class TextViewDecorator extends AbstractTextView {
        final AbstractTextView textView;
        TextViewDecorator(AbstractTextView textView) {
            this.textView = textView;
        }

        @Override
        void showText() {
            textView.showText();
        }        
    }

    /** 脏话检测 */
    static final class DirtyWordDetectionTextViewDecorator extends TextViewDecorator {

        DirtyWordDetectionTextViewDecorator(AbstractTextView textView) { 
            super(textView);
        }

        @Override
        void setText(String text) {
            final String dirtyWord = "花Q";
            if (!text.contains(dirtyWord)) {
                textView.setText(text);
                return;
            }
            
            text = text.replace(dirtyWord, "**");
            textView.setText(text);
        }
    }

    /** 敏感词检测 */
    static final class SensitiveWordDetectionTextViewDecorator extends TextViewDecorator {

        SensitiveWordDetectionTextViewDecorator(AbstractTextView textView) { 
            super(textView);
        }

        @Override
        void setText(String text) {
            final String sensitiveWord = "911";
            if (!text.contains(sensitiveWord)) {
                textView.setText(text);
                return;
            }
            
            text = text.replace(sensitiveWord, "??");
            textView.setText(text);
        }
    }

    public static void main(String[] args) {
        final String input = "911 花Q，啦啦啦";
        // 1. 创建基础实例
        AbstractTextView textView = new ConcreteTextView();
        textView.setText(input);
        textView.showText();

        // 2. 添加一层脏词检测
        textView = new DirtyWordDetectionTextViewDecorator(textView);
        textView.setText(input);
        textView.showText();

        // 3. 添加一层敏感词检测
        textView = new SensitiveWordDetectionTextViewDecorator(textView);
        textView.setText(input);
        textView.showText();

        // 4. 你甚至可以动态去掉一层
        if (textView instanceof TextViewDecorator) {
            textView = ((TextViewDecorator)textView).textView;
        }
        textView.setText(input);
        textView.showText();        
    }

    private DecoratorPatternSample() {}
}
