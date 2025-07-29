-- 코드를 작성해주세요
SELECT a.ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO a join ITEM_TREE b on a.ITEM_ID = b.ITEM_ID
where a.ITEM_ID not in (SELECT PARENT_ITEM_ID
                     FROM ITEM_TREE
                       WHERE PARENT_ITEM_ID is not null)
order by ITEM_ID DESC