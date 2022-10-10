package per.eicho.demo.designpattern.structural;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import per.eicho.demo.designpattern.structural.FlyweightSample.RGBColor.PredefinedColor;

/**
 * <p>结构型模式 - 享元模式</p>
 * <pre>
 *  Flyweight，译做享元。享元模式主要解决的问题是通过共享的方式来减少
 *  大量被创建的相同的<b>小体量的</b>实例，减少内存资源的消耗。
 * </pre>
 * 
 * <pre>
 *  享元模式的实际应用：
 *   - {@link java.lang.Integer#valueOf(int)}
 *   - {@link java.lang.Boolean#valueOf(boolean)}
 *   - 如上述之外其他6种原生类型的装箱类等等
 * </pre>
 */
final class FlyweightSample {

    /** 存储RGB颜色信息的类 */
    final static class RGBColor {
        final int r;
        final int g;
        final int b;

        RGBColor(int red, int green, int blue) {
            this.r = red;
            this.g = green;
            this.b = blue;
        }

        /** 预定义的颜色枚举类型 */
        static enum PredefinedColor {
            RED,
            YELLO,
            GRAY,
            WHITE
        }

        static RGBColor colorOf(PredefinedColor predefinedColor) {
            return colorCache.get(predefinedColor);
        }

        /** 类缓存，存储享元模式需要共享的RGBColor实例 */
        static final Map<PredefinedColor, RGBColor> colorCache;

        static {
            final Map<PredefinedColor, RGBColor> tempColorCache = new HashMap<>();
            
            tempColorCache.put(PredefinedColor.RED, new RGBColor(0xff, 0x00, 0x00));
            tempColorCache.put(PredefinedColor.YELLO, new RGBColor(0xff, 0xff, 0x00));
            tempColorCache.put(PredefinedColor.GRAY, new RGBColor(0x80, 0x80, 0x80));
            tempColorCache.put(PredefinedColor.WHITE, new RGBColor(0xff, 0xff, 0xff));

            colorCache = Collections.unmodifiableMap(tempColorCache);
        }
    }

    public static void main(String[] args) {
        final RGBColor redColor1 = RGBColor.colorOf(PredefinedColor.RED);
        final RGBColor redColor2 = RGBColor.colorOf(PredefinedColor.RED);
        assert(redColor1 == redColor2);
    }

    private FlyweightSample() {}
}
