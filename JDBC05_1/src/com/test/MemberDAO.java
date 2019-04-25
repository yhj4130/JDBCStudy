package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	private Connection conn;
	
	MemberDAO() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	
	// 데이터 입력 담당 메소드
	public int add(MemberDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("INSERT INTO TBL_EMP (EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG) " + 
				"VALUES (EMPSEQ.NEXTVAL, '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD'), %d, '%s', %d, %d, %d, %d)"
				, dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi(), dto.getBasicpay(), dto.getSudang());
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	// 직원 전체 출력
	public ArrayList<MemberDTO> selectAll(String orderBy)
	{
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try
		{
			
			Statement stmt = conn.createStatement();
			String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_LOC, TEL, BUSEO_NAME, JIKWI_NAME, BASICPAY, SUDANG, NVL(BASICPAY*12,0)+NVL(SUDANG,0) AS PAY FROM TBL_EMP E JOIN TBL_CITY C ON E.CITY_ID=C.CITY_ID JOIN TBL_BUSEO B ON E.BUSEO_ID = B.BUSEO_ID JOIN TBL_JIKWI J ON E.JIKWI_ID = J.JIKWI_ID ORDER BY %s", orderBy);			
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				MemberDTO dto = new MemberDTO();
				
				dto.setSid(rs.getInt("EMP_ID"));
				dto.setName(rs.getString("EMP_NAME"));
				dto.setSsn(rs.getString("SSN"));
				dto.setIbsadate(rs.getString("IBSADATE"));
				dto.setCityN(rs.getString("CITY_LOC"));
				dto.setTel(rs.getString("TEL"));
				dto.setBuseoN(rs.getString("BUSEO_NAME"));
				dto.setJikwiN(rs.getString("JIKWI_NAME"));
				dto.setBasicpay(rs.getInt("BASICPAY"));
				dto.setSudang(rs.getInt("SUDANG"));
				dto.setPay(rs.getInt("PAY"));
				
				list.add(dto);
			}
			
			rs.close();
			stmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}

		return list;
	}
	
	// 검색 출력 
	public ArrayList<MemberDTO> select(String column, String value)
	{
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try
		{
			Statement stmt = conn.createStatement();
			String sql = String.format("SELECT E.EMP_ID, E.EMP_NAME, E.SSN, E.IBSADATE, C.CITY_LOC, C.CITY_ID, E.TEL, B.BUSEO_NAME, B.BUSEO_ID, J.JIKWI_NAME, J.JIKWI_ID, E.BASICPAY, E.SUDANG, NVL(E.BASICPAY*12,0)+NVL(E.SUDANG,0) AS PAY\r\n" + 
					"FROM TBL_EMP E JOIN TBL_CITY C ON E.CITY_ID=C.CITY_ID \r\n" + 
					"               JOIN TBL_BUSEO B ON E.BUSEO_ID = B.BUSEO_ID\r\n" + 
					"               JOIN TBL_JIKWI J ON E.JIKWI_ID = J.JIKWI_ID\r\n" + 
					"WHERE %s = '%s'", column, value);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				MemberDTO dto = new MemberDTO();
				
				dto.setSid(rs.getInt("EMP_ID"));
				dto.setName(rs.getString("EMP_NAME"));
				dto.setSsn(rs.getString("SSN"));
				dto.setIbsadate(rs.getString("IBSADATE"));
				dto.setCityN(rs.getString("CITY_LOC"));
				dto.setCity(rs.getInt("CITY_ID"));
				dto.setTel(rs.getString("TEL"));
				dto.setBuseoN(rs.getString("BUSEO_NAME"));
				dto.setBuseo(rs.getInt("BUSEO_ID"));
				dto.setJikwiN(rs.getString("JIKWI_NAME"));
				dto.setJikwi(rs.getInt("JIKWI_ID"));
				dto.setBasicpay(rs.getInt("BASICPAY"));
				dto.setSudang(rs.getInt("SUDANG"));
				dto.setPay(rs.getInt("PAY"));
				
				list.add(dto);
			}
			
			rs.close();
			stmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		return list;
	}

	
	// 인원 수 확인 담당 메소드
	public int count()
	{
		int result=0;
		
		try
		{
			Statement stmt = conn.createStatement();
			String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
			
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				result = rs.getInt("COUNT");
			}
			
			rs.close();
			stmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 데이터 수정 담당 메소드
	public int modify(MemberDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("UPDATE TBL_EMP SET EMP_NAME='%s', SSN='%s', IBSADATE='%s', CITY_ID=%d, TEL='%s', BUSEO_ID=%d, JIKWI_ID=%d, BASICPAY=%d, SUDANG=%d WHERE SID='%d'"
								, dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi(), dto.getBasicpay(), dto.getSudang(), dto.getSid());
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	// 데이터 삭제 담당 메소드
	public int remove(int sid) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("DELETE FROM TBL_EMP WHERE SID=%d", sid);
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	// 데이터베이스 연결 종료 담당 메소드
	public void close() throws SQLException
	{
		DBConn.close();
	}

	public int foundID(String id, String table, String name, String num) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT %s FROM %s WHERE %s = '%s'", id, table, name, num);
		
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next())		
		{
			result = rs.getInt(id);
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}

	
	public ArrayList<String> list(String table, String column) throws SQLException 
	{
		ArrayList<String> result = new ArrayList<String>();
		
		try
		{
			Statement stmt = conn.createStatement();
			
			String sql = String.format("SELECT * FROM %s", table);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				result.add(rs.getString(column));
			}
			
			rs.close();
			stmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
	}
}
