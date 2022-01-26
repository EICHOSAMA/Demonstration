# Write your MySQL query statement below
SELECT a.class FROM
(SELECT 
  class, COUNT(student) as cnt
FROM 
  COURSES
GROUP BY class) as a WHERE a.cnt >= 5