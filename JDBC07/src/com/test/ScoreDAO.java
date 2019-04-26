package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	private Connection conn;
	
	public Connection connection()
	{
		try
		{
			conn = DBConn.getConnection();
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return conn;
	}
	
	// 데이터 입력 담당 메소드
	public int add(ScoreDTO dto)
	{
		int result = 0;
		
		try
		{
			String sql = "INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(4, dto.getMat());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 전체 리스트 출력 담당 메소드
	public  ArrayList<ScoreDTO> lists()
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		try
		{
			String sql = "SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT) AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE ORDER BY SID ASC";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				ScoreDTO dto = new ScoreDTO();
				
				dto.setSid(rs.getInt("SID"));
				dto.setName(rs.getString("NAME"));
				dto.setKor(rs.getInt("KOR"));
				dto.setEng(rs.getInt("ENG"));
				dto.setMat(rs.getInt("MAT"));
				dto.setTot(rs.getInt("TOT"));
				dto.setAvg(rs.getDouble("AVG"));
				dto.setRank(rs.getInt("RANK"));
				
				result.add(dto);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 이름 검색 담당 메소드
	public ArrayList<ScoreDTO> lists(String name) 
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		try
		{
			String sql = "SELECT * FROM (SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT) AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE ORDER BY SID ASC) WHERE NAME = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				ScoreDTO dto = new ScoreDTO();
				
				dto.setSid(rs.getInt("SID"));
				dto.setName(rs.getString("NAME"));
				dto.setKor(rs.getInt("KOR"));
				dto.setEng(rs.getInt("ENG"));
				dto.setMat(rs.getInt("MAT"));
				dto.setTot(rs.getInt("TOT"));
				dto.setAvg(rs.getDouble("AVG"));
				dto.setRank(rs.getInt("RANK"));
				
				result.add(dto);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 번호 검색 담당 메소드
	public ArrayList<ScoreDTO> lists(int sid)
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		try
		{
			String sql = "SELECT * FROM (SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT) AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE ORDER BY SID ASC) WHERE SID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				ScoreDTO dto = new ScoreDTO();
				
				dto.setSid(rs.getInt("SID"));
				dto.setName(rs.getString("NAME"));
				dto.setKor(rs.getInt("KOR"));
				dto.setEng(rs.getInt("ENG"));
				dto.setMat(rs.getInt("MAT"));
				dto.setTot(rs.getInt("TOT"));
				dto.setAvg(rs.getDouble("AVG"));
				dto.setRank(rs.getInt("RANK"));
				
				result.add(dto);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 인원 수 확인 담당 메소드
	public int count()
	{
		int result = 0;
		
		try
		{
			String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				result = rs.getInt("COUNT");
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 데이터 수정 담당 메소드
	public int modify(ScoreDTO dto) 
	{
		int result = 0;
		
		try
		{
			String sql = "UPDATE TBL_SCORE SET NAME = ?, KOR=?, ENG=?, MAT=? WHERE SID=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(4, dto.getMat());
			pstmt.setInt(5, dto.getSid());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//데이터 삭제 담당 메소드
	public int remove(int sid)
	{
		int result = 0;

		try
		{
			
			String sql = "DELETE FROM TBL_SCORE WHERE SID=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sid);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 데이터베이스 연결 종료 담당 메소드
	public void close()
	{
		DBConn.close();
	}
}
