-- 1141. User Activity for the Past 30 Days I
-- https://leetcode.com/problems/user-activity-for-the-past-30-days-i/

SELECT 
    DISTINCT author_id as 'id'
FROM 
    Views 
WHERE 
    author_id = viewer_id
ORDER BY 
    'id' ASC
