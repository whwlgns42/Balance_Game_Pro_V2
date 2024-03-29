CREATE TABLE QUESTIONS (-- 질문
	QID INT PRIMARY KEY, -- PK
	WRITER VARCHAR2(30), -- 작성자 ID
	TITLE VARCHAR2(300) NOT NULL, -- 제목
	ANSWER_A VARCHAR2(300) NOT NULL, -- 답변 A
	ANSWER_B VARCHAR2(300) NOT NULL, -- 답변 B
	EXPLANATION VARCHAR2(300) NOT NULL, -- 문제 설명
	CATEGORY INT DEFAULT 1, -- 카테고리 FK
	Q_ACCESS CHAR(1) DEFAULT 'F' CHECK(Q_ACCESS IN('T','F')) NOT NULL, -- 문제 승인여부
	REG_DATE DATE DEFAULT SYSDATE -- 질문 등록시간
); -- 테이블 합쳐서 칼럼 추가하는 방향으로


CREATE TABLE ANSWERS (-- 답변
	AID INT PRIMARY KEY, -- PK
	QID INT NOT NULL, -- 질문 FK
	LOGIN_ID VARCHAR2(50), -- 로그인 FK
	ANSWER VARCHAR2(300) CHECK(ANSWER IN('A','B')), -- 선택한 답변
	REG_DATE DATE DEFAULT SYSDATE -- 답변 등록시간
);


CREATE TABLE COMMENTS (-- 댓글
	CID INT PRIMARY KEY, -- PK
	QID INT NOT NULL,  -- 질문 FK
	LOGIN_ID VARCHAR2(50), -- 로그인 FK
	CONTENT VARCHAR2(300) NOT NULL,-- 댓글
	REG_DATE DATE DEFAULT SYSDATE -- 댓글 등록시간
);


CREATE TABLE MEMBER ( -- 멤버
    MID INT PRIMARY KEY,-- PK
    LOGIN_ID VARCHAR2(50) UNIQUE NOT NULL, -- 로그인 ID
    MPW VARCHAR2(255) NOT NULL,-- 비밀번호
    NAME VARCHAR2(100) NOT NULL,-- 이름
    CELL_PHONE VARCHAR2(30) NOT NULL, -- 전화번호
    EMAIL VARCHAR2(100) NOT NULL,-- 이메일
    ADDRESS VARCHAR2(1000) NOT NULL,-- 주소
    GENDER VARCHAR2(10) NOT NULL CHECK (GENDER IN ('M', 'F')),-- 성별
    AGE INT NOT NULL,-- 나이
    GRADE INT DEFAULT 1,-- 등급
    MADMIN VARCHAR2(10) DEFAULT 'USER' CHECK (MADMIN IN ('ADMIN', 'USER')),-- 관리자 유무
    REG_DATE DATE DEFAULT SYSDATE-- 유저 등록시간
);

CREATE TABLE SAVE (-- 찜
	SID INT PRIMARY KEY,-- PK
	QID INT , -- 질문 FK
	LOGIN_ID VARCHAR2(50)-- 로그인 FK
);

CREATE TABLE SUPPORT (--후원
	SUID INT PRIMARY KEY,-- PK
	LOGIN_ID VARCHAR2(50),-- 로그인 FK
	AMOUNT NUMBER NOT NULL,-- 후원금액
	REG_DATE DATE DEFAULT SYSDATE-- 후원 등록시간
);

--카테고리 정규화
CREATE TABLE CATEGORY(--카테고리
	CGID INT PRIMARY KEY,-- PK
	CATEGORY VARCHAR2(300) NOT NULL
);

CREATE TABLE SUGGESTION(--건의
	SUGID INT PRIMARY KEY,--건의 PK
	LOGIN_ID VARCHAR2(50), --로그인 아이디
	TITLE VARCHAR2(50), --제목
	SUGGESTION VARCHAR2(4000),--건의사항
	REG_DATE DATE DEFAULT SYSDATE--건의 등옥시간
);


drop table QUESTIONS;
drop table ANSWERS;
drop table COMMENTS;
drop table MEMBER;
drop table SAVE;
drop table SUPPORT;
drop table CATEGORY;
drop table SUGGESTION;