-- 1795. Rearrange Products Table
-- https://leetcode.com/problems/rearrange-products-table/

SELECT
    p1.product_id,
    'store1' as 'store',
    p1.store1 as price
FROM
    Products p1
WHERE
    p1.store1 IS NOT NULL
UNION
SELECT
    p2.product_id,
    'store2' as 'store',
    p2.store2 as price
FROM
    Products p2
WHERE
    p2.store2 IS NOT NULL
UNION
SELECT
    p3.product_id,
    'store3' as 'store',
    p3.store3 as price
FROM
    Products p3
WHERE
    p3.store3 IS NOT NULL