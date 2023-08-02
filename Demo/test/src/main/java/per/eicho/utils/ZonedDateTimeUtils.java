package per.eicho.utils;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TimeZone;

/**
 * 收录ZonedDateTime常用使用例的工具类
 * 
 * @since 2023-03-17
 * @author torane
 */
public final class ZonedDateTimeUtils {
    private static final DateTimeFormatter FORMATTER_4_DEFAULT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); 
    private static final DateTimeFormatter FORMATTER_4_DST = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss 'DST'");

    /**
     * <p>把给定的 {@link ZonedDateTime} 实例格式化</p>
     * <pre>
     *  标准格式为 yyyy-MM-dd hh:mm:ss 
     *  本格式化方法支持夏令时的显示，如果系统当前时区支持夏令时且正在夏令时持续时间之内，
     *  那么会在原来格式的基尾部追加 <b>DST</b> 用以标识显示的时间为夏令时时间。
     * 
     *  因为不输出时区信息，给定的 {@link ZonedDateTime} 实例的时区会与系统时间的时区统一。
     * </pre>
     * 
     * <p>Example:</p>
     * <pre>
     *  如系统时区为 Europe/Berlin (欧洲/柏林)，根据目前欧洲夏令时的规则为
     *   1. 每年三月最后一个周日的 01:00 UTC 时将时钟拨快1小时.
     *   2. 每年十月最后一个周日的 01:00 UTC 时将时钟拨慢1小时.
     * 
     *  如果系统时间在上述 1和2 的区间之内（夏令时时间），如
     *   input: 2023-04-04 01:40 UTC+0200 output: "2023-04-04 01:40 DST" 
     *  如果系统时间在上述 1和2 的区间之外（非夏令时时间），如
     *   input: 2023-03-19 01:40 UTC+0100 output: "2023-03-19 01:40"
     * </pre>
     * 
     * @param zonedDateTime 需要转换的实例，non-null
     * @return 格式化后的时间字符串
     */
    public static String formatWithDST(ZonedDateTime zonedDateTime) {
        Objects.requireNonNull(zonedDateTime);
        final TimeZone timeZone = TimeZone.getDefault();
        final OffsetDateTime odt = zonedDateTime.toOffsetDateTime()
            .withOffsetSameInstant(ZonedDateTime.now().getOffset());
        if (timeZone.observesDaylightTime()) {
            return FORMATTER_4_DST.format(odt);
        }
        return FORMATTER_4_DEFAULT.format(odt);
    }

    /**
     * <p>转换给定epcho毫秒级时间戳至ZonedDateTime实例<p>
     * <pre>
     *  ZonedDateTime实例的Time Zone为系统时区（{@link ZoneId#systemDefault()}）
     * </pre>
     * 
     * @param epochMilli epcho毫秒级时间戳（距1970-01-01T00:00:00Z的毫秒数）
     * @return ZonedDateTime实例
     */
    public static ZonedDateTime toZonedDateTime(long epochMilli) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
    }

    private ZonedDateTimeUtils() {}
}
