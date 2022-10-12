package per.eicho.demo.designpattern.creational;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>创建型模式 - 抽象工厂</p>
 * <pre>
 *  抽象工厂，Abstract Factory。工厂模式是把组装复杂对象的逻辑给隐藏到工厂内部。
 *  抽象工厂模式呢则是当你需要使用<b>多组协同使用的产品系列中的一组</b>时使用到的设计模式。
 *  
 *  通俗来讲就是假设有医疗用品厂，我们可以有
 *    1. 遵循国际标准ISO的医疗用品厂
 *    2. 遵循国标GB的医疗用品厂
 *    3. 遵循日标JIS的医疗用品厂。
 *  这些医疗用具厂都能生产各类医疗用品（口罩、消毒液、防护服）等，
 *  但因为各国市场监管原因必须遵守本国标准、没有本国标准的国家就使用ISO标准。
 *  
 *  另一个应用场景，就是GUI应用的皮肤（Style）了，一般来说某个风格的组件是不与其他风格组件
 *  搭配的，就比如说色彩不搭、扁平不搭立体的等等。
 *  应用可以通过指定具体的抽象通常实现类来实现风格切换等。
 * </pre>
 * 
 * <pre>
 *  抽象工厂模式因其实现利用了工厂方法模式，是非常非常容易被误导的一个GoF设计模式。
 *  一定要注意它核心是在于对于“一组”产品的平滑替换。
 * </pre>
 */
@GoFDesignPattern
final class AbstractFactoryPatternSample {

    static abstract class AbstractStandardlizedProduct {
        final String standardCode;
        final String productName;
        AbstractStandardlizedProduct(String standardCode, String productName) {
            this.standardCode = standardCode;
            this.productName = productName;
        }

        void printProductInfo() { System.out.println(productName + "-" + standardCode); }
    }

    static abstract class Mask extends AbstractStandardlizedProduct {
        Mask(String standardCode) { super(standardCode, "口罩"); }
    }

    static final class ISOMask extends Mask {
        ISOMask() { super("ISO-MASK"); }
    }

    static final class GBMask extends Mask {
        GBMask() { super("GB-MASK"); }
    }

    static final class JISMask extends Mask {
        JISMask() { super("JIS-MASK"); }
    }

    static abstract class Disinfectant extends AbstractStandardlizedProduct {
        Disinfectant(String standardCode) { super(standardCode, "消毒液"); }
    }

    static final class ISODisinfectant extends Disinfectant {
        ISODisinfectant() { super("ISO-DISINFECTANT"); }
    }

    static final class GBDisinfectant extends Disinfectant {
        GBDisinfectant() { super("GB-DISINFECTANT"); }
    }

    static final class JISDisinfectant extends Disinfectant {
        JISDisinfectant() { super("JIS-DISINFECTANT"); }
    }    
    
    /** 抽象工厂类定义<b>一组</b>产品 */
    static interface MedicalFactory {
        Mask createMask();
        Disinfectant createDisinfectant();
    }

    static final class ISOMedicalFactory implements MedicalFactory {
        @Override public Mask createMask() { return new ISOMask(); }
        @Override public Disinfectant createDisinfectant() { return new ISODisinfectant(); }
    }

    static final class GBMedicalFactory implements MedicalFactory {
        @Override public Mask createMask() { return new GBMask(); }
        @Override public Disinfectant createDisinfectant() { return new GBDisinfectant(); }
    }
    
    static final class JISMedicalFactory implements MedicalFactory {
        @Override public Mask createMask() { return new JISMask(); }
        @Override public Disinfectant createDisinfectant() { return new JISDisinfectant(); }
    }

    public static void main(String[] args) {
        MedicalFactory medicalFactory = new ISOMedicalFactory();
        medicalFactory.createDisinfectant().printProductInfo();
        medicalFactory.createMask().printProductInfo();
    
        medicalFactory = new GBMedicalFactory();
        medicalFactory.createDisinfectant().printProductInfo();
        medicalFactory.createMask().printProductInfo();
    
        medicalFactory = new JISMedicalFactory();
        medicalFactory.createDisinfectant().printProductInfo();
        medicalFactory.createMask().printProductInfo();
    }

    private AbstractFactoryPatternSample() {}
}
