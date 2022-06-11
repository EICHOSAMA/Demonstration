# 626. Exchange Seats
# https://leetcode.com/problems/exchange-seats/

// ID - 1-based
// 1 ⇒ 2 (odd + 1)
// 2 ⇒ 1 (even - 1)
// 3 ⇒ 

SELECT 
    (CASE
        WHEN MOD(id, 2) = 0 THEN id - 1 
        WHEN MOD(id, 2) = 1 AND counts <> id THEN id + 1 
        ELSE id 
    END) AS id, 
    student 
FROM 
    seat, 
    (SELECT COUNT(*) AS counts FROM seat) AS seat_counts 
ORDER BY 
    id ASC;