-- 1084. Sales Analysis III
-- https://leetcode.com/problems/sales-analysis-iii/

SELECT
    s.product_id, p.product_name 
FROM
    Sales s, Product p 
WHERE
    s.product_id = p.product_id 
GROUP BY
    s.product_id
HAVING
    '2019-01-01' <= MIN(s.sale_date) AND MAX(s.sale_date) <= '2019-03-31'