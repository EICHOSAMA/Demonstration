-- 1407. Top Travellers
-- https://leetcode.com/problems/top-travellers/

SELECT 
    u.name as 'name', ifnull(sum(r.distance), 0) as 'travelled_distance' 
FROM 
    Users as u 
LEFT JOIN 
    Rides as r ON u.id = r.user_id
GROUP BY 
    u.id
ORDER BY
    travelled_distance DESC, 'name' ASC
