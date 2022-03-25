-- 607. Sales Person
-- https://leetcode.com/problems/sales-person/

SELECT
    s.name 
FROM
    SalesPerson s
WHERE
    s.sales_id  not in 
        (SELECT 
            sales_id
        FROM
            Orders o, Company c
        WHERE
            o.com_id = c.com_id AND 
            c.name = 'RED'
        )