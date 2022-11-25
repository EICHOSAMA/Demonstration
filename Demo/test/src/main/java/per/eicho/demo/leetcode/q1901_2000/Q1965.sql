-- 1965. Employees With Missing Information
-- https://leetcode.com/problems/employees-with-missing-information/description/

SELECT DISTINCT
    t.employee_id 
FROM (
    SELECT employee_id FROM Employees
    UNION 
    SELECT employee_id FROM Salaries) as t
WHERE
    t.employee_id NOT IN (
        SELECT 
            e.employee_id as 'employee_id'
        FROM
            Employees e
        INNER JOIN Salaries s on e.employee_id = s.employee_id
    )
ORDER BY
    t.employee_id ASC