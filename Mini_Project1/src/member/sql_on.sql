CREATE TABLE WORKER(
ID VARCHAR2(10),
PW VARCHAR2(10) NOT NULL,
NAME VARCHAR2(10) NOT NULL,
INDATE DATE DEFAULT SYSDATE,
CONSTRAINT WORKER_ID_PK PRIMARY KEY(ID)
);

CREATE TABLE WORKER_MoHp(
ID VARCHAR2(10),
HP NUMBER(3),
MONEY NUMBER(3),
CNT_DATE NUMBER(3),
CONSTRAINT WORKER_MoHp_FK FOREIGN KEY (ID)
REFERENCES WORKER(ID)
);

COMMIT;

ALTER TABLE WORKER_MoHp MODIFY ID VARCHAR2(100);

SELECT * FROM WORKER_mohp;
SELECT * FROM WORKER;
INSERT INTO WORKER values ('ASDASDQEQDdasad', 'dqoihdoewhof', 'adjfhasdijfasfddsf', '');

update worker_mohp wh set wh.cnt_date = wh.cnt_date+1
from worker wk w
where wh.id in(select w.id from worker w );

