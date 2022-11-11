package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;

public final class HJ018 {

    private static final int IDX_OF_CATEGORY_A = 0;
    private static final int IDX_OF_CATEGORY_B = 1;
    private static final int IDX_OF_CATEGORY_C = 2;
    private static final int IDX_OF_CATEGORY_D = 3;
    private static final int IDX_OF_CATEGORY_E = 4;
    private static final int IDX_OF_CATEGORY_ERROR = 5;
    private static final int IDX_OF_CATEGORY_PRIVATE_ID = 6;
    
    private static final long[] RANGE_OF_CATEGORY_A;
    private static final long[] RANGE_OF_CATEGORY_B;
    private static final long[] RANGE_OF_CATEGORY_C;
    private static final long[] RANGE_OF_CATEGORY_D;
    private static final long[] RANGE_OF_CATEGORY_E;
    private static final long[][] RANGES_OF_CATEGORY_PRIVATE_ID;
    

    static {
        RANGE_OF_CATEGORY_A = new long[]{
            addressParts2LongValue(1, 0, 0, 0),
            addressParts2LongValue(126, 255, 255, 255)};
        
        RANGE_OF_CATEGORY_B = new long[]{
            addressParts2LongValue(128, 0, 0, 0),
            addressParts2LongValue(191, 255, 255, 255)};    

        RANGE_OF_CATEGORY_C = new long[]{
            addressParts2LongValue(192, 0, 0, 0),
            addressParts2LongValue(223, 255, 255, 255)}; 

        RANGE_OF_CATEGORY_D = new long[]{
            addressParts2LongValue(224, 0, 0, 0),
            addressParts2LongValue(239, 255, 255, 255)};        
            
        RANGE_OF_CATEGORY_E = new long[]{
            addressParts2LongValue(240, 0, 0, 0),
            addressParts2LongValue(255, 255, 255, 255)};

        RANGES_OF_CATEGORY_PRIVATE_ID = new long[3][];
        RANGES_OF_CATEGORY_PRIVATE_ID[0] = new long[]{
            addressParts2LongValue(10, 0, 0, 0),
            addressParts2LongValue(10, 255, 255, 255)};
        RANGES_OF_CATEGORY_PRIVATE_ID[1] = new long[]{
            addressParts2LongValue(172, 16, 0, 0),
            addressParts2LongValue(172, 31, 255, 255)};
        RANGES_OF_CATEGORY_PRIVATE_ID[2] = new long[]{
            addressParts2LongValue(192, 168, 0, 0),
            addressParts2LongValue(192, 168, 255, 255)};
    }

    private static long addressParts2LongValue(int... parts) {
        if (parts.length != 4) throw new IllegalArgumentException("should pass 4 decimal number.");
        long value = 0L;
        for (int i = 0; i < parts.length; i++) {
            final int part = parts[i];
            if (isPartOutOfRange(part)) throw new IllegalArgumentException("part should be a dicimal number inside range[0, 256)");
            value <<= 8;
            value |= part;
        }
        return value;
    }

    public static void main(String[] args) {
        // 输入：多行字符串。每行一个IP地址和掩码，用~隔开。
        // 输出：统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int[] result = new int[7]; // A,B,C,D,E,Error,私有IP
        while (in.hasNext()) { // 注意 while 处理多个 case
            final String line = in.next();
            final int idx = line.indexOf('~');
        
            final String address = line.substring(0, idx);
            final String mask = line.substring(idx + 1);
            judgeAndCount(address, mask, result);
        }

        for (int i = 0, bound = result.length - 1; i < bound; i++) {
            System.out.print(result[i]);
            System.out.print(' ');
        }
        System.out.println(result[result.length - 1]);

        in.close();
    }

    private static void judgeAndCount(String address, String mask, int[] result) {
        // 1. judge address.
        final int[] addressParts;
        if (null == (addressParts = returnPartsIfAddresIsValid(address))) {
            result[IDX_OF_CATEGORY_ERROR]++;
            return;
        }

        // 2. ignore-case
        if (shouldIgnore(addressParts)) return;        

        // 3. judge mask
        if (null == returnPartsIfMaskIsValid(mask)) {
            result[IDX_OF_CATEGORY_ERROR]++;
            return;
        }
        
        // 4. check if ip address is private address
        final long id = addressParts2LongValue(addressParts);        
        if (isPrivateIPAddress(id)) {
            result[IDX_OF_CATEGORY_PRIVATE_ID]++;
        }

        // 5. check category;
        if (insideRange(id, RANGE_OF_CATEGORY_A)) {
            result[IDX_OF_CATEGORY_A]++;
        } else if (insideRange(id, RANGE_OF_CATEGORY_B)) {
            result[IDX_OF_CATEGORY_B]++;
        } else if (insideRange(id, RANGE_OF_CATEGORY_C)) {
            result[IDX_OF_CATEGORY_C]++;
        } else if (insideRange(id, RANGE_OF_CATEGORY_D)) {
            result[IDX_OF_CATEGORY_D]++;
        } else if (insideRange(id, RANGE_OF_CATEGORY_E)) {
            result[IDX_OF_CATEGORY_E]++;
        }
    }

    private static boolean insideRange(long num, long[] range) {
        return range[0] <= num && num <= range[1];
    }

    private static boolean isPrivateIPAddress(final long id) {
        for (long[] range : RANGES_OF_CATEGORY_PRIVATE_ID) {
            if (insideRange(id, range)) return true;
        }
        return false;
    }

    private static boolean shouldIgnore(int[] addressParts) {
        // 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类
        return addressParts[0] == 0 || addressParts[0] == 0b0111_1111;
    }

    private static int[] returnPartsIfAddresIsValid(String address) {
        final int[] parts = new int[4];
        int p = 0;
        for (int i = 0, len = address.length(); i < len;) {
            if (p == parts.length) return null;
            int part = 0;

            char ch;
            while (i < len && (ch = address.charAt(i)) != '.') {
                if (!isDigit(ch)) return null;
                part = part * 10 + (ch - '0');
                // part必须保持在[0, 256)
                if (isPartOutOfRange(part)) return null;
                i++;
            }
            i++; // skip '.'
            parts[p++] = part;            
        }
        return parts;
    }

    private static int[] returnPartsIfMaskIsValid(String mask) {
        final int[] parts = returnPartsIfAddresIsValid(mask);
        if (parts == null) return null;

        int i = 0;
        if (parts[0] == 0 || parts[3] == 0xFF) return null;
        boolean flag = true;
        while (i < parts.length) {
            final int part = parts[i];
            if (flag) {
                if (part == 0xFF) {
                    i++;
                    continue;
                }
                // part != 0xFF
                flag = false;
                int m = 0xFF;
                boolean isValidPart = false;
                while (m != 0) {
                    m -= (m & -m);
                    if (part == m) {
                        isValidPart = true;
                        break;
                    }
                }
                if (!isValidPart) return null;
                i++;
            } else {
                if (part != 0x00) return null; // must be 0x00
                i++;
            }
        }

        return parts;
    }

    private static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }
    
    private static boolean isPartOutOfRange(int part) {
        return part > 0xFF || part < 0;
    }
}
