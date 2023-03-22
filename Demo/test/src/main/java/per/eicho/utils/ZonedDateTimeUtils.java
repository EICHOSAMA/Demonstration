package per.eicho.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 收录ZonedDateTime常用使用例的工具类
 * 
 * @since 2023-03-17
 * @author torane
 */
public final class ZonedDateTimeUtils {
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
