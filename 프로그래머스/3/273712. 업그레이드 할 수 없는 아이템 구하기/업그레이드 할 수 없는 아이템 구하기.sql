# SELECT ITEM_ID, ITEM_NAME, RARITY
# FROM ITEM_INFO
# WHERE ITEM_ID NOT IN (
#     SELECT PARENT_ITEM_ID
#     FROM ITEM_TREE
#     WHERE PARENT_ITEM_ID IS NOT NULL
# )
# ORDER BY ITEM_ID DESC



select a.item_id, a.item_name, a.rarity
from item_info as a
left outer join item_tree as b
on a.item_id = b.parent_item_id
where b.item_id is null
order by a.item_id desc