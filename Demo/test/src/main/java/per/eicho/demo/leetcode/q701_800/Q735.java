package per.eicho.demo.leetcode.q701_800;

import java.util.LinkedList;

/**
 * <p>735. Asteroid Collision 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/asteroid-collision/">735. Asteroid Collision</a>
 */
public final class Q735 {
    public int[] asteroidCollision(int[] asteroids) {
        // 1. 2 <= asteroids.length <= 10^4
        // 2. -1000 <= asteroids[i] <= 1000
        // 3. asteroids[i] != 0
        final LinkedList<Integer> list = new LinkedList<>();
        final int n = asteroids.length;

        outer:
        for (int i = 0; i < n; i++) {
            final int asteroid = asteroids[i];

            if (asteroid > 0) {
                list.add(asteroid);
                continue;
            }

            // asteroid < 0
            final int absAsteroid = Math.abs(asteroid);

            int asteroid2;
            while (list.size() > 0 && (asteroid2 = list.peekLast()) > 0) {
                if (asteroid2 > absAsteroid) {
                    continue outer;
                }

                list.pollLast();
                if (asteroid2 == absAsteroid) {
                    continue outer;
                }
            }

            list.add(asteroid);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
