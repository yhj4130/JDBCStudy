package com.test;

import java.sql.SQLException;
import java.util.Scanner;

public class Process
{
	MemberDAO dao;
	
	Scanner sc = new Scanner(System.in);
	
	
	public Process() throws ClassNotFoundException, SQLException
	{
		dao = new MemberDAO();
	}
	
	// 사원 입력 기능
	public void sawonInsert()
	{
		try
		{			
			do
			{
				System.out.println();
				System.out.println("직원 정보 입력 ---------------------------------------------------------      ");
				System.out.print("이름 : ");
				String name = sc.next();
				
				// 반복문의 조건을 깨뜨리는 수식 구성
				if(name.equals("."))
					return;
				
				System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				
				System.out.print("입사일(yyyy-mm-dd) : ");
				String ibsa = sc.next();
				
				System.out.print("지역("+print("TBL_CITY", "CITY_LOC")+") : ");
				String city = sc.next();
				
				int cityN = dao.foundID("CITY_ID", "TBL_CITY", "CITY_LOC", city);
				
				System.out.print("전화번호 : ");
				String tel = sc.next();
				
				System.out.print("부서("+print("TBL_BUSEO", "BUSEO_NAME")+") : ");
				String buseo = sc.next();
				
				int buseoN = dao.foundID("BUSEO_ID", "TBL_BUSEO", "BUSEO_NAME", buseo);
				
				System.out.print("직위("+print("TBL_JIKWI", "JIKWI_NAME")+") : ");
				String jikwi = sc.next();
				
				int jikwiN = dao.foundID("JIKWI_ID", "TBL_JIKWI", "JIKWI_NAME", jikwi);
				
				int pay = dao.foundID("MIN_BASICPAY", "TBL_JIKWI", "JIKWI_ID", String.valueOf(jikwiN));
		
				System.out.print("기본급(최소 "+ pay +"이상) : ");
				int basicpay = sc.nextInt();
				
				System.out.print("수당 : ");
				int sudang = sc.nextInt();
				
				MemberDTO dto = new MemberDTO();
				
				dto.setName(name);
				dto.setSsn(ssn);
				dto.setIbsadate(ibsa);
				dto.setCity(cityN);
				dto.setTel(tel);
				dto.setBuseo(buseoN);
				dto.setJikwi(jikwiN);
				dto.setBasicpay(basicpay);
				dto.setSudang(sudang);
				
				int result = dao.add(dto);
				
				if(result > 0)
					System.out.println("직원 정보 입력 완료 !!!");                                                                                 
				                  
			} while (true);
			
		} catch (Exception e)
		{
			System.out.println("에러발생");
			System.out.println(e.toString());
		}
		
	}
	
	public String print(String table, String column) throws SQLException 
	{
		String result = "";
		
		for (String  item : dao.list(table, column))
		{
			result = result + item +"/";
		}
		
		return result;
	}
	
	// 전체 출력
	public void array()
	{	
		System.out.println();
		System.out.println("직원 전체 출력");
		Scanner sc = new Scanner(System.in);
		int choice;
		
		System.out.println("1. 사번 정렬");
		System.out.println("2. 이름 정렬");
		System.out.println("3. 부서 정렬");
		System.out.println("4. 직위 정렬");
		System.out.println("5. 급여 내림차순 정렬");
		System.out.print(">> 선택(1~5, -1 종료) : ");
		
		choice = sc.nextInt();
		
		// 정렬
		switch (choice)
		{
		case 1:
			selectAllPrint("EMP_ID");
			break;
		case 2:
			selectAllPrint("EMP_NAME");
			break;
		case 3:
			selectAllPrint("BUSEO_NAME");
			break;
		case 4:
			selectAllPrint("JIKWI_NAME");
			break;
		case 5:
			selectAllPrint("PAY DESC");
			break;
		case -1:
			return;
		}
	}

	// 출력 기능
	public void selectAllPrint(String column)
	{
		System.out.println();
		System.out.printf("전체 인원 : %d명\n", dao.count());
		System.out.println("사번	이름	주민번호	입사일		   지역	 전화번호	부서	직위	기본급	수당	급여");

		for (MemberDTO dto : dao.selectAll(column))
		{
			System.out.printf("%3d %5s %5s %7s %4s %7s %4s %4s %9d %4d %4d\n"
					, dto.getSid(), dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCityN(), dto.getTel(), dto.getBuseoN(), dto.getJikwiN(), dto.getBasicpay(), dto.getSudang()
					, dto.getPay());
		}
	}
	
	// 검색 출력
	public void select() throws SQLException
	{
		System.out.println();
		Scanner sc = new Scanner(System.in);

		System.out.println("1. 사번 검색");
		System.out.println("2. 이름 검색");
		System.out.println("3. 부서 검색");
		System.out.println("4. 직위 검색");
		System.out.print(">> 선택(1~4, -1 종료) : ");
		int choice = sc.nextInt();
		
		System.out.println();
		
		switch (choice)
		{
		case 1:
			System.out.print("검색할 사번을 입력하세요 : ");
			selectPrint("EMP_ID");
			break;
		case 2:
			System.out.print("검색할 이름을 입력하세요 : ");
			selectPrint("EMP_NAME");
			break;
		case 3:
			System.out.print("검색할 부서를 입력하세요("+ print("TBL_BUSEO", "BUSEO_NAME") +") : ");
			selectPrint("BUSEO_NAME");
			break;
		case 4:
			System.out.print("검색할 직급을 입력하세요("+ print("TBL_JIKWI", "JIKWI_NAME") +") : ");
			selectPrint("JIKWI_NAME");
			break;
		case -1:
			return;
		}
		System.out.println();
	}
	
	// 검색 출력 print
	private void selectPrint(String selectBy)
	{	
		String input = sc.next();
		System.out.println();
		System.out.println("사번  이름     주민번호      입사일   지역    전화번호    부서  직위   기본급    수당      급여");
		
		for (MemberDTO dto : dao.select(selectBy, input))
		{
			System.out.printf("%3d %5s %5s %7s %4s %7s %4s %4s %9d %4d %4d\n"
					, dto.getSid(), dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCityN(), dto.getTel(), dto.getBuseoN(), dto.getJikwiN(), dto.getBasicpay(), dto.getSudang()
					, dto.getPay());
		}
	}
	
	// 데이터 삭제
	public void delete() throws SQLException
	{
		System.out.println();
		
		System.out.print("삭제할 직원의 사번을 입력하세요 : ");
		int check = dao.remove(sc.nextInt());
		
		if (check>0)
			System.out.println("삭제가 완료되었습니다!");
		
		System.out.println();
	}

	// 데이터 수정
	public void update() throws SQLException
	{	
		System.out.println();
	
		String inputTemp;
		System.out.print("수정할 직원의 사번을 입력하세요 : ");
		MemberDTO dto = dao.select("EMP_ID", sc.next()).get(0);
		System.out.println();
		System.out.println("사번  이름     주민번호      입사일   지역    전화번호    부서  직위   기본급    수당      급여");
		System.out.printf("%3d %5s %5s %7s %4s %7s %4s %4s %9d %4d %4d\n", dto.getSid(), dto.getName(), dto.getSsn(), dto.getIbsadate().substring(0, 10), dto.getCityN(), dto.getTel(), dto.getBuseoN(), dto.getJikwiN(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
		
		System.out.println();
		System.out.println();
		System.out.print("이름 : ");
		inputTemp = sc.next();
		
		System.out.print("지역(" + print("TBL_CITY", "CITY_LOC") + ") : ");
		inputTemp = sc.next();
		
		System.out.print("전화번호 : ");
		inputTemp = sc.next();
		
		System.out.print("부서(" + print("TBL_BUSEO", "BUSEO_NAME") + ") : ");
		inputTemp = sc.next();
		
		System.out.print("직위(" + print("TBL_JIKWI", "JIKWI_NAME") + ") : ");
		inputTemp = sc.next();
		String jikwiTemp = "";
		
		dto.setJikwi(dao.foundID("TBL_JIKWI", "JIKWI_ID", "JIKWI_NAME", inputTemp));
		jikwiTemp = inputTemp;
		
		System.out.print("기본급(최소 "+ dao.foundID("TBL_JIKWI", "MIN_BASICPAY", "JIKWI_NAME", jikwiTemp) +"원 이상) : ");
		inputTemp = sc.next();
	
		dto.setBasicpay(Integer.parseInt(inputTemp));
		
		System.out.print("수당 : ");
		inputTemp = sc.next();

		dto.setSudang(Integer.parseInt(inputTemp));
		
		int result = dao.modify(dto);
		
		if (result>0)
			System.out.println("업데이트 완료!!!");
		
		System.out.println();
	}
}
