DROP DATABASE IF EXISTS bookdata;
CREATE DATABASE bookdata;

USE bookdata;

CREATE TABLE book (
  id INT NOT NULL AUTO_INCREMENT,
  book_title VARCHAR(30),
  author_name VARCHAR(30),
  publication_date VARCHAR(30),
  language_name VARCHAR(30),
  format_name VARCHAR(30),
  used_or_new BOOLEAN,  
  PRIMARY KEY(id) 
);

INSERT INTO book 
	(book_title, author_name, publication_date, language_name, format_name,used_or_new)
VALUES
	('The History of Tom Jones','Henry Fielding','25th March ','English','Paperback',true),
	('Pride and Prejustice','Jane Austen','5th April ','English','Hardcover',false),
    ('Le Rouge et le Noir','Stendhai','2nd May ','French','Paperback',true),
    ('Le Pere Goriot','Honore de Balzac','10th July ','French','Hardcover',false);
    
DELIMITER //
CREATE PROCEDURE drop_user_if_exists(IN user_name VARCHAR(20))
BEGIN
    DECLARE userCount BIGINT DEFAULT 0 ;
    SELECT COUNT(*) INTO userCount FROM mysql.user
    WHERE User = user_name and  Host = 'localhost';
    IF userCount > 0 THEN
		SET @SQL_STATEMENT = CONCAT('DROP USER ',user_name,'@localhost');
		PREPARE PREP_S FROM @SQL_STATEMENT;
        EXECUTE PREP_S;
        DEALLOCATE PREPARE PREP_S;
    END IF;
END; //
DELIMITER ;

CALL drop_user_if_exists('library_user') ;        
CREATE USER library_user@localhost IDENTIFIED BY 'sesame';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON bookdata.*
TO library_user@localhost;

