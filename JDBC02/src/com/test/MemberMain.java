package com.test;

import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

public class MemberMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			MemberDAO dao = new MemberDAO();
			
			int count = dao.count();
			
			do
			{
				System.out.printf("이름 전화번호 입력(%d) : ", (++count));
				String name = sc.next();
				
				// 반복문의 조건을 무너뜨리는 코드 구성
				if (name.equals("."))
					break;
				
				String tel = sc.next();
				
				// MemberDTO 객체 구성
				MemberDTO dto = new MemberDTO();
				dto.setName(name);
				dto.setTel(tel);
				
				// 데이터베이스에 데이터를 입력하는 메소드 호출
				int result = dao.add(dto);
				if (result > 0)
					System.out.println("회원 정보 입력 완료 !!!");
				
			} while (true);
			
			System.out.println();
			System.out.println("----------------------------------");
			System.out.printf("전체 회원 수 : %d명\n", dao.count());
			System.out.println("----------------------------------");
			System.out.println("번호	이름	전화번호");
			
			for (MemberDTO obj : dao.lists())
			{
				System.out.printf("%3s %7s %12s\n", obj.getSid(), obj.getName(), obj.getTel());
			}
			
			System.out.println("----------------------------------");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		} 
		
		finally 
		{
			try
			{
				DBConn.close();
				System.out.println("데이터베이스 연결 닫힘 !!!");
				System.out.println("프로그램 종료");
			} catch (SQLException e)
			{
				System.out.println(e.toString());
			}
		}
	}
}


// 실행 결과
/*
이름 전화번호 입력(4) : 전훈의 010-1111-1111
회원 정보 입력 완료 !!!
이름 전화번호 입력(5) : 유진석 010-2222-2222
회원 정보 입력 완료 !!!
이름 전화번호 입력(6) : 최보라 010-3333-3333
회원 정보 입력 완료 !!!
이름 전화번호 입력(7) : .

----------------------------------
전체 회원 수 : 6명
----------------------------------
번호	이름	전화번호
  1     전훈의 010-1111-1111
  2     유진석 010-2222-2222
  3     최보라 010-3333-3333
  4     전훈의 010-1111-1111
  5     유진석 010-2222-2222
  6     최보라 010-3333-3333
----------------------------------
데이터베이스 연결 닫힘 !!!
프로그램 종료
*/


