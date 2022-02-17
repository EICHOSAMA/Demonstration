-- 185. Department Top Three Salaries
-- https://leetcode.com/problems/department-top-three-salaries/

SELECT
    d.Name AS 'Department', e1.Name AS 'Employee', e1.Salary
FROM
    Employee e1
JOIN
    Department d
ON 
    e1.DepartmentId = d.Id
WHERE
    -- SELECT RECORD IF THERE HAS LESS THAN 3 RECORD HAS GREATER SALARY THAN CURRENT RECORD.
    3 > (SELECT
            COUNT(DISTINCT e2.Salary)
        FROM
            Employee e2
        WHERE
            e2.Salary > e1.Salary AND 
            e1.DepartmentId = e2.DepartmentId
        )
;