-- 175. Combine Two Tables
-- https://leetcode.com/problems/combine-two-tables/

SELECT 
    p.firstName as 'firstName',
    p.lastName as 'lastName',
    a.city as 'city',
    a.state as 'state'
FROM
    Person p
LEFT JOIN 
    Address a 
on 
    p.personId = a.personId 
