package per.eicho.demo.nowcoder.offer;

public final class JZ061 {
    public boolean isContinuous(int [] numbers) {
        int mark = 0;
        int zeroCount = 0;
        for (int num : numbers) {
            if (num == 0) {
                zeroCount++;
            } else {
                mark |= (1 << num);
            }
        }

        int lastBit = mark & -mark;
        for (int i = 0; i < 5; i++) {
            if ((mark & lastBit) == 0) {
                // mark该位为0
                zeroCount--;
            } else {
                // mark该位为1
            }
            lastBit <<= 1;
        }

        return zeroCount == 0;
    }
}
