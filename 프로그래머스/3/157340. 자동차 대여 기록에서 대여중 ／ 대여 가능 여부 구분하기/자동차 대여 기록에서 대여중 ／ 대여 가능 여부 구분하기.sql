-- 코드를 입력하세요
SELECT  distinct CAR_ID, 
    CASE
        WHEN CAR_ID in (
            SELECT CAR_ID
            FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
            WHERE "2022-10-16" between start_date and end_Date
        ) then "대여중"
        else "대여 가능"
    end as AVAILABILITY
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
order by CAR_ID desc