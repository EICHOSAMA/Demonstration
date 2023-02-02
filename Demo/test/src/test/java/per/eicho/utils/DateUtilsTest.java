package per.eicho.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DateUtilsTest {

    private int input;
    private boolean expected;

    public DateUtilsTest(int input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {0, true}, 
            {1, false},
            {4, true},
            {8, true},
            {12, true},
            {2000, true}, {2100, false}, {2200, false}, {2300, false}, 
            {2400, true}, {2401, false}, {2402, false}, {2403, false},
            {2404, true},
            {-4, true}, {-3, false}, {-2, false}, {-1, false},
            {-2000, true}, {-2100, false}, {-2200, false}, {-2300, false},  
        });
    }

    @Test
    public void testIsLeapYear() {
        Assert.assertEquals("", expected, DateUtils.isLeapYear(input)); 
    }
}
