-- 1587. Bank Account Summary II
-- https://leetcode.com/problems/bank-account-summary-ii/

SELECT 
    u.name as 'name', sum(t.amount) as 'balance' 
FROM
    Users u, Transactions t
WHERE
    u.account = t.account
GROUP BY 
    t.account
HAVING  
    sum(t.amount) > 10000
    
 