-- 584. Find Customer Referee
-- https://leetcode.com/problems/find-customer-referee/

SELECT 
    name 
FROM 
    customer 
WHERE 
    isnull(referee_id) OR referee_id <> 2