/*===================
   ScoreMain.java
===================*/

/*
○ 성적 처리 → 데이터베이스 연동(데이터베이스 연결 및 액션 처리)
				ScoreDTO 클래스 활용(속성만 존재하는 클래스, getter, setter)
				ScoreDAO 클래스 활용(데이터베이스 액션 처리)
				Process 클래스 활용(단위 기능 구성)

여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
총점, 평균, 석차 등을 계산하여 출력하는 프로그램 구현.
※ 서브 메뉴 구성 → Process 클래스 활용

실행 예)

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 1

7번 학생 성적 입력(이름 국어 영어 수학) : 이원영 50 60 70
성적 입력이 완료되었습니다.

8번 학생 성적 입력(이름 국어 영어 수학) : 김선아 80 80 80
성적 입력이 완료되었습니다.

9번 학생 성적 입력(이름 국어 영어 수학) : .

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 2

전체 인원 : 8명
번호	이름	국어	영어	수학	총점	평균	석차
 1
 2
 3
 4							....
 5
 6
 7
 8
 
 =====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : -1

프로그램이 종료되었습니다.
*/

package com.test;

import java.util.Scanner;

public class ScoreMain
{
	public static void main(String[] args)
	{
		Process prc = new Process();
		
		Scanner sc = new Scanner(System.in);
		
		String menus;
		
		do
		{
			System.out.println();
			System.out.println("=====[ 성적 처리 ]=====");
			System.out.println("1. 성적 입력");
			System.out.println("2. 성적 전체 출력");
			System.out.println("3. 이름 검색 출력");
			System.out.println("4. 성적 수정");
			System.out.println("5. 성적 삭제");
			System.out.println("=======================");
			System.out.print(">> 선택(1~5), -1종료) : ");
			
			menus = sc.next();
			
			try
			{
				int menu = Integer.parseInt(menus);
				
				if (menu==-1)
				{
					System.out.println();
					System.out.println("프로그램이 종료되었습니다.");
					return;
				}
				switch (menu)
				{
				case 1:
					// 성적 입력 기능 수행
					prc.sungjukInsert(); break;
				case 2:
					// 성적 전체 출력 기능 수행
					prc.sungjukSelectAll(); break;
				case 3:
					// 이름 검색 출력 기능 수행
					prc.sungjukSearchName(); break;
				case 4:
					// 성적 수정 기능 수행
					prc.sungjukUpdate(); break;
				case 5:
					// 성적 삭제 기능 수행
					prc.sungjukDelete(); break;
				}
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		} while (true);
	}
}


// 실행 결과
/*

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 2

전체 인원 : 4명
번호	이름	국어	영어	수학	총점	평균	석차
  2     박길동    88      77      99     264    88.0       1
  3     임길동    80      81      82     243    81.0       2
  4     이길동    60      70      80     210    70.0       3
  5     김길동    70      50      90     210    70.0       3

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 1

5번 학생 성적 입력(이름 국어 영어 수학) : 윤길동 88 91 75
성적 입력이 완료되었습니다.

6번 학생 성적 입력(이름 국어 영어 수학) : .

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 2

전체 인원 : 5명
번호	이름	국어	영어	수학	총점	평균	석차
  2     박길동    88      77      99     264    88.0       1
  3     임길동    80      81      82     243    81.0       3
  4     이길동    60      70      80     210    70.0       4
  5     김길동    70      50      90     210    70.0       4
  6     윤길동    88      91      75     254    84.7       2

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 3
검색할 이름 입력 : 윤길동
번호	이름	국어	영어	수학	총점	평균	석차
  6     윤길동    88      91      75     254    84.7       2

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 4
수정할 번호를 입력하세요 : 6
번호	이름	국어	영어	수학	총점	평균	석차
  6     윤길동    88      91      75     254    84.7       2

수정 데이터 입력(이름 국어 영어 수학) : 남길동 90 99 97
수정이 완료되었습니다.

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 2

전체 인원 : 5명
번호	이름	국어	영어	수학	총점	평균	석차
  2     박길동    88      77      99     264    88.0       2
  3     임길동    80      81      82     243    81.0       3
  4     이길동    60      70      80     210    70.0       4
  5     김길동    70      50      90     210    70.0       4
  6     남길동    90      99      97     286    95.3       1

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 5
삭제할 번호를 입력하세요 : 5
번호	이름	국어	영어	수학	총점	평균	석차
  5     김길동    70      50      90     210    70.0       4

>> 정말 삭제하시겠습니까?(Y/N) : y
삭제가 완료되었습니다.

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 3
검색할 이름 입력 : 황길동
검색 결과가 존재하지 않습니다.

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 4
수정할 번호를 입력하세요 : 10
수정 대상이 존재하지 않습니다.

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : 5
삭제할 번호를 입력하세요 : 9
삭제 대상이 존재하지 않습니다.

=====[ 성적 처리 ]=====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=======================
>> 선택(1~5), -1종료) : -1

프로그램이 종료되었습니다.
*/
