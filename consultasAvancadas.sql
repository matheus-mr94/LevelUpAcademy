SELECT distinct s.`name`, s.sequence
FROM Subcategory s
INNER JOIN Course c
ON c.subcategory_id = s.id
WHERE `active`
ORDER BY sequence;

SELECT c.instructor,
COUNT(*)
FROM Course c
GROUP BY c.instructor
ORDER BY COUNT(*) DESC LIMIT 1;

SELECT ca.`name` as category_name,COUNT(co.id) as courses ,
COALESCE(SUM(co.estimated_time_in_hours), 0) as total_time 
FROM Category ca 
LEFT JOIN Subcategory su ON ca.id = su.category_id
LEFT JOIN Course co ON su.id = co.subcategory_id
WHERE ca.`active`
GROUP BY ca.`name`;