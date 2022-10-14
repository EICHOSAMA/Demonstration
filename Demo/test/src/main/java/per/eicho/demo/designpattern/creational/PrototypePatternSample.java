package per.eicho.demo.designpattern.creational;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>创建型模式 - 原型模式</p>
 * <pre>
 *  原型，Prototype模式，意图是通过克隆（Clone）的方式来极大地简化复杂对象的创建过程。
 *  原型涉及到克隆绕不开的就是<b>浅拷贝</b>和<b>深拷贝</b>以及 {@link Cloneable}接口了。
 * </pre>
 * 
 * <pre>
 *  在Java里，实现 {@link Cloneable} 接口意味着该类支持 field-by-field copy（也就是深拷贝）
 *  Java实例的属性分为几种，深拷贝策略也略有不同。
 *   - 原生类型（Primitive type）：8种，如int、float、boolean之类的，直接值拷贝即可。
 *   - 引用类型（Reference type）：根据其状态是否可改变分两种
 *     - 不可变类（Immutable Class）：实例被创建后不可更改，因其不可更改性，直接拷贝引用即可。 
 *     - 可变类（Mmutable Class）：因其可变，需要与其他实例做区分，需要对可变类属性也实行深拷贝。
 * </pre>
 */
@GoFDesignPattern
final class PrototypePatternSample {

    static final class XXXDTO implements Cloneable {

        /* ------ 原生类型 ------- */

        private int intField = 0;
        private short shortField = 0;
        private long longField = 0;
        private boolean booleanField = false;
        private byte byteField = 0;
        private char charField = '\u0000';
        private float floatField = 0.0f;
        private double doubleField = 0.0d;

        /* ------ 引用类型 ------- */

        /** 不可变实例 */
        private String strField = null;
        /** 可变实例 */
        private final List<String> strListField = new ArrayList<>();

        XXXDTO() {}

        @Override
        public XXXDTO clone() {
            final XXXDTO prototype = this;
            final XXXDTO clonedObj = new XXXDTO();

            // 1. 原生类型，值拷贝
            clonedObj.intField = prototype.intField;
            clonedObj.shortField = prototype.shortField;
            clonedObj.longField = prototype.longField;
            clonedObj.booleanField = prototype.booleanField;
            clonedObj.byteField = prototype.byteField;
            clonedObj.charField = prototype.charField;
            clonedObj.floatField = prototype.floatField;
            clonedObj.doubleField = prototype.doubleField;
            
            // 2. 不可变引用类型，拷贝引用
            clonedObj.strField = prototype.strField;

            // 3. 可变引用类型，对其也进行深拷贝
            for (String str : strListField) {
                clonedObj.strListField.add(str); // 因为str是不可变的直接给过去即可。
            }
            return clonedObj;
        }
    }

    public static void main(String[] args) {
        // 使用Assert记得添加虚拟机参数 -ea (-enable assertions)
        final XXXDTO prototype = new XXXDTO();

        prototype.booleanField = true;
        prototype.intField = 255555;
        prototype.strField = "String是不可变类";
        prototype.strListField.add("str1");
        prototype.strListField.add("str2");
        
        final XXXDTO clonedObj = prototype.clone();

        assert(clonedObj != prototype);
        assert(clonedObj.getClass() == prototype.getClass());

        assert(clonedObj.intField == prototype.intField);
        assert(clonedObj.shortField == prototype.shortField);
        assert(clonedObj.longField == prototype.longField);
        assert(clonedObj.booleanField == prototype.booleanField);
        assert(clonedObj.byteField == prototype.byteField);
        assert(clonedObj.charField == prototype.charField);
        assert(clonedObj.floatField == prototype.floatField);
        assert(clonedObj.doubleField == prototype.doubleField);

        assert(clonedObj.strField == prototype.strField);
        
        assert(clonedObj.strListField != prototype.strListField);
        assert(clonedObj.strListField.size() == clonedObj.strListField.size());
        for (int i = 0; i < clonedObj.strListField.size(); i++) {
            assert(clonedObj.strListField.get(i) == prototype.strListField.get(i));
        }
    }
    
    private PrototypePatternSample() {}
}
