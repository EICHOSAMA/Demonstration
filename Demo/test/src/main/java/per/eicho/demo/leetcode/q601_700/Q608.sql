-- 608. Tree Node
-- https://leetcode.com/problems/tree-node/

SELECT 
    t1.id, 
    CASE 
        WHEN ISNULL(t1.p_id) THEN "Root"
        WHEN c.countOfSubTree > 0 THEN "Inner"
        ELSE "Leaf"
    END 

FROM 
    Tree t1
LEFT JOIN
    (SELECT count(t2.id) as 'countOfSubTree', t2.p_id FROM Tree t2 Group BY t2.p_id) as c 
    ON c.p_id = t1.id