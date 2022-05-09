-- 1581. Customer Who Visited but Did Not Make Any Transactions
-- https://leetcode.com/problems/customer-who-visited-but-did-not-make-any-transactions/

SELECT 
    customer_id, COUNT(customer_id) as 'count_no_trans' 
FROM
    Visits v
LEFT JOIN 
    Transactions t On v.visit_id = t.visit_id
WHERE 
    amount IS NULL
GROUP BY 
    customer_id

