-- 코드를 입력하세요

SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
from REST_INFO
where (Food_TYPE, FAVORITES) in (
    SELECT FOOD_TYPE, max(FAVORITES)
    FROM REST_INFO
    group by FOOD_TYPE
)
order by FOOD_TYPE desc




/*


//1. 종류별로 최댓값
//2. 최댓값인 REST_ID
//3. 이 REST_ID에 대한 정보 출력

*/