-- 1741. Find Total Time Spent by Each Employee
-- https://leetcode.com/problems/find-total-time-spent-by-each-employee/description/

SELECT
    e.event_day as 'day',
    e.emp_id as 'emp_id',
    SUM(e.out_time - e.in_time) as 'total_time'
FROM
    Employees e
GROUP BY
    e.event_day, e.emp_id