package per.eicho.demo.algorithm.id;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * <p>雪花ID</p>
 * <pre>
 *  雪花ID，Snow flake ID是Twitter开源的分布式ID生成算法，其值由64个bit组成。
 *  其组成结构为：
 *    1. unused（未使用）       ，范围 [0,1)，   大小1位 ，
 *    2. timestamp（时间戳）    ，范围 [1, 42)， 大小41位，单位毫秒，41位表示支持2^41毫秒约69年
 *    3. machine ID（机器ID）   ，范围 [42, 52)，大小10位，也可以细分如机房ID+机房机器编号、集群ID+集群下节点ID灵活调整
 *    4. item id in millisecond，范围 [52, 64)，大小12位，代表某毫秒级别的item ID，12位代表每毫秒该机器支持4096个ID。
 * </pre>
 */
public class SnowFlakeID {
    final long id;
    private static final int BIT_COUNT_4_UNUSED = 1;
    private static final int BIT_COUNT_4_TIMESTAMPE = 41;
    private static final int BIT_COUNT_4_MACHINE_ID = 10;
    private static final int BIT_COUNT_4_ITEM_ID_PER_SEC = 12;
    private static final Instant SNOW_FLAKE_ID_START_INSTANT;
    // ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("Etc/GMT+0"));
    
    static {
        // 2022年1月1日0点0分0秒-0纳秒, 本初子午线（0时区为基准）
        final ZonedDateTime zdt = ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("Etc/GMT+0"));
        SNOW_FLAKE_ID_START_INSTANT = zdt.toInstant();
    }

    SnowFlakeID(long val) {
        this.id = val;
    }

    public int getMachineID() {
        return (int)((id >> BIT_COUNT_4_MACHINE_ID) & 0b11_1111_1111L);
    }

    public int getItemID() {
        return (int)(id & 0b1111_1111_1111L);
    }

    /**
     * <p>获取时间戳信息</p>
     * <pre>
     *  获取雪花ID中的时间戳信息，单位毫秒（millisecond）。
     * </pre>
     */
    public long getTimeStamp() {
        return id >> (BIT_COUNT_4_ITEM_ID_PER_SEC + BIT_COUNT_4_MACHINE_ID);
    }

    public ZonedDateTime getTimeStampInZDTForm() {
        final long timestamp = getTimeStamp();
        final Instant instant = SNOW_FLAKE_ID_START_INSTANT.plus(timestamp, ChronoUnit.MILLIS);
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(64 + 3);
        final int[] bitCounts = new int[]{
            BIT_COUNT_4_UNUSED, BIT_COUNT_4_TIMESTAMPE, 
            BIT_COUNT_4_MACHINE_ID, BIT_COUNT_4_ITEM_ID_PER_SEC};
        final long mask = 0b1L;
        
        int stage = 3;
        long value = this.id;
        while (stage >= 0) {
            int bitCount = bitCounts[stage--];
            while (bitCount > 0) {
                sb.append(value & mask);
                value >>= 1;
                bitCount--;
            }
            if (stage >= 0) sb.append('-');
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Random random = new Random();
        long val;
        while ((val = random.nextLong()) < 0L) {}
        SnowFlakeID snowFlakeID = new SnowFlakeID(val);
        System.out.println(snowFlakeID.toString());
        System.out.println(snowFlakeID.getMachineID());
        System.out.println(snowFlakeID.getItemID());
        System.out.println(snowFlakeID.getTimeStamp());
        System.out.println(snowFlakeID.getTimeStampInZDTForm());
        // System.out.println(SNOW_FLAKE_START_TIMESTAMPE_IN_MICROSECOND);
    }
}
