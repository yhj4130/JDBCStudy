SELECT USER
FROM DUAL;
--==>> SCOTT

TRUNCATE TABLE TBL_MEMBER;
--==>> Table TBL_MEMBER이(가) 잘렸습니다.

CREATE SEQUENCE MEMBERSEQ
NOCACHE;
--==>> Sequence MEMBERSEQ이(가) 생성되었습니다.

DROP SEQUENCE MEMBERSEQ;


--○ 입력(INSERT) 쿼리문 구성
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '최은상', '010-1111-1111')
;

--○ 인원 수 확인 쿼리문 구성
SELECT COUNT(*) AS COUNT
FROM TBL_MEMBER;
--> 한 줄 구성
SELECT COUNT(*) AS COUNT FROM TBL_MEMBER
;

--○ 전체 리스트 조회 쿼리문 구성
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--> 한 줄 구성
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;

DELETE 
FROM TBL_MEMBER;

COMMIT;