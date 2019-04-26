SELECT USER
FROM DUAL;
--==>> SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	전훈의	010-1111-1111
2	유진석	010-2222-2222
3	최보라	010-3333-3333
7	이지혜	010-1212-1212
8	이지혜	010-1212-1212
9	이기승	010-9090-9090
10	윤희진	010-7777-7777
11	조수연	010-8888-8888
12	조목연	010-9999-9999
*/


--○ CallableStatement 실습을 위한 프로시저 생성
--   프로시저 명 : PRC_MEMBERINSERT
--   프로시저 기능 : TBL_MEMBER 테이블에 데이터를 입력하는 프로시저
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VNAME IN TBL_MEMBER.NAME%TYPE
, VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
    VSID    TBL_MEMBER.SID%TYPE;
BEGIN
    
    -- 기존 SID 의 최대값 얻어오기
    SELECT NVL(MAX(SID), 0) INTO VSID
    FROM TBL_MEMBER;
    
    -- 데이터 입력
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES ((VSID+1), VNAME, VTEL);
    
    -- 커민
    COMMIT;
END;
--==>> Procedure PRC_MEMBERINSERT이(가) 컴파일되었습니다.


--○ 생성된 프로시저 테스트(확인)
EXEC PRC_MEMBERINSERT('유진석', '010-1111-2222');
--==>> PL/SQL 프로시저가 성공적으로 완료되었습니다.


--○ 테이블 조회
SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	전훈의	010-1111-1111
2	유진석	010-2222-2222
3	최보라	010-3333-3333
7	이지혜	010-1212-1212
8	이지혜	010-1212-1212
9	이기승	010-9090-9090
10	윤희진	010-7777-7777
11	조수연	010-8888-8888
12	조목연	010-9999-9999
13	유진석	010-1111-2222
*/


--○ CallableStatement 실습을 위한 프로시저 작성
--   프로시저 명 : PRC_MEMBERSELECT
--   프로시저 기능 : TBL_MEMBER 테이블의 데이터를 읽어오는 프로시저
--   ※ 『SYS_REFCURSOR』 자료형을 이용하여 커서 다루기
CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT
( VRESULT   OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN VRESULT FOR
        SELECT SID, NAME, TEL
        FROM TBL_MEMBER
        ORDER BY SID;
    
    --CLOSE VRESULT;    -- CLOSE 해버리면 넘겨주지 못함
END;
--==>> Procedure PRC_MEMBERSELECT이(가) 컴파일되었습니다.
