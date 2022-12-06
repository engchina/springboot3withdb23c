-- DROP TABLE REGION CASCADE CONSTRAINTS;

CREATE TABLE REGION
(
    REGION_ID   VARCHAR2(100)
        CONSTRAINT REGION_ID_NN NOT NULL,
    REGION_NAME VARCHAR2(200)
);

CREATE UNIQUE INDEX REGION_ID_PK
    ON REGION (REGION_ID);

ALTER TABLE REGION
    ADD (CONSTRAINT reg_id_pk
        PRIMARY KEY (REGION_ID)
        );

INSERT INTO REGION(REGION_ID, REGION_NAME)
VALUES ( 'ap-sydney-1'
       , 'オーストラリア東部(シドニー)');
INSERT INTO REGION(REGION_ID, REGION_NAME)
VALUES ( 'ap-melbourne-1'
       , 'オーストラリア南東部(メルボルン)');
INSERT INTO REGION(REGION_ID, REGION_NAME)
VALUES ( 'sa-saopaulo-1'
       , 'ブラジル東部(サンパウロ)');
INSERT INTO REGION(REGION_ID, REGION_NAME)
VALUES ( 'ap-osaka-1'
       , '日本中央部(大阪)');
INSERT INTO REGION(REGION_ID, REGION_NAME)
VALUES ( 'ap-tokyo-1'
       , '日本東部(東京)');

COMMIT;