-- 262. Trips and Users
-- https://leetcode.com/problems/trips-and-users/

SELECT 
    T.request_at AS 'Day', 
    ROUND(SUM(IF(T.STATUS = 'completed',0,1)) / COUNT(T.STATUS), 2) AS 'Cancellation Rate'
FROM 
    Trips AS T
JOIN 
    Users AS C ON (T.client_id = C.users_id AND C.banned ='No')
JOIN 
    Users AS D ON (T.driver_id = D.users_id AND D.banned ='No')
WHERE 
    T.request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY 
    T.request_at