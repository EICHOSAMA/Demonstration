package per.eicho.demo.designpattern.structural;

import java.util.Objects;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>结构型模式 - 组成模式</p>
 * <pre>
 *  组成，Composite。组成模式主要是将复杂对象和单一对象组合成树形结构。
 *  对其客户（Client）表现出统一性。树形结构几乎都可以往组成模式上联想。
 *  解释器模式通常会与组成模式搭配使用。
 * </pre>
 */
@GoFDesignPattern
final class CompositePatternSample {

    static abstract class ExpressionNode {
        abstract int evaluate();
    }

    static final class NumericExpressionNode extends ExpressionNode {
        final int num;
        NumericExpressionNode(int num) { 
            this.num = num; 
        }
        
        @Override
        int evaluate() { 
            return num; 
        }
    }

    static abstract class AbstractOperatorExpressionNode extends ExpressionNode {
        final ExpressionNode expr1;
        final ExpressionNode expr2;

        AbstractOperatorExpressionNode(ExpressionNode expr1, ExpressionNode expr2) {
            Objects.nonNull(expr1);
            Objects.nonNull(expr2);
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        protected abstract int calculate(int num1, int num2);

        @Override
        int evaluate() {
            return calculate(expr1.evaluate(), expr2.evaluate());
        }
    }

    static final class AddOperatorExpressionNode extends AbstractOperatorExpressionNode {
        
        AddOperatorExpressionNode(ExpressionNode expr1, ExpressionNode expr2) { 
            super(expr1, expr2); 
        }

        @Override
        protected int calculate(int num1, int num2) { 
            return num1 + num2; 
        }
    }

    static final class MinusOperatorExpressionNode extends AbstractOperatorExpressionNode {
        
        MinusOperatorExpressionNode(ExpressionNode expr1, ExpressionNode expr2) { 
            super(expr1, expr2); 
        }

        @Override
        protected int calculate(int num1, int num2) { 
            return num1 - num2; 
        }
    }

    static final class ProductOperatorExpressionNode extends AbstractOperatorExpressionNode {
        
        ProductOperatorExpressionNode(ExpressionNode expr1, ExpressionNode expr2) { 
            super(expr1, expr2); 
        }

        @Override
        protected int calculate(int num1, int num2) { 
            return num1 * num2; 
        }
    }

    static final class DivisionOperatorExpressionNode extends AbstractOperatorExpressionNode {
        
        DivisionOperatorExpressionNode(ExpressionNode expr1, ExpressionNode expr2) { 
            super(expr1, expr2); 
        }

        @Override
        protected int calculate(int num1, int num2) { 
            return num1 / num2; 
        }
    }

    public static void main(String[] args) {
        final ExpressionNode expressionNode1 = new NumericExpressionNode(10);
        System.out.println(expressionNode1.evaluate());
        
        final ExpressionNode expressionNode2 = new NumericExpressionNode(10);
        System.out.println(expressionNode2.evaluate());

        System.out.println(new AddOperatorExpressionNode(expressionNode1, expressionNode2).evaluate());
        System.out.println(new MinusOperatorExpressionNode(expressionNode1, expressionNode2).evaluate());
        System.out.println(new ProductOperatorExpressionNode(expressionNode1, expressionNode2).evaluate());
        System.out.println(new DivisionOperatorExpressionNode(expressionNode1, expressionNode2).evaluate());
    }
    
    private CompositePatternSample() {}
}
