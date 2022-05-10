package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1603. Design Parking System 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-parking-system/">1603. Design Parking System</a>
 */
@SuppressWarnings("unused")
public final class Q1603 {
    private static final class ParkingSystem {

        final int[] spaces;

        /** 
         * Initializes object of the ParkingSystem class. 
         * The number of slots for each parking space are given as part of the constructor. 
         */
        public ParkingSystem(int big, int medium, int small) {
            spaces = new int[]{0, big, medium, small};
        }
        
        /**
         * Checks whether there is a parking space of carType for the car that wants to get into the parking lot. 
         * carType can be of three kinds: big, medium, or small, 
         * which are represented by 1, 2, and 3 respectively. 
         * A car can only park in a parking space of its carType. 
         * 
         * If there is no space available, return false, 
         * else park the car in that size space and return true.
         * 
         * @param carType
         * @return
         */
        public boolean addCar(int carType) {
            // carType ∈ [1,2,3]
            if (spaces[carType] == 0) return false;
            spaces[carType]--;
            return true; 
        }
    }
}
