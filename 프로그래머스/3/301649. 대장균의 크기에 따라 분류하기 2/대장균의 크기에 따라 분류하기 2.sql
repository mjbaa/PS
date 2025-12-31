-- 코드를 작성해주세요
SELECT ID, 
CASE
    WHEN rvalue <=0.25 then 'CRITICAL'
    WHEN rvalue <= 0.50 then 'HIGH'
    WHEN rvalue <= 0.75 then 'MEDIUM'
    ELSE 'LOW'
END as COLONY_NAME
FROM (
    SELECT ID, PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) as rvalue
    FROM ECOLI_DATA
) as rank_table
ORDER BY ID


