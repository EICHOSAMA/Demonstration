-- 1141. User Activity for the Past 30 Days I
-- https://leetcode.com/problems/user-activity-for-the-past-30-days-i/

SELECT
    activity_date as day ,COUNT(DISTINCT user_id ) as active_users 
FROM 
    Activity 
WHERE
    activity_date BETWEEN DATE_SUB('2019-07-27', INTERVAL 29 DAY)  AND '2019-07-27' 
GROUP BY
    activity_date