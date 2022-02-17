-- 184. Department Highest Salary
-- https://leetcode.com/problems/department-highest-salary/

SELECT
    d.name AS 'Department', e.name AS 'Employee', e.salary
FROM 
    Employee e
JOIN
    Department d
ON
    e.departmentId = d.id
WHERE
    (e.departmentId, e.salary) IN
    (SELECT e2.departmentId, MAX(salary) FROM Employee e2 GROUP BY e2.departmentId)