-- 1484. Group Sold Products By The Date
-- https://leetcode.com/problems/group-sold-products-by-the-date/

SELECT 
    a.sell_date, 
    COUNT(DISTINCT a.product) as 'num_sold', 
    GROUP_CONCAT(DISTINCT a.product SEPARATOR ',') as products 
FROM 
    (SELECT * FROM activities ORDER BY sell_date, product) as a
GROUP BY 
    a.sell_date