-- 카테고리 별 2022년 1월 판매량 합산
SELECT category, sum(b.sales) as TOTAL_SALES
from BOOK as a
join BOOK_SALES as b
    on a.book_id = b.book_id
where SALES_DATE like "2022-01%"
group by category
order by category