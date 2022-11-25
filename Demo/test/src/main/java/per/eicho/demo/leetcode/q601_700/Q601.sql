-- 601. Human Traffic of Stadium
-- https://leetcode.com/problems/human-traffic-of-stadium/description/

SELECT
    s0.id,
    s0.visit_date,
    s0.people
FROM
    Stadium s0
LEFT JOIN Stadium sm2 on sm2.id = s0.id - 2 and sm2.people >= 100
LEFT JOIN Stadium sm1 on sm1.id = s0.id - 1 and sm1.people >= 100
LEFT JOIN Stadium sp1 on sp1.id = s0.id + 1 and sp1.people >= 100
LEFT JOIN Stadium sp2 on sp2.id = s0.id + 2 and sp2.people >= 100
WHERE
    s0.people >= 100 AND
    (
        (NOT ISNULL(sp1.id) AND NOT ISNULL(sm1.id)) OR
        (NOT ISNULL(sm2.id) AND NOT ISNULL(sm1.id)) OR
        (NOT ISNULL(sp1.id) AND NOT ISNULL(sp2.id))    
    )
ORDER BY 
    s0.visit_date;