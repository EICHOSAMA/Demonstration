package per.eicho.demo.designpattern.behavioral;

import java.util.Arrays;
import java.util.List;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>行为模式 - 策略/p>
 * <pre>
 *  策略，Strategy。策略的含义是“为达成目标需要做的事"，
 *  
 *  经典的梗是“如何把大象塞进冰箱里”，其实就是问的“把大象塞进冰箱的策略”。也就是5W2H的How了。
 *  不同的人实现同一目标会有不同的方法，即不同的策略。如有的人上清华可能直接靠自身实例硬靠，
 *  有的人靠保送，有的人靠体育特招，有的人曲线救国改变国籍再以国际生的身份考入清华。
 * </pre>
 * 
 * <pre>
 *  策略模式，就是把达成某一目标的多个不同策略进行封装供给外部使用的一种设计模式。
 *  和状态模式类似，也能大量减少 if else 等判断语句，提升程序可读性。
 * </pre>
 */
@GoFDesignPattern
final class StrategyPatternSample {

    static interface SortStrategy {
        void sort(List<String> names);
    }

    static final class LexicalOrderSortStrategy implements SortStrategy {
        @Override
        public void sort(List<String> names) {
            names.sort((str1, str2) -> str1.compareTo(str2));
        }
    }

    static final class LexicalOrderIgnoreCaseSortStrategy implements SortStrategy {
        @Override
        public void sort(List<String> names) {
            names.sort((str1, str2) -> str1.compareToIgnoreCase(str2));        
        }
    }



    public static void main(String[] args) {
        final List<String> names = Arrays.asList("Name4", "NAme3", "name2", "naMe1");    
        SortStrategy sortStrategy = new LexicalOrderSortStrategy();

        sortStrategy.sort(names);
        System.out.println(names); // [NAme3, Name4, naMe1, name2]

        sortStrategy = new LexicalOrderIgnoreCaseSortStrategy();
        sortStrategy.sort(names);
        System.out.println(names); // [naMe1, name2, NAme3, Name4]
    }
    
    private StrategyPatternSample() {}
}
