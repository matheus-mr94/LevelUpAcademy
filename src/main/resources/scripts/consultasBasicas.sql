SELECT * FROM Category WHERE `active` = true ORDER BY sequence ;

SELECT * FROM Subcategory WHERE `active` = true ORDER BY sequence;

SELECT * FROM Course WHERE `visible` = true;

SELECT * FROM Subcategory  WHERE `description` = '' OR `description` is NULL;