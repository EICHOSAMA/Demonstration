-- 1158. Market Analysis I
-- https://leetcode.com/problems/market-analysis-i/description/

SELECT
    u.user_id as 'buyer_id',
    u.join_date,
    IFNULL(i.order_count, 0) as 'orders_in_2019'
FROM
    Users u
LEFT JOIN
    (SELECT 
        o.buyer_id,
        count(o.order_id) as order_count
    FROM
        Orders o
    WHERE
        YEAR(o.order_date) = 2019
    GROUP BY o.buyer_id
    ) as i
ON
    u.user_id = i.buyer_id