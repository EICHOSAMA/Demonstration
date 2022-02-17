-- 178. Rank Scores
-- https://leetcode.com/problems/rank-scores/

SELECT
    s.score, (SELECT COUNT(DISTINCT s2.Score) FROM Scores s2 WHERE s2.Score >= s.Score) AS 'rank ' 
FROM
    Scores AS s
ORDER BY 
    s.score DESC