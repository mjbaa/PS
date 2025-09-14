SELECT book_id, date_format(published_date, "%Y-%m-%d") as PUBLISHED_DATE
FROM book
WHERE year(published_date) = 2021 and category = '인문'
ORDER BY published_date