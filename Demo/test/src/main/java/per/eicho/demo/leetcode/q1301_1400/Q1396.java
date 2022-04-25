package per.eicho.demo.leetcode.q1301_1400;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>1304. Find N Unique Integers Sum up to Zero 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-underground-system/">
 *   1304. Find N Unique Integers Sum up to Zero</a>
 */
public final class Q1396 {
    private static class UndergroundSystem {

        private static final class Record {
            final Integer id;
            final String stationName;
            final int time;

            Record(Integer id, String stationName, int t) {
                this.id = id;
                this.stationName = stationName;
                this.time = t;
            }
        }

        private static final class TravelInfo {
            int totalTime; // 
            int count; // the count of travel.
        }

        Map<Integer, Record> checkInTimes;
        Map<String, Map<String, TravelInfo>> travelInfos;

        public UndergroundSystem() {
            checkInTimes = new HashMap<>();
            travelInfos = new HashMap<>();
        }
        
        public void checkIn(int id, String stationName, int t) {
            // 1. A customer with a card ID equal to id, checks in at the station stationName at time t.
            // 2. A customer can only be checked into one place at a time.
            checkInTimes.put(id, new Record(id, stationName, t));
        }
        
        public void checkOut(int id, String stationName, int t) {
            // A customer with a card ID equal to id, checks out from the station stationName at time t.  
            final Record record = checkInTimes.get(id);
            
            if (!travelInfos.containsKey(record.stationName)) {
                travelInfos.put(record.stationName, new HashMap<>());
            }
            final Map<String, TravelInfo> infos = travelInfos.get(record.stationName);
            
            if (!infos.containsKey(stationName)) {
                infos.put(stationName, new TravelInfo());
            }
            final TravelInfo travelInfo = infos.get(stationName);

            travelInfo.count++;
            travelInfo.totalTime += (t - record.time);
        }
        
        public double getAverageTime(String startStation, String endStation) {
            int count = 0;
            double totalTime = 0;

            if (travelInfos.containsKey(startStation)) {
                if (travelInfos.get(startStation).containsKey(endStation)) {
                    final TravelInfo travelInfo = travelInfos.get(startStation).get(endStation);
                    count += travelInfo.count;
                    totalTime += travelInfo.totalTime;
                }
            }

            if (travelInfos.containsKey(endStation)) {
                if (travelInfos.get(endStation).containsKey(startStation)) {
                    final TravelInfo travelInfo = travelInfos.get(endStation).get(startStation);
                    count += travelInfo.count;
                    totalTime += travelInfo.totalTime;
                }
            }
            
            return totalTime / count;
        }
    }
    
}
