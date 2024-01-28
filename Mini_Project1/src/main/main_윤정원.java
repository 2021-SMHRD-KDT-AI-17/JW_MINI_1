package main;

import java.util.Scanner;

import member.Wk_MemberDAO;
import member.Wk_MemberDTO;
import play.GoTohomeDAO;
import play.PlayDTO;
import play.RestDAO;
import play.SelfPlusDAO;
import play.WorkDAO;

public class main_윤정원 {

	public static void main(String[] args) {
		
		Wk_MemberDAO mdao = new Wk_MemberDAO(); //  회원가입, 로그인, 랭킹 보기 기능 사용위한 생성자 선언
		Wk_MemberDTO mdto = new Wk_MemberDTO(); //  정보 담기 위한 DTO 생성자 선언
		
		
		WorkDAO wdao = new WorkDAO(); // 일하기 기능 사용위한 생성자 선언
		RestDAO rdao = new RestDAO(); // 휴식 기능 사용위한 생성자 선언
		SelfPlusDAO sdao = new SelfPlusDAO(); // 자기계발 기능사용 위한 생성자 선언
		GoTohomeDAO gdao = new GoTohomeDAO(); // 퇴근하기 기능 사용위한 생성자 선언
		
		Scanner sc = new Scanner(System.in);
		int count = 0;
		
	
		
////	가입 테스트
		while(true) {
			
			System.out.println("======== 회사원의 고군분투 하루 ========");
			System.out.println("[1]회원가입 [2] 로그인 [3] 랭킹");
			int choice = sc.nextInt();
			
				if(choice ==1 ) {
					System.out.print("가입할 아이디 입력 :");
					String joinId=sc.nextLine();
					mdto.setId(joinId);
				
				if(mdao.checkId(joinId)) { break;}
				//checkId() 메서드 호출시 true= 중복 false=중복없음
				}
	
				System.out.print("가입할 비밀번호 입력 :");
				String joinPw=sc.nextLine();
				mdto.setPw(joinPw);

				System.out.print("가입할 이름 입력 :");
				String joinName=sc.nextLine();
				mdto.setName(joinName);
				mdao.wokerJoin(mdto);
		}
	
//	===============================================

	
//	회원리스트 테스트 코드
//	ArrayList<Wk_MemberDTO> arrList= new ArrayList<>();
//	arrList=mdao.workerList();
//	
//	for(int i = 0; i<arrList.size(); i++) {
//		System.out.println("아이디 : " +arrList.get(i).getId());
//		System.out.println("비번 : " +arrList.get(i).getName());
//		System.out.println("날짜 : " +arrList.get(i).getInDate());
//	}
		
		
	
		 while(true){ // 로그인
			
			 //  로그인
			System.out.print("아이디 입력 : ");
			String logId=sc.nextLine();
			mdto.setId(logId);
			System.out.print("비밀번호 입력 : ");
			String logPw=sc.nextLine();
			mdto.setPw(logPw);
			
			
			PlayDTO pdto= new PlayDTO();
			pdto = mdao.wkLogin(mdto, 1); // 로그인 정보가 담긴 mdto를 pdto에 할당
			if(pdto.getId()!=null) { // ID 일치시 ID, HP, MONEY 값 출력
				System.out.print("ID : " + pdto.getId() + "\t");
				System.out.print("HP : " + pdto.getHp()+"\t");
				System.out.println("Money : " + pdto.getMoney()+ "\t");
				
				System.out.println("[1]회원 정보 수정 [2] 정보 삭제 [3] 플레이 하기"); // 로그인 성공시 선택
				int choice = sc.nextInt();		
				// 회원 정보 수정	
				if(choice ==1) {
					
					System.out.print("변경할 비밀번호를 입력하세요 : ");
					String upPw = sc.nextLine();
					System.out.print("변경할 이름을 입력하세요 : ");
					String upName = sc.nextLine();
				
					mdto.setPw(upPw);
					mdto.setName(upName);
					
					mdao.wokerUpdate(mdto);
					
				}
				
				else if( choice ==2 ) {
					
					System.out.print("삭제할 ID를 입력 하세요");
					String delId = sc.nextLine();					
					
					
					
					
				}
			}	
				
				
				
		while(true) {
			System.out.println("오늘 하루도 시작이다~!! 뭐 부터 시작하지??");
			System.out.println(" [1]일하기 [2]자기계발 [3]휴식 [4]퇴근하기 ");
			int choice = sc.nextInt();
			
			
			
			if(choice == 2) { // 자기계발 하기
				
	
				System.out.println("[1]자기계발서 읽기 [2] 1:1 컨설턴트 받기 [3]임장 다니기");
				int input = sc.nextInt();
				
				if(input ==1) {
					int cnt = sdao.readBook(pdto);
					if(cnt >0) {
						System.out.println("이름 : " + pdto.getId() + "HP : " + pdto.getHp()
											+ "MONEY : " + (pdto.getMoney()+30));	
					}
				}
				if(input ==2) {
					
					PlayDTO pdto2 = new PlayDTO(); // SelfDAO의 consult 메소드 실행 결과값 받아올 dto
					pdto2 = sdao.consult(pdto); // cnt와 consult메소드에서 발생시킨 난수 ran 가져옴
					if(pdto2.getCnt()>0) {
						System.out.println("이름 : " + pdto.getId() + "HP : " + pdto.getHp()
						+ "MONEY : " + (pdto.getMoney()-pdto2.getRan()));	
					}
				}
				if(input ==3) {
					int cnt = sdao.property(pdto);
					if(cnt >0) {
						System.out.println("이름 : " + pdto.getId() + "HP : " + pdto.getHp()
						+ "MONEY : " + (pdto.getMoney()+30));	
					}
				}
				
			}
			
			

			
			if(choice ==4 ) { // 퇴근하기 
				System.out.println(" [1]버스타기 [2]걸어가기 [3]택시타기"); // 로그인 성공 후 선택
				int input = sc.nextInt();
				
				if(input ==1) { // 버스 타기 선택시
					int cnt= gdao.goBus(pdto); // goBus에 pdto 자료형 넘겨주고 기능 수행
					if(cnt> 0) { // 
						System.out.println("이름 : "  + pdto.getId() +  " HP : " + 
								(pdto.getHp()-20) + " MONEY : " + pdto.getMoney());
						System.out.println("퇴근을 완료했습니다");
						System.out.println();
	
					
					}
				}
				else if(input ==2) { // 걸어가기 선택시
					int cnt= gdao.goWalk(pdto);
					if(cnt > 0) {
						System.out.println("이름 : "  + pdto.getId() +  " HP : " + 
								(pdto.getHp()-20)+ " MONEY :  " + pdto.getMoney());
						System.out.println("퇴근을 완료했습니다");
						System.out.println();
						
					}
				}
				else if(input == 3) { // 택시타기 선택시
					int cnt= gdao.goTaxi(pdto);
					if(cnt > 0) {
						System.out.println("이름 : "  + pdto.getId() +  " HP : " + 
								pdto.getHp() + " MONEY :  " + (pdto.getMoney()-50));
						System.out.println("퇴근을 완료했습니다");
						System.out.println();
					}
				}
				else {
					System.out.println("다시 선택하세요");
					}
			}
			else {
				System.out.println("다시 로그인 하세요");
			}
			}
			
			
			
			
			
		}
		}
			
		
				
		
 }
	
	

