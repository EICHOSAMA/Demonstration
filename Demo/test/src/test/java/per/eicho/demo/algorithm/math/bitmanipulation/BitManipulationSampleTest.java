package per.eicho.demo.algorithm.math.bitmanipulation;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class BitManipulationSampleTest {
    
    private int input;
    private int expected;

    public BitManipulationSampleTest(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameters(name = "{index}: testReverse({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {0b00000000_00000000_00000000_00000000, 0b11111111_11111111_11111111_11111111},
            {0b11111111_11111111_11111111_11111111, 0b00000000_00000000_00000000_00000000},
            
            {0b01111111_11111111_11111111_11111111, 0b10000000_00000000_00000000_00000000},
            {0b10000000_00000000_00000000_00000000, 0b01111111_11111111_11111111_11111111},
            
            {0b10000000_10101010_10101010_10101010, 0b01111111_01010101_01010101_01010101},
            {0b01111111_01010101_01010101_01010101, 0b10000000_10101010_10101010_10101010}
        });
    }


    @Test
    public void testReverse() {
        assertThat(BitManipulationSample.reverse(input), is(expected));
    }
}
