--------------------------------------------------------
--  文件已创建 - 星期日-六月-10-2018
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence AUTO_PRIMARY_KEY_FRIENDS
--------------------------------------------------------

   CREATE SEQUENCE  "HXL"."AUTO_PRIMARY_KEY_FRIENDS"  MINVALUE 1 MAXVALUE 9999999999999 INCREMENT BY 1 START WITH 41 NOCACHE  ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence AUTO_PRIMARY_KEY_MESSAGE
--------------------------------------------------------

   CREATE SEQUENCE  "HXL"."AUTO_PRIMARY_KEY_MESSAGE"  MINVALUE 1 MAXVALUE 9999999999999 INCREMENT BY 1 START WITH 325 NOCACHE  ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence AUTO_PRIMARY_KEY_USER
--------------------------------------------------------

   CREATE SEQUENCE  "HXL"."AUTO_PRIMARY_KEY_USER"  MINVALUE 1 MAXVALUE 9999999999999 INCREMENT BY 1 START WITH 33 NOCACHE  ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table FRIENDS
--------------------------------------------------------

  CREATE TABLE "HXL"."FRIENDS"
   (	"ID" NUMBER(8,0),
	"A_ID" NUMBER(8,0),
	"B_ID" NUMBER(8,0),
	"STATUS" NUMBER(1,0) DEFAULT 2,
	"ADD_TIME" TIMESTAMP (6) DEFAULT sysdate
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;


   COMMENT ON COLUMN "HXL"."FRIENDS"."ID" IS '主键';

   COMMENT ON COLUMN "HXL"."FRIENDS"."A_ID" IS '发送好友请求的用户id';

   COMMENT ON COLUMN "HXL"."FRIENDS"."B_ID" IS '接收好友请求的用户id';

   COMMENT ON COLUMN "HXL"."FRIENDS"."STATUS" IS '消息处理的状态，0表示接收好友请求，1表示拒绝好友请求，2表示该消息未处理';
--------------------------------------------------------
--  DDL for Table MESSAGE
--------------------------------------------------------

  CREATE TABLE "HXL"."MESSAGE"
   (	"ID" NUMBER(8,0),
	"FROM_USER_ID" NUMBER(8,0),
	"TO_USER_ID" NUMBER(8,0),
	"CONTENT" VARCHAR2(500 BYTE),
	"SEND_TIME" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table USER_INFO
--------------------------------------------------------

  CREATE TABLE "HXL"."USER_INFO"
   (	"ID" NUMBER(8,0),
	"USER_NAME" VARCHAR2(20 BYTE),
	"AGE" NUMBER(2,0) DEFAULT 22,
	"ADD_TIME" TIMESTAMP (6) DEFAULT sysdate,
	"PASSWORD" VARCHAR2(64 BYTE)
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into HXL.FRIENDS
SET DEFINE OFF;
Insert into HXL.FRIENDS (ID,A_ID,B_ID,STATUS,ADD_TIME) values (40,105,17,0,to_timestamp('09-JUN-18 10.10.28.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.FRIENDS (ID,A_ID,B_ID,STATUS,ADD_TIME) values (28,105,8,0,to_timestamp('09-JUN-18 11.51.24.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.FRIENDS (ID,A_ID,B_ID,STATUS,ADD_TIME) values (27,105,7,0,to_timestamp('08-JUN-18 08.23.53.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.FRIENDS (ID,A_ID,B_ID,STATUS,ADD_TIME) values (37,105,6,0,to_timestamp('09-JUN-18 09.21.14.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.FRIENDS (ID,A_ID,B_ID,STATUS,ADD_TIME) values (1,105,106,0,to_timestamp('08-JUN-18 01.50.42.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.FRIENDS (ID,A_ID,B_ID,STATUS,ADD_TIME) values (2,107,105,1,to_timestamp('08-JUN-18 01.50.42.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.FRIENDS (ID,A_ID,B_ID,STATUS,ADD_TIME) values (3,106,107,0,to_timestamp('08-JUN-18 01.50.42.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
REM INSERTING into HXL.MESSAGE
SET DEFINE OFF;
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (262,105,106,'测试',to_timestamp('06-JUN-18 08.55.18.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (263,106,105,'测试',to_timestamp('06-JUN-18 08.55.59.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (264,106,105,'回车发送消',to_timestamp('06-JUN-18 08.56.07.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (265,106,105,'回车发送消息',to_timestamp('06-JUN-18 08.56.11.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (276,105,106,'Hellow',to_timestamp('06-JUN-18 09.04.09.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (277,105,106,'HelloWorld',to_timestamp('06-JUN-18 09.04.12.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (286,106,105,'普通消息',to_timestamp('08-JUN-18 02.52.26.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (287,106,105,'普通消息',to_timestamp('08-JUN-18 02.54.48.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (288,105,106,'测试',to_timestamp('08-JUN-18 02.54.53.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (274,105,106,'测试',to_timestamp('06-JUN-18 09.03.42.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (275,106,105,'你好',to_timestamp('06-JUN-18 09.03.54.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (257,106,105,'dfadfd',to_timestamp('06-JUN-18 08.37.40.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (259,106,105,'test',to_timestamp('06-JUN-18 08.48.58.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (260,105,106,'测试',to_timestamp('06-JUN-18 08.51.21.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (261,106,105,'远方',to_timestamp('06-JUN-18 08.51.35.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (284,105,106,'测试',to_timestamp('08-JUN-18 02.50.07.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (282,105,106,'你好',to_timestamp('07-JUN-18 09.36.11.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (283,106,105,'测试',to_timestamp('07-JUN-18 09.37.36.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (285,106,105,'message',to_timestamp('08-JUN-18 02.50.14.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (295,106,105,'66666666',to_timestamp('22-DEC-74 08.27.20.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (298,106,105,'你好',to_timestamp('09-JUN-18 04.00.37.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (293,105,8,'你好。',to_timestamp('09-JUN-18 11.56.37.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (294,8,105,'你好，test',to_timestamp('09-JUN-18 11.56.46.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (299,106,105,'测试',to_timestamp('09-JUN-18 04.01.03.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (300,106,105,'hello',to_timestamp('09-JUN-18 04.10.03.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (302,105,106,'你好，test2',to_timestamp('09-JUN-18 04.11.38.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (303,105,106,'你好，test',to_timestamp('09-JUN-18 04.11.56.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (304,105,106,'你好 test2',to_timestamp('09-JUN-18 04.12.04.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (306,106,105,'你好',to_timestamp('09-JUN-18 04.13.31.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (313,105,106,'666',to_timestamp('09-JUN-18 04.31.09.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (251,106,105,'fds',to_timestamp('06-JUN-18 07.12.35.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (252,105,106,'ngh',to_timestamp('06-JUN-18 07.12.40.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (319,105,17,'你好',to_timestamp('09-JUN-18 11.00.04.469000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (320,17,105,'测试',to_timestamp('09-JUN-18 11.00.09.651000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (321,17,105,'test',to_timestamp('09-JUN-18 11.00.24.763000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (322,105,6,'你好',to_timestamp('10-JUN-18 11.35.57.662000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (323,6,105,'你好，test',to_timestamp('10-JUN-18 11.36.11.458000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (324,106,105,'你好，test',to_timestamp('10-JUN-18 11.49.09.806000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (296,105,7,'jklj',to_timestamp('09-JUN-18 03.47.36.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (289,105,106,'test',to_timestamp('08-JUN-18 07.45.16.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (290,106,105,'test',to_timestamp('08-JUN-18 07.46.12.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (297,106,105,'hjkhjkhjkh',to_timestamp('09-JUN-18 03.48.24.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (301,106,105,'你好',to_timestamp('09-JUN-18 04.11.26.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (305,105,106,'你好',to_timestamp('09-JUN-18 04.13.14.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (307,106,105,'你好，test2',to_timestamp('09-JUN-18 04.14.52.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (308,105,106,'你好',to_timestamp('09-JUN-18 04.15.10.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (309,106,105,'dfdfdfad',to_timestamp('09-JUN-18 04.15.24.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (310,105,106,'你好',to_timestamp('09-JUN-18 04.20.09.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (311,105,106,'测试',to_timestamp('09-JUN-18 04.21.49.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (312,105,106,'你好',to_timestamp('09-JUN-18 04.29.44.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (314,105,106,'fdsfadsf',to_timestamp('09-JUN-18 04.31.37.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (253,105,106,'test',to_timestamp('06-JUN-18 08.20.58.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (254,106,105,'测试',to_timestamp('06-JUN-18 08.29.08.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (315,105,106,'ffdffadfd',to_timestamp('09-JUN-18 04.33.38.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (316,105,106,'gsdgadfshs',to_timestamp('09-JUN-18 04.33.48.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (317,106,105,'fasgadsfadsf',to_timestamp('09-JUN-18 04.34.02.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (318,105,106,'你好',to_timestamp('09-JUN-18 06.42.17.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (250,105,106,'测试',to_timestamp('06-JUN-18 07.08.44.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (255,105,106,'测试',to_timestamp('06-JUN-18 08.31.30.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (256,105,106,'test',to_timestamp('06-JUN-18 08.35.00.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (258,106,105,'远方',to_timestamp('06-JUN-18 08.46.38.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (271,105,106,'测试回车键发送消息',to_timestamp('06-JUN-18 09.01.11.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (272,105,106,'测试',to_timestamp('06-JUN-18 09.01.14.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (273,105,106,'第二条测试',to_timestamp('06-JUN-18 09.01.19.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (248,105,106,'测试',to_timestamp('06-JUN-18 07.08.04.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into HXL.MESSAGE (ID,FROM_USER_ID,TO_USER_ID,CONTENT,SEND_TIME) values (249,106,105,'很好',to_timestamp('06-JUN-18 07.08.13.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'));
REM INSERTING into HXL.USER_INFO
SET DEFINE OFF;
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (6,'test666',22,to_timestamp('08-JUN-18 10.25.11.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (9,'test11',22,to_timestamp('08-JUN-18 10.48.55.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (7,'test8',22,to_timestamp('08-JUN-18 10.44.26.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (8,'test9',22,to_timestamp('08-JUN-18 10.46.36.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (107,'test3',22,to_timestamp('05-JUN-18 11.38.03.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (3,'test7',22,to_timestamp('05-JUN-18 11.44.31.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (11,'test12',22,to_timestamp('09-JUN-18 09.47.21.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (12,'test13',22,to_timestamp('09-JUN-18 09.47.27.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (13,'test14',22,to_timestamp('09-JUN-18 09.47.34.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (14,'test15',22,to_timestamp('09-JUN-18 09.47.47.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (16,'test16',22,to_timestamp('09-JUN-18 09.47.58.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (17,'test17',22,to_timestamp('09-JUN-18 09.48.03.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (18,'test18',22,to_timestamp('09-JUN-18 09.48.09.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (19,'test19',22,to_timestamp('09-JUN-18 09.48.15.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (20,'test20',22,to_timestamp('09-JUN-18 09.48.22.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (21,'test21',22,to_timestamp('09-JUN-18 09.48.29.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (22,'test22',22,to_timestamp('09-JUN-18 09.48.44.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (24,'test23',22,to_timestamp('09-JUN-18 09.48.57.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (25,'test24',22,to_timestamp('09-JUN-18 09.49.05.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (26,'test25',22,to_timestamp('09-JUN-18 09.49.15.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (27,'test26',22,to_timestamp('09-JUN-18 09.49.21.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (28,'test27',22,to_timestamp('09-JUN-18 09.49.27.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (29,'test28',22,to_timestamp('09-JUN-18 09.49.34.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (30,'test29',22,to_timestamp('09-JUN-18 09.49.41.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (105,'test',22,to_timestamp('04-JUN-18 12.08.52.644000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (106,'test2',22,to_timestamp('04-JUN-18 01.07.05.229000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (31,'test30',22,to_timestamp('09-JUN-18 09.49.47.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
Insert into HXL.USER_INFO (ID,USER_NAME,AGE,ADD_TIME,PASSWORD) values (32,'test31',22,to_timestamp('09-JUN-18 09.49.56.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'test');
--------------------------------------------------------
--  DDL for Index USER_INFO_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "HXL"."USER_INFO_UK1" ON "HXL"."USER_INFO" ("USER_NAME")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index FRIENDS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HXL"."FRIENDS_PK" ON "HXL"."FRIENDS" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index MESSAGE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HXL"."MESSAGE_PK" ON "HXL"."MESSAGE" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index USER_INFO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HXL"."USER_INFO_PK" ON "HXL"."USER_INFO" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger AUTO_PK_FRIENDS_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "HXL"."AUTO_PK_FRIENDS_TRIGGER"
before insert on friends
for each row
begin
    -- dual是Oracle中的一个伪表
    select AUTO_PRIMARY_KEY_FRIENDS.Nextval into:new.id from DUAL;
    DBMS_OUTPUT.PUT_LINE('触发器调用成功！');
end;
/
ALTER TRIGGER "HXL"."AUTO_PK_FRIENDS_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger AUTO_PRIMARY_KEY_USER_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "HXL"."AUTO_PRIMARY_KEY_USER_TRIGGER"
before insert on user_info
for each row
begin
    -- dual是Oracle中的一个伪表
    select auto_primary_key_user.Nextval into:new.id from DUAL;
    DBMS_OUTPUT.PUT_LINE('触发器调用成功！');
end;
/
ALTER TRIGGER "HXL"."AUTO_PRIMARY_KEY_USER_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PRIMARY_KEY_MESSAGE_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "HXL"."PRIMARY_KEY_MESSAGE_TRIGGER"
before insert on message
for each row
begin
    -- dual是Oracle中的一个伪表
    select auto_primary_key_message.Nextval into:new.id from DUAL;
    DBMS_OUTPUT.PUT_LINE('触发器调用成功！');
end;
/
ALTER TRIGGER "HXL"."PRIMARY_KEY_MESSAGE_TRIGGER" ENABLE;
--------------------------------------------------------
--  Constraints for Table MESSAGE
--------------------------------------------------------

  ALTER TABLE "HXL"."MESSAGE" ADD CONSTRAINT "MESSAGE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "HXL"."MESSAGE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."MESSAGE" MODIFY ("FROM_USER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."MESSAGE" MODIFY ("TO_USER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."MESSAGE" MODIFY ("CONTENT" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."MESSAGE" MODIFY ("SEND_TIME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table USER_INFO
--------------------------------------------------------

  ALTER TABLE "HXL"."USER_INFO" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."USER_INFO" MODIFY ("USER_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."USER_INFO" MODIFY ("AGE" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."USER_INFO" MODIFY ("ADD_TIME" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."USER_INFO" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."USER_INFO" ADD CONSTRAINT "USER_INFO_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "HXL"."USER_INFO" ADD CONSTRAINT "USER_INFO_UK1" UNIQUE ("USER_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table FRIENDS
--------------------------------------------------------

  ALTER TABLE "HXL"."FRIENDS" ADD CONSTRAINT "FRIENDS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "HXL"."FRIENDS" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."FRIENDS" MODIFY ("A_ID" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."FRIENDS" MODIFY ("B_ID" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."FRIENDS" MODIFY ("STATUS" NOT NULL ENABLE);
 
  ALTER TABLE "HXL"."FRIENDS" MODIFY ("ADD_TIME" NOT NULL ENABLE);