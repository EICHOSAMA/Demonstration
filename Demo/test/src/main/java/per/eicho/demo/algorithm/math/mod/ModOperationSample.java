package per.eicho.demo.algorithm.math.mod;

/**
 * <p>求余与求模</p>
 * 
 * <pre>
 *   日常编程时，对于 % 运算符，一般我们并不会特别区分求余与求模的区别。
 *   但实际上这两个运算虽然类似，但并不完全相等，所以笔者打算以本样例类
 *   配以简单的代码和注释简要说明求余与求模的定义。
 * </pre>
 * 
 * <pre>
 *   求余，即求余运算，英：remainder operation，求余运算符被称为remainder operator。
 *   现在我们假设有两个数，被除数a（dividend）和除数b（divisor）。
 *   求余运算呢，就是对于a求出无法被b除尽，剩余部分。那么其计算公式则如下
 *   
 *     remainder(a, b) = a - b * rounddown(a / b); 
 *     这里rounddown函数是向下（0的方向）舍入的意思。(与Excel ROUNDDOWN函数相同)
 *   
 *   i.e. 
 *     remainder( 10,  3) = 10 - 3 * rounddown(10, 3) = 10 - 3 * 3 = 1;
 *     remainder( 10, -3) = 10 - (-3) * rounddown(10, -3) = 10 - (-3) * (-3) = 1;
 *     remainder(-10,  3) = -10 - 3 * rounddown(-10, 3) = -10 - 3 * (-3) = -1;
 *     remainder(-10, -3) = -10 - (-3) * rounddown(-10, -3) = -10 - (-3) * 3 = -1;
 * </pre>
 * 
 * <pre>
 *   求模，即求模运算，英：modulo operation，求摸运算符被称为modulo operator。
 *   我们依然利用上面的假设，现有两个数被除数a（dividend）和除数b（divisor）。
 *   求模运算的结果笔者找到的定义都很模糊，求模运算一般是在计算机里较为常用，
 *   定义的模糊也导致会在意外情况下会令人难以预测求模运算的结果，这也是笔者写下本文的原因。
 * 
 *   通常求模运算的结果被认为是非负的，但这是错误的，求模运算也会出现负数结果。
 *   求模运算结果的符号被认为是与除数（divisor）一致。（具体看i.e.）
 *   求模运算的计算公式如下
 *      
 *     modulo(a, b) = a - b * floor(a / b); 
 *     这里floor函数是向下（负无穷的方向，小的方向）舍入的意思。(与Excel FLOOR函数相同)
 *   
 *   i.e.
 *     modulo( 10,  3) = 10 - 3 * floor(10, 3) = 10 - 3 * 3 = 1;
 *     modulo( 10, -3) = 10 - (-3) * floor(10, -3) = 10 - (-3) * (-4) = -2;
 *     modulo(-10,  3) = -10 - 3 * floor(-10, 3) = -10 - 3 * (-4) = 2;
 *     modulo(-10, -3) = -10 - (-3) * floor(-10, -3) = -10 - (-3) * 3 = -1;
 * </pre>
 * 
 * <pre>
 *   结语，求模/求余运算是我们在日常编程中经常会用到的计算之一，但是每个语言的不同实现，
 *   导致这里是一片混乱的状态，这种混乱的状态可能会导致出现计算结果出现意外结果的。
 *   
 *   如有的语言（如. Visual Basic）的实现会导致求模运算的结果符号与被除数一致，
 *   如 -11 mod 2 的结果为 -1, 当你拿 (a mod b) == 1 来判断奇偶性的时候就会出现意想不到的错误。
 *   当然这种语言还是少数，希望各位读者在使用别的语言求模运算的时候留个心眼。
 * </pre>
 * 
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html">
 *   Summary of Operators - [Oracle, Java Tutorials]</a>
 * @see <a href="https://en.wikipedia.org/wiki/Remainder">
 *   Remainder - [Wikipedia]</a>
 * @see <a href="https://en.wikipedia.org/wiki/Modulo_operation">
 *   Modulo operation - [Wikipedia]</a>
 * @see <a href="https://support.microsoft.com/en-us/office/rounddown-function-2ec94c73-241f-4b01-8c6f-17e6d7968f53#:~:text=ROUNDDOWN%20behaves%20like%20ROUND%2C%20except,down%20to%20the%20nearest%20integer.">
 *   ROUNDDOWN function - [Microsoft]</a>
 * @see <a href="https://support.microsoft.com/en-us/office/floor-function-14bb497c-24f2-4e04-b327-b0b4de5a8886">
 *   FLOOR function - [Microsoft]</a>
 * @see <a href="https://docs.microsoft.com/en-us/dotnet/visual-basic/language-reference/operators/mod-operator">
 *   Mod operator (Visual Basic) - [Microsoft]</a>
 * 
 */
public final class ModOperationSample {
    
    public static int modulo(int dividend, int divisor) {
        checkIfDivisorIs0(divisor);
        // modulo(a, b) = a - b * floor(a / b); 
        return dividend - divisor * floor(dividend, divisor);
    }

    private static int floor(int dividend, int divisor) {
        int r = dividend / divisor;
        if (r * divisor != dividend && (dividend ^ divisor) < 0) r--;
        return r;
    }

    public static int remainder(int dividend, int divisor) {
        checkIfDivisorIs0(divisor);
        // return dividend % divisor;
        // remainder(a, b) = a - b * rounddown(a / b); 
        return dividend - divisor * (dividend / divisor);
    }

    private static void checkIfDivisorIs0(int divisor) {
        if (divisor == 0) throw new IllegalArgumentException("divisor is 0.");
    }

    private ModOperationSample() {}

    public static void main(String[] args) {
        // System.out.println(mod(2, -3));
        // System.out.println(mod(-2, -3));
        System.out.println(Math.floorMod(10, 3));   // 1
        System.out.println(Math.floorMod(10, -3));  // -2
        System.out.println(Math.floorMod(-10, 3));  // 2
        System.out.println(Math.floorMod(-10, -3)); // -1
    }
}
