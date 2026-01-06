SELECT ID, 
    CASE
        when a.rvalue <= 0.25 then "CRITICAL"
        when a.rvalue <= 0.50 then "HIGH"
        when a.rvalue <= 0.75 then "MEDIUM"
        else "LOW"
    end as COLONY_NAME
FROM (select ID, Percent_rank() over(order by size_of_colony desc) as rvalue
from ECOLI_DATA) as a
order by ID
