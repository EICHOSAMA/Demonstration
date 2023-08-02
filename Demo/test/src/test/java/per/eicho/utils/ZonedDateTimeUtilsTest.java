package per.eicho.utils;

import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ZonedDateTimeUtilsTest {
    private static final TimeZone JAPAN_TIME_ZONE = TimeZone.getTimeZone("GMT+0900");

    @BeforeClass
    public static void beforeAllTest() {
        // Set time zone to Japan time zone for this whole test.
        TimeZone.setDefault(JAPAN_TIME_ZONE);
    }

    @Test
    public void testToZonedDateTime_succeed_with_normal_input() {
        ZonedDateTime real = ZonedDateTimeUtils.toZonedDateTime(0L);
        ZonedDateTime expect = ZonedDateTime.of(1970, 1, 1, 9, 0, 0, 0, JAPAN_TIME_ZONE.toZoneId());
        Assert.assertEquals(expect, real);
    }

    @Test
    public void testToZonedDateTime_succeed_with_max_long_input() {
        ZonedDateTime real = ZonedDateTimeUtils.toZonedDateTime(Long.MAX_VALUE);
        ZonedDateTime expect = ZonedDateTime.of(292278994, 8, 17, 16, 12, 55, 807_000_000, JAPAN_TIME_ZONE.toZoneId());
        Assert.assertEquals(expect, real);
    }

    @Test
    public void testToZonedDateTime_succeed_with_min_long_input() {
        ZonedDateTime real = ZonedDateTimeUtils.toZonedDateTime(Long.MIN_VALUE);
        ZonedDateTime expect = ZonedDateTime.of(-292275055, 5, 17, 1, 47, 04, 192_000_000, JAPAN_TIME_ZONE.toZoneId());
        Assert.assertEquals(expect, real);
    }
}
