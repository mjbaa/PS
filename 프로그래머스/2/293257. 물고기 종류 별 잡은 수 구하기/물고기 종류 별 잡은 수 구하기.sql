-- 코드를 작성해주세요
SELECT count(*) as FISH_COUNT, b.FISH_NAME
FROM FISH_INFO as a
JOIN FISH_NAME_INFO as b
    on a.FISH_TYPE = b.FISH_TYPE
group by FISH_NAME
order by FISH_COUNT desc