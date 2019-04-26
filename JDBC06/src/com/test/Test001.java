/*=========================
	Test001.java
	- 쿼리문 전송 실습 1
=========================*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if(conn != null) {
				System.out.println("데이터베이스 연결 성공 !!!");
				
				try
				{
					/*
					Statement stmt = conn.createStatement();
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '이지혜', '010-1212-1212')";
					
					int result = stmt.executeUpdate(sql);
					
					if(result > 0)
						System.out.println("데이터 입력 성공 !!");
					
					stmt.close();
					DBConn.close();
					*/
					
					/*
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '이기승', '010-9090-9090')";
					
					Statement stmt = conn.createStatement();
					
					int result = stmt.executeUpdate(sql);
					
					if(result > 0)
						System.out.println("데이터 입력 성공 !!");
					
					stmt.close();
					DBConn.close();
					*/
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, ?, ?)";
					
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					// IN 매개변수 넘겨주기
					pstmt.setString(1, "윤희진");
					pstmt.setString(2, "010-7777-7777");
					
					int result = pstmt.executeUpdate();
					
					if(result > 0)
						System.out.println("데이터 입력 성공 !!");
					
					pstmt.close();
					DBConn.close();
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
