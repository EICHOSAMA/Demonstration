package per.eicho.demo.nowcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

public final class HJ098 {

    private static final AutoSellingSystem autoSellingSystem = new DefaultAUtoSellingSystemImpl();

    public static void main(String[] args) {
        @SuppressWarnings("all")
        Scanner in = new Scanner(System.in);
        // 命令：
        //  1. 系统初始化: r A1 数量 -A2 数量 -A3 数量 -A4 数量 -A5 数量 -A6 数量 1 元张数 -2 元张数 -5 元张数 -10 元张数
        //  2. 投币: p 10;
        //  3. 购买商品: b A1;
        //  4. 退币: c;
        //  5. 查询: q 查询类别([0,1])
        // i.e. r 22-18-21-21-7-20 3-23-10-6;c;q0;p 1;b A6;c;b A5;b A1;c;q1;p 5;

        // 注意 hasNext 和 hasNextLine 的区别
        in.useDelimiter(";");
        while (in.hasNext()) { // 注意 while 处理多个 case
            final String commandStr = in.next();
            if (commandStr.length() == 0) continue;
            // System.out.println(commandStr);
            final char command = commandStr.charAt(0);
            if (command == 'r') {
                final String[] parts = commandStr.split("\\s|-");
                if (parts.length != (1 + 6 + 4)) return;
                final int[] productCounts = toNums(parts, 1, 6);
                if (productCounts == null) continue;
                final int[] cashCounts = toNums(parts, 7, 4);
                if (cashCounts == null) continue;
                autoSellingSystem.init(productCounts, cashCounts);
            } else if (command == 'p') {
                final String[] parts = commandStr.split("\\s");
                if (parts.length != (1 + 1)) return;
                final int money = Integer.parseInt(parts[1]);
                // （钱币面额不考虑负数、字符等非正整数的情况）
                autoSellingSystem.charge(money);
            } else if (command == 'b') {
                final String[] parts = commandStr.split("\\s");
                if (parts.length != (1 + 1)) return;
                autoSellingSystem.buy(parts[1]);
            } else if (command == 'c') {
                autoSellingSystem.cancel();
            } else if (command == 'q') {
                final String[] parts = commandStr.split("\\s");
                final String arg1 = parts.length < 2 ? null : parts[1];
                autoSellingSystem.query(arg1);
            }
        }

        in.close();
    }

    private static int[] toNums(String[] parts, int start, int size) {
        final int[] nums = new int[size];
        for (int i = 0, p = start; i < size; i++, p++) {
            try {
                nums[i] = Integer.parseInt(parts[p]);
            } catch (Exception e) {
                return null;
            }
        }
        return nums;
    }

    private static final class DefaultAUtoSellingSystemImpl implements AutoSellingSystem {

        final int[] productPrices = new int[]{2, 3, 4, 5, 8, 6};
        final int[] denominations = new int[]{1, 2, 5, 10};
        int[] productCounts = new int[6];
        int[] cashCounts = new int[4];
        int userBalance = 0;

        @Override
        public void init(int[] productCounts, int[] cashCounts) {
            this.productCounts = productCounts;
            this.cashCounts = cashCounts;
            System.out.println("S001:Initialization is successful");
        }

        @Override
        public void charge(int denomination) {
            if (!isValidDenomination(denomination)) {
                System.out.println("E002:Denomination error");
                return;
            }

            if (!checkIfChangeIsEnough(denomination)) {
                System.out.println("E003:Change is not enough, pay fail");
                return;                
            }

            if (checkIfAllProductsSold()) {
                System.out.println("E005:All the goods sold out");
                return;  
            }

            cashCounts[indexOfDenomination(denomination)]++;
            userBalance += denomination;
            System.out.println("S002:Pay success,balance=" + userBalance);
        }

        private int indexOfDenomination(int denomination) {
            switch (denomination) {
                case 1: return 0;
                case 2: return 1;
                case 5: return 2;
                case 10: return 3;
                default: return -1;
            }
        }

        private boolean isValidDenomination(int denomination) {
            return indexOfDenomination(denomination) != -1;
        }

        private boolean checkIfChangeIsEnough(int denomination) {
            if (denomination == 1 || denomination == 2) return true;
            if (cashCounts[0] + cashCounts[1] * 2 < denomination) return false;
            return true;
        }
        
        private boolean checkIfAllProductsSold() {
            for (int productCount : productCounts) {
                if (productCount > 0) return false;
            }
            return true;
        }

        @Override
        public void buy(String productID) {
            if (!isValidProductID(productID)) {
                System.out.println("E006:Goods does not exist");
                return;
            }

            final int index = indexOfProductID(productID);
            if (checkIfProductSoldOut(index)) {
                System.out.println("E007:The goods sold out");
                return;
            }

            if (!canBuy(index)) {
                System.out.println("E008:Lack of balance");
                return;
            }

            productCounts[index]--;
            userBalance -= productPrices[index]; 
            System.out.println("S003:Buy success,balance=" + userBalance);
        }

        static final int IDX_PRODUCT_ID_UNKNOWN = -1;
        private int indexOfProductID(String productID) {
            if (productID == null || productID.length() != 2) return IDX_PRODUCT_ID_UNKNOWN;
            if (productID.charAt(0) != 'A') return IDX_PRODUCT_ID_UNKNOWN;
            final int index = productID.charAt(1) - '1';
            if (index < 0 || index > 6) return IDX_PRODUCT_ID_UNKNOWN; // [0, 6)
            return index;
        }

        private boolean isValidProductID(String productID) {
            return indexOfProductID(productID) != IDX_PRODUCT_ID_UNKNOWN;
        }

        private boolean checkIfProductSoldOut(int productIndex) {
            return productCounts[productIndex] == 0;
        }

        private boolean canBuy(int productIndex) { 
            return userBalance >= productPrices[productIndex];
        }

        @Override
        public void cancel() {
            if (userBalance == 0) {
                System.out.println("E009:Work failure");
                return;
            }

            final int[] plan = tryCancel();
            applyPlan(plan);
            printCoinInfo(plan);
        }

        private void applyPlan(int[] plan) {
            for (int i = 0; i < cashCounts.length; i++) cashCounts[i] -= plan[i];
            userBalance = 0;
        }

        private int[] tryCancel() {
            final int[] bestPlan = new int[6];
            bestPlan[4] = userBalance;
            bestPlan[5] = 0;

            final int[] plan = new int[6];
            plan[4] = userBalance;
            plan[5] = 0;

            search(3, plan, bestPlan);

            return bestPlan;
        }

        private void search(int idx, int[] status, int[] bestPlan) {
            if (idx == -1) {
                if (status[4] > bestPlan[4]) return;
                if (status[4] == bestPlan[4]) {
                    if (status[5] >= bestPlan[5]) return;
                    // Copy;
                }
                // Copy
                System.arraycopy(status, 0, bestPlan, 0, status.length);
                return;
            }
            final int denomination = denominations[idx];

            while (status[4] - denomination >= 0
              && status[idx] + 1 <= cashCounts[idx]) {
                status[4] -= denomination;
                status[5]++;
                status[idx]++;
            }

            search(idx - 1, status, bestPlan);
            if (bestPlan[4] == 0) return;
            
            while (status[idx] > 0) {
                status[idx]--;
                status[5]--;
                status[4] += denomination;

                search(idx - 1, status, bestPlan);
                if (bestPlan[4] == 0) return;
            }
        }

        private void printCoinInfo(int[] plan) {
            for (int i = 0; i < denominations.length; i++) {
                System.out.println(denominations[i] + " yuan coin number=" + plan[i]);
            }
        }

        @Override
        public void query(String type) {
            if (!isValidQueryType(type)) {
                System.out.println("E010:Parameter error");
                return;
            }

            if ("0".equals(type)) {
                queryProductInfo();
            } else {
                queryCashBoxInfo();
            }
        }

        private void queryProductInfo() {
            final int[][] productInfos = new int[6][3];
            for (int i = 0; i < productInfos.length; i++) {
                final int[] productInfo = productInfos[i];
                
                productInfo[0] = i + 1; // 名称
                productInfo[1] = productPrices[i]; // 单价
                productInfo[2] = productCounts[i]; // 数量
            }

            Arrays.sort(productInfos, (info1, info2) -> {
                // 根据商品数量从大到小进行排序；商品数量相同时，按照商品名称的先后顺序进行排序 。
                if (info1[2] != info2[2]) return Integer.compare(info2[2], info1[2]); // desc
                return Integer.compare(info1[0], info2[0]); // asc
            } );

            for (int[] productInfo : productInfos) {
                System.out.println("A" + productInfo[0] + " " + productInfo[1] + " " + productCounts[2]);
            }
        }

        private void queryCashBoxInfo() {
            for (int i = 0; i < cashCounts.length; i++) {
                System.out.println(denominations[i] + " yuan coin number=" + cashCounts[i]);
            }
        }

        private boolean isValidQueryType(String queryType) {
            if ("0".equals(queryType) || "1".equals(queryType)) return true;
            return false;
        }

    }

    private static interface AutoSellingSystem {

        /** 1. 系统初始化 */
        void init(int[] productCounts, int[] cashCounts); 

        /** 
         * <p>2. 投币</p> 
         * <pre>
         * （1） 如果投入非1元、2元、5元、10元的钱币面额（钱币面额不考虑负数、字符等非正整数的情况），
         *       输出“E002:Denomination error”；
         * （2） 如果存钱盒中1元和2元面额钱币总额小于本次投入的钱币面额，
         *       输出“E003:Change is not enough, pay fail”，但投入1元和2元面额钱币不受此限制。
         * （3） 如果自动售货机中商品全部销售完毕，投币失败。
         *       输出“E005:All the goods sold out”；
         * （4） 如果投币成功，
         *       输出“S002:Pay success,balance=X”；
         * </pre>
         */
        void charge(int denomination);

        /**
         * <p>3. 购买商品</p>
         * <pre>
         * （1） 如果购买的商品不在商品列表中，
         *       输出“E006:Goods does not exist”；
         * （2） 如果所购买的商品的数量为0，
         *       输出“E007:The goods sold out”；
         * （3） 如果投币余额小于待购买商品价格，
         *       输出“E008:Lack of balance”；
         * （4） 如果购买成功，
         *       输出“S003:Buy success,balance=X”；
         * </pre>
         */
        void buy(String productID);

        /**
         * <p>4. 退币</p>
         * <pre>
         * （1） 如果投币余额等于0的情况下，输出“E009:Work failure”；
         * （2） 如果投币余额大于0的情况下，按照 退币原则 进行“找零”，输出退币信息；
         * </pre>
         */
        void cancel();

        /**
         * <p>查询: q</p>
         * <pre>
         * （1） 查询自动售货机中商品信息，包含商品名称、单价、数量。 根据商品数量从大到小进行排序；商品数量相同时，按照商品名称的先后顺序进行排序 。
         *       例如：A1的商品名称先于A2的商品名称，A2的商品名称先于A3的商品名称。
         * （2） 查询存钱盒信息，包含各种面额钱币的张数；
         * （3） 查询类别如下表所示:
         *       0 - 查询商品信息
         *       1 - 查询存钱盒信息
         *  如果“查询类别”参数错误，输出“E010:Parameter error”。“查询类别”参数错误时，不进行下面的处理；
         * </pre>
         */
        void query(String type);
    }    
}
