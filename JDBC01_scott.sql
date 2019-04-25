SELECT USER
FROM DUAL;
--==>> SCOTT

DROP TABLE TBL_MEMBER;
--==>> Table TBL_MEMBER��(��) �����Ǿ����ϴ�.

--�� �ǽ� ���̺� ����
CREATE TABLE TBL_MEMBER
( SID   NUMBER
, NAME  VARCHAR2(30)
, TEL   VARCHAR2(60)
, CONSTRAINT MEMBER_SID_PK PRIMARY KEY(SID)
);
--==>> Table TBL_MEMBER��(��) �����Ǿ����ϴ�.

--�� ���� ������ �Է�
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(1, '������', '010-1111-1111');
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.

--�� Ȯ�� 
SELECT *
FROM TBL_MEMBER;
--==>> 1	������	010-1111-1111

--�� Ŀ��
COMMIT;
--==>> Ŀ�� �Ϸ�.

--�� �ڹٿ��� Test002 Ŭ���� ���� �� �ٽ�Ȯ��
SELECT *
FROM TBL_MEMBER;
--==>>
/*
2	������	010-2222-2222
1	������	010-1111-1111
*/


--�� �Է� ������ ����
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(3, '�̱��', '010-3333-3333');



--�� �ڹٿ��� Test003 Ŭ���� ���� �� �ٽ�Ȯ��
SELECT *
FROM TBL_MEMBER;
--==>>
/*
2	������	010-2222-2222
3	�̱��	010-3333-3333
4	���Ѿ�	010-4444-4444
1	������	010-1111-1111
*/


--�� ������ �غ�(��ȸ)
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY 1
;
























