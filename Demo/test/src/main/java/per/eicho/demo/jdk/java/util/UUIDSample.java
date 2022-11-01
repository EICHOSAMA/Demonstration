package per.eicho.demo.jdk.java.util;

import java.util.UUID;

/**
 * <p>UUID类的样例类</p>
 * <pre>
 *  UUID, 全称Universally Unique Identifier（通用唯一标识符）。
 *  也是RFC旗下的一个标准（<a href="https://www.rfc-editor.org/rfc/rfc4122">rfc4122</a>）。
 *  JDK提供了UUID的一个实现{@link java.util.UUID}类。
 * </pre>
 * 
 * <pre>
 *  UUID本身是一个128比特位的一个值，也就是16Bytes，4个int或者2个long就能存储下这些信息。
 *  笔者的JDK实现是通过2个long来存储这些信息。
 *  而对外的表现呢则通常是32个16进制数（hex-digit）构成。如下：
 *                        
 *                        ↓ version(1, 2, 3, 4 or 5)
 *    UUID: a621ff0f-0f34-4f1b-b607-b0ef97c5d116
 *                             ↑ variant: rfc compliant(10xx binary = 8, 9, A or B)
 *    构成: 8-4-4-4-12
 *  
 *  不难看出version信息在UUID里是被放在[48, 52)位上的，参考{@link UUID#version()}源码。
 * </pre>
 * 
 * <pre>
 *  可以简单的认为UUID类是一种数据结构（聚合了128比特的数据），JDK提供UUID创建算法。
 *  UUID数据结构提供了基本的
 * </pre>
 */
final class UUIDSample {

    public static void main(String[] args) {
        printUUID(UUID.randomUUID());
        printUUID(gennerateNamedBasedUUID("张三"));
        printUUID(uuidFromString("938efb1c-59ff-11ed-8483-234c15a66f5d")); // ← Time-based UUID String
    }

    private static UUID gennerateNamedBasedUUID(String name) {
        final UUID namedBasedUUID = UUID.nameUUIDFromBytes(name.getBytes());
        return namedBasedUUID;
    }

    private static void printUUID(UUID uuid) {
        System.out.println("-----UUID print start-----");
        System.out.println(uuid);
        System.out.println("Version:" + uuid.version());
        System.out.println("Variant:" + uuid.variant());
        if (uuid.version() == 1) {
            // time-based uuid
            System.out.println("Timestamp:" + uuid.timestamp());
            System.out.println("Node:" + uuid.node());   
            System.out.println("ClockSequence:" + uuid.clockSequence());         
        }
        System.out.println("-----UUID print end-----");
    }

    private static UUID uuidFromString(String uuidStr) {
        return UUID.fromString(uuidStr);
    }
    
    private UUIDSample() {}
}
