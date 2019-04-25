/*==================
	MemberMain.java
==================*/

/*
○ 문제
   TBL_MEMBER 테이블을 활용하여
   이름과 전화번호를 여러 건 입력받고, 전체 출력하는 프로그램을 구현한다.
   단, 데이터베이스 연동이 이루어져야 하고
   MemberDAO, MemberDTO 클래스를 활용해야 한다.

실행 예)

이름 전화번호 입력(1) : 전훈의 010-1111-1111
회원 정보 입력 완료 !!!
이름 전화번호 입력(2) : 유진석 010-2222-2222
회원 정보 입력 완료 !!!
이름 전화번호 입력(3) : 최보라 010-3333-3333
회원 정보 입력 완료 !!!
이름 전화번호 입력(4) : .

--------------------------------------------
전체 회원 수 : 3명
--------------------------------------------
번호	이름	전화번호
1	   전훈의  010-1111-1111
2	   유진석  010-2222-2222
3	   최보라  010-3333-3333
--------------------------------------------
*/

package com.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;

public class MemberMain_Backup
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		
		Connection conn = DBConn.getConnection();
		
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		
		int num=1;
		
		while(true)
		{
			System.out.print("이름 전화번호 입력("+num+") : ");
			String name = sc.next();
			
			if (name.equals("."))
				break;
			
			String tel = sc.next();
			
			dto.setName(name);
			dto.setTel(tel);
			
			dao.add(dto);
			
			System.out.println("회원 정보 입력 성공 !!!");
			
			num++;
		};
		
		System.out.println("\n----------------------------------");
		System.out.printf("전체 회원 수 : %d명\n", dao.count());
		System.out.println("----------------------------------");
		System.out.println("번호	이름	전화번호");
		
		for (MemberDTO string : dao.lists())
		{
			System.out.printf("%3s %7s %12s\n", string.getSid(), string.getName(), string.getTel());
		}
		
		System.out.println("----------------------------------");
		
	}
}


// 실행 결과
/*
이름 전화번호 입력(1) : 전훈의 010-1111-1111
회원 정보 입력 성공 !!!
이름 전화번호 입력(2) : 유진석 010-2222-2222
회원 정보 입력 성공 !!!
이름 전화번호 입력(3) : 최보라 010-3333-3333
회원 정보 입력 성공 !!!
이름 전화번호 입력(4) : .

----------------------------------
전체 회원 수 : 3명
----------------------------------
번호	이름	전화번호
  1     전훈의 010-1111-1111
  2     유진석 010-2222-2222
  3     최보라 010-3333-3333
----------------------------------
*/