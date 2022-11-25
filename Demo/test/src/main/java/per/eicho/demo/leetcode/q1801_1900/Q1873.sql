-- 1873. Calculate Special Bonus
-- https://leetcode.com/problems/calculate-special-bonus/

SELECT
    employee_id,
    CASE
        WHEN MOD(employee_id, 2) = 1 AND SUBSTR(name, 1, 1) <> 'M' THEN salary
        ELSE 0 
    END as 'bonus'
FROM
    Employees
ORDER BY
    employee_id