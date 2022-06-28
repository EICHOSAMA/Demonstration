package per.eicho.demo.algorithm.math.mod;

import static org.hamcrest.core.Is.*;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class ModOperationSampleModoloTest {
    private int dividend;
    private int divisor;
    private int expected;
    
    public ModOperationSampleModoloTest(int dividend, int divisor, int expected) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.expected = expected;
    }

    @Parameters(name = "{index}: testRemainder({0}), {1} = {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {10, 1, Math.floorMod(10,1)},
            {10, 2, Math.floorMod(10, 2)},
            {10, 11, Math.floorMod(10, 11)},

            {10, -1, Math.floorMod(10, -1)},
            {10, -2, Math.floorMod(10, -2)},
            {10, -11, Math.floorMod(10, -11)},

            {-10, 1, Math.floorMod(-10, 1)},
            {-10, 2, Math.floorMod(-10, 2)},
            {-10, 11, Math.floorMod(-10, 11)},
           
            {-10, -1, Math.floorMod(-10, -1)},
            {-10, -2, Math.floorMod(-10, -2)},
            {-10, -11, Math.floorMod(-10, -11)},
                        
            {Integer.MAX_VALUE, Integer.MAX_VALUE , Math.floorMod(Integer.MAX_VALUE, Integer.MAX_VALUE)},
            {Integer.MAX_VALUE, Integer.MIN_VALUE , Math.floorMod(Integer.MAX_VALUE, Integer.MIN_VALUE)},
            {Integer.MIN_VALUE, Integer.MAX_VALUE , Math.floorMod(Integer.MIN_VALUE, Integer.MAX_VALUE)},
            {Integer.MIN_VALUE, Integer.MIN_VALUE , Math.floorMod(Integer.MIN_VALUE, Integer.MIN_VALUE)},

            {Integer.MAX_VALUE, -1 , Math.floorMod(Integer.MAX_VALUE, -1)},
            {Integer.MAX_VALUE, 1 , Math.floorMod(Integer.MAX_VALUE, 1)},
            {Integer.MIN_VALUE, 1 , Math.floorMod(Integer.MIN_VALUE, 1)},
            {Integer.MIN_VALUE, -1 , Math.floorMod(Integer.MIN_VALUE, -1)},
        });
    }
    
    @Test
    public void testModolo() {
        assertThat(ModOperationSample.modulo(dividend, divisor), is(expected));
    }
}
