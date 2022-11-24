-- 1667. Fix Names in a Table
-- https://leetcode.com/problems/fix-names-in-a-table/

SELECT 
    user_id,
    CONCAT(UCASE(SUBSTR(name, 1, 1)), LCASE(SUBSTR(name, 2))) as 'name'
FROM
    Users
ORDER BY
    user_id