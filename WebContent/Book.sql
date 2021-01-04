-- 테이블을 만들어 오류 발생할 때 테이블이 2번 생성이 안되게 삭제하기
DROP TABLE book_TBL;

-- 오라클 버전 테이블 만들기
CREATE table book_TBL(
	bcode	NUMBER(5) not null PRIMARY KEY,
	btitle	VARCHAR2(30),
	bwriter	VARCHAR2(30),
	bpub	VARCHAR2(30),
	bprice	NUMBER(10),
	bdate	DATE
);
COMMIT

INSERT INTO book_TBL VALUES(10100, '자바킹', '강길동', '1001', 12000, '2020-11-02');
INSERT INTO book_TBL VALUES(10101, '알고리듬', '남길동', '1002', 18000, '2020-05-05');
INSERT INTO book_TBL VALUES(10102, '스프링두', '서길동', '1003', 23000, '2019-08-03');
INSERT INTO book_TBL VALUES(10103, '파이썬', '홍길동', '1004', 9000, '2019-10-11');

--INSERT INTO MEMBER_TBL(memno, name, id, pass, birth, gender, job, city, joindate) VALUES(1005, '김길동', 'HONG5', '1234', 2001, '남자', '공무원', '용인', SYSDATE);
-- 해당 테이블 목록 보기 정렬 칼럼 지정 오름차순, 내림차순
--SELECT * FROM MEMBER_TBL ORDER BY MEMNO ASC;
-- 해당 테이블의 가장 큰 값 가져오기
--SELECT MAX(MEMNO) FROM MEMBER_TBL;
-- 지정된 MEMNO 값에 해당하는 칼럼 삭제하기
--DELETE FROM MEMBER_TBL WHERE MEMNO = 1005;
-- 지정된 MEMNO값에 해당하는 칼럼 값들 수정하기
--UPDATE MEMBER_TBL SET pass='1111', birth=2010, gender='남자', job='회사원', city='성남' WHERE memno = 1002; 