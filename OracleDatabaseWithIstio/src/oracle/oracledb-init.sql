
-- CREATE TABLE RATINGS (
--   REVIEW_ID NUMBER(10,0) NOT NULL,
--   RATING NUMBER(10,0),
--   PRIMARY KEY (REVIEW_ID)
-- );
-- INSERT INTO RATINGS (REVIEW_ID, RATING) VALUES (1, 5);
-- INSERT INTO RATINGS (REVIEW_ID, RATING) VALUES (2, 4);
-- COMMIT;

-- https://ords.db23c.site:8181/ords/
-- workspace: db23c
-- pdbadmin/oracle

CREATE TABLE  "DETAILS" 
   (	"ID" NUMBER(10,0), 
	"AUTHOR" VARCHAR2(200), 
	"YEAR" NUMBER(4,0), 
	"TYPE" VARCHAR2(200), 
	"PAGES" NUMBER(10,0), 
	"PUBLISHER" VARCHAR2(200), 
	"LANGUAGE" VARCHAR2(200), 
	"ISBN-10" VARCHAR2(200), 
	"ISBN-13" VARCHAR2(200), 
	 CONSTRAINT "PK_ID" PRIMARY KEY ("ID")
  USING INDEX  ENABLE
   );
INSERT INTO DETAILS ("ID", "AUTHOR", "YEAR", "TYPE", "PAGES", "PUBLISHER", "LANGUAGE", "ISBN-10", "ISBN-13") VALUES (0, 'William Shakespeare', 1595, 'paperback', 200, 'PublisherA', 'English', '0123456789', '123-0123456789');
COMMIT;

CREATE OR REPLACE JSON RELATIONAL DUALITY VIEW DETAILS_DV AS 
  SELECT JSON {'id' is "ID" , 
	'author' IS "AUTHOR", 
	'year' IS "YEAR", 
	'type' IS "TYPE", 
	'pages' IS "PAGES", 
	'publisher' IS "PUBLISHER", 
	'language' IS "LANGUAGE", 
	'isbn10' IS "ISBN-10", 
	'isbn13' IS "ISBN-13"}
  FROM DETAILS WITH INSERT UPDATE DELETE;

DECLARE
  COL SODA_COLLECTION_T;
BEGIN
  COL := DBMS_SODA.CREATE_DV_COLLECTION('details_dv', 'DETAILS_DV');
END;
/

-- curl -X GET -u 'pdbadmin:oracle' -H "Content-Type: application/json" https://ords.db23c.site:8181/ords/pdbadmin/soda/latest/details_dv --cacert /u01/app/ords/certs/ca.crt

-- However, Oracle does not recommend that you allow anonymous access in production systems.
BEGIN
  ords.delete_privilege_mapping('oracle.soda.privilege.developer', '/soda/*');
END;
/

-- curl -X GET -H "Content-Type: application/json" https://ords.db23c.site:8181/ords/pdbadmin/soda/latest/details_dv --cacert /u01/app/ords/certs/ca.crt
