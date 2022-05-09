-- 1581. Customer Who Visited but Did Not Make Any Transactions
-- https://leetcode.com/problems/customer-who-visited-but-did-not-make-any-transactions/

SELECT 
    * 
FROM
    Visits
LEFT JOIN Transactions On visit_id
