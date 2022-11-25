-- 1393. Capital Gain/Loss
-- https://leetcode.com/problems/capital-gainloss/description/

SELECT
    s.stock_name,
    (IFNULL(sst.sell_total, 0) - IFNULL(sbt.buy_total, 0)) as 'capital_gain_loss'
FROM
    (SELECT DISTINCT 
        stock_name
    FROM
        Stocks
    ) as s
LEFT JOIN
    (SELECT 
        sb.stock_name,
        sum(sb.price) as 'buy_total' 
    FROM 
        Stocks sb 
    WHERE 
        sb.operation = 'Buy' 
    GROUP BY 
        sb.stock_name) as sbt
ON s.stock_name = sbt.stock_name
LEFT JOIN
    (SELECT 
        ss.stock_name,
        sum(ss.price) as 'sell_total' 
    FROM 
        Stocks ss 
    WHERE 
        ss.operation = 'Sell' 
    GROUP BY 
        ss.stock_name) as sst
ON s.stock_name = sst.stock_name
GROUP BY
    s.stock_name