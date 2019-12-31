create table loan
(
        id number not null,
        amount double not null,
        term integer not null,
        country_id integer not null,
        client_id integer not null,
        primary key(id)

);


--INSERT INTO LOAN
--(ID, AMOUNT, TERM, COUNTRY_ID, CLIENT_ID)
--VALUES(1, 1000, 60, 1, 1);
--
--
--INSERT INTO LOAN
--(ID, AMOUNT, TERM, COUNTRY_ID, CLIENT_ID)
--VALUES(2, 1000, 60, 2, 2);
--
--
--INSERT INTO LOAN
--(ID, AMOUNT, TERM, COUNTRY_ID, CLIENT_ID)
--VALUES(3, 1000, 60, 3, 3);