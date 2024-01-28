package main;

import java.util.Random;
import java.util.Scanner;

import member.Wk_MemberDAO;
import member.Wk_MemberDTO;
import play.GoTohomeDAO;
import play.PlayDTO;
import play.RestDAO;
import play.SelfPlusDAO;
import play.WorkDAO;

public class main_고희청 {

	public static void main(String[] args) {
		
		Wk_MemberDAO mdao = new Wk_MemberDAO(); //  회원가입, 로그인, 랭킹 보기 기능 사용위한 생성자 선언
		Wk_MemberDTO mdto = new Wk_MemberDTO(); //  정보 담기 위한 DTO 생성자 선언
		
		
		WorkDAO wdao = new WorkDAO(); // 일하기 기능 사용위한 생성자 선언
		RestDAO rdao = new RestDAO(); // 휴식 기능 사용위한 생성자 선언
		SelfPlusDAO sdao = new SelfPlusDAO(); // 자기계발 기능사용 위한 생성자 선언
		GoTohomeDAO gdao = new GoTohomeDAO(); // 퇴근하기 기능 사용위한 생성자 선언
		PlayDTO pdto= new PlayDTO();
		
		Scanner sc = new Scanner(System.in);
		int count = 0;
		

		while(true) {
			
			System.out.println("======== 회사원의 고군분투 하루 ========");
			System.out.println("[1]회원가입 [2] 로그인 [3] 랭킹");
			int choice = sc.nextInt();
			
				if(choice ==1 ) { // **회원가입
					System.out.print("가입할 아이디 입력 :");
					String joinId=sc.next();
					mdto.setId(joinId); // true(중복), false(중복 x)로 리턴 됨
				
				if(!mdao.checkId(joinId)) {
				//checkId() 메서드 호출시 true= 중복 false=중복없음
				} 
					System.out.print("가입할 비밀번호 입력 :");
					String joinPw=sc.next();
					mdto.setPw(joinPw);

					System.out.print("가입할 이름 입력 :");
					String joinName=sc.next();
					mdto.setName(joinName);
				
					mdao.wokerJoin(mdto);
				}
				
				
				if(choice ==2) { // ** 로그인
					
					System.out.print("아이디 입력 : ");
					String logId = sc.next();
					
					System.out.print("비밀번호 입력 : ");
					String logPw = sc.next();
					
					Wk_MemberDTO logdto = new Wk_MemberDTO();
					logdto.setId(logId); // 입력받은 아이디 log_dto에 담기
					logdto.setPw(logPw); // 입력받은 패스워드 log_dto 담기
					
					pdto = mdao.wkLogin(logdto, 1); // 로그인 정보가 담긴 logdto를 pdto에 할당
					if(pdto.getId()!=null) { // ID 일치시 ID, HP, MONEY 값 출력
						System.out.print("ID : " + pdto.getId() + "\t");
						System.out.print("HP : " + pdto.getHp()+"\t");
						System.out.print("Money : " + pdto.getMoney()+ "\t");
						System.out.println("근무일수 : " + pdto.getCnt_date()+ "\n");
						
						
						System.out.println("하루 일하기3번 까지 선택 가능하며, \n "
								+ "퇴근하기를 선택해야 게임이 종료가 됩니다. \n"); // 게임룰 설명
						
						while(true) {
							

							System.out.println(" ** 회사 출근! 할 일을 골라주세요 **");
							System.out.println(" [1]일하기 [2]자기계발 [3]휴식 [4]퇴근하기");
							int select = sc.nextInt();
							
							//pdto = mdao.wkLogin(logdto, 1); // update된 테이블 worker_mohp 조회위해 생성자 생성

							
							if(select ==1) { // ** 일하기 
								pdto = mdao.wkLogin(logdto, 1);
								Random rd = new Random();
								int r = rd.nextInt(4)+1; // 1~4 랜덤수 발생
								System.out.println("랜덤 발생");
								//pdto.setId(pdto); // 로그인한 id pdto에 담기
								System.out.println(r);

								if(r==1) { // 1일때는 야근하기
								
									int cnt = wdao.overtime(pdto);
									if(cnt>0) {
										System.out.println("야근 당첨!!");
										System.out.println("이름 : " + pdto.getId() + "HP : "+ (pdto.getHp()-20)
																+ "Money : " + (pdto.getMoney()+20));
									}
								}else if(r==2) { // 2일때는 업무실수
								
									int cnt = wdao.mistake(pdto);
									
									if(cnt>0) {
										System.out.println("업무 실수!!");
										System.out.println("이름 : " + pdto.getId() + "HP : "+ (pdto.getHp()-10)
												+ "Money : " + (pdto.getMoney()+10));
									}
								}else if(r==3) { // 3일때는 혼나기
								
									int cnt = wdao.trouble(pdto);
									
									if(cnt>0) {
										System.out.println("상사한테 혼나기!!");
										System.out.println("이름 : " + pdto.getId() + "HP : "+ (pdto.getHp()-20)
												+ "Money : " + pdto.getMoney());
									}
									
								}else { // 4일때는 정상업무
									
									int cnt = wdao.normalWork(pdto);
									
									if(cnt>0) {
										System.out.println("룰루랄라 즐겁게 일하기~~ ");
										System.out.println("이름 : " + pdto.getId() + "HP : "+ pdto.getHp()
												+ "Money : " + (pdto.getMoney()+15));
									}
									
								}
								
							}
							
							
							
							if(select == 2) { // ** 자기계발 하기
								System.out.println("[1]자기계발서 읽기 [2] 1:1 컨설턴트 받기 [3]임장 다니기");
								int input = sc.nextInt();
								
								if(input ==1) {
									pdto.setId(logId); // 로그인한 id를 pdto에 담기
									
									int cnt = sdao.readBook(pdto); // SelfDAO의 readbook 메서드에 pdto 정보 매개변수로 넘기고
																  //  return 받을 값 변수 정해주기 
									if(cnt >0) {
										System.out.println("이름 : " + pdto.getId() + " HP : " + pdto.getHp()
										+ " MONEY : " + (pdto.getMoney()+50));	
									}
								}else if(input ==2) {
									
									PlayDTO consult = new PlayDTO(); // SelfDAO의 consult 메소드 실행 결과값 받아올 dto
									consult = sdao.consult(pdto); // consult메소드에서 발생시킨 난수 ran, cnt 가져옴
									if(consult.getCnt()>0) {
										System.out.println("이름 : " + pdto.getId() + " HP : " + pdto.getHp()
										+ " MONEY : " + (pdto.getMoney() + consult.getRan()));	
									}
								}else if(input ==3) {  
									int cnt = sdao.property(pdto); //SelfDAO의 property 메서드에 pdto 정보 매개변수로 넘기고
																  //  return 받을 값 변수 정해주기
									if(cnt >0) {
										System.out.println("이름 : " + pdto.getId() + " HP : " + pdto.getHp()
										+ " MONEY : " + (pdto.getMoney()+20));	
									}
								}
							
							}
							
							if (select == 3) {
								
								System.out.println("[1]취침 [2] 쇼핑 [3]식사");
								
								int input = sc.nextInt();
								
								if(input ==1) { // 취침 선택시
									int cnt= rdao.sleep(pdto); // GoTohomeDAO의 gobus 메서드에 pdto 정보 매개변수로 넘기고
															 //  return 받을 값 변수 정해주기
									if(cnt> 0) { // 
										System.out.println("이름 : "  + pdto.getId() +  " HP : " + 
												(pdto.getHp()+40) + " MONEY : " + pdto.getMoney());
										System.out.println("취침 했습니다");
										System.out.println();
									
									}
								}
								else if(input ==2) { // 쇼핑 선택시
									int cnt= rdao.shopping(pdto); //GoTohomeDAO의 gowalk 메서드에 pdto 정보 매개변수로 넘기고
																//  return 받을 값 변수 정해주기
									if(cnt > 0) {
										System.out.println("이름 : "  + pdto.getId() +  " HP : " + 
												pdto.getHp()+ " MONEY :  " + (pdto.getMoney()-30));
										System.out.println("쇼핑 했습니다");
										System.out.println();
									
									}
								}
								else if(input == 3) { // 식사 선택시
									int cnt= rdao.eat(pdto); //	GoTohomeDAO의 gotaxi 메서드에 pdto 정보 매개변수로 넘기고
																//  return 받을 값 변수 정해주기
									if(cnt > 0) {
										System.out.println("이름 : "  + pdto.getId() +  " HP : " + 
												(pdto.getHp()+30) + " MONEY :  " + (pdto.getMoney()-20));
										System.out.println("식사 했니다");
										System.out.println();
										
									}
								}
								
							}
							
							
						
							
							if(select  ==4 ) { // 퇴근하기 
								System.out.println(" [1]버스타기 [2]걸어가기 [3]택시타기"); // 로그인 성공 후 선택
								int input = sc.nextInt();
								
								if(input ==1) { // 버스 타기 선택시
									int cnt= gdao.goBus(pdto); // GoTohomeDAO의 gobus 메서드에 pdto 정보 매개변수로 넘기고
															 //  return 받을 값 변수 정해주기
									if(cnt> 0) { // 
										System.out.println("이름 : "  + pdto.getId() +  " HP : " + 
												(pdto.getHp()-20) + " MONEY : " + pdto.getMoney());
										System.out.println("퇴근을 완료했습니다");
										System.out.println();
										break;
									}
								}
								else if(input ==2) { // 걸어가기 선택시
									int cnt= gdao.goWalk(pdto); //GoTohomeDAO의 gowalk 메서드에 pdto 정보 매개변수로 넘기고
																//  return 받을 값 변수 정해주기
									if(cnt > 0) {
										System.out.println("이름 : "  + pdto.getId() +  " HP : " + 
												(pdto.getHp()-20)+ " MONEY :  " + pdto.getMoney());
										System.out.println("퇴근을 완료했습니다");
										System.out.println();
										break;
									}
								}
								else if(input == 3) { // 택시타기 선택시
									int cnt= gdao.goTaxi(pdto); //	GoTohomeDAO의 gotaxi 메서드에 pdto 정보 매개변수로 넘기고
																//  return 받을 값 변수 정해주기
									if(cnt > 0) {
										System.out.println("이름 : "  + pdto.getId() +  " HP : " + 
												pdto.getHp() + " MONEY :  " + (pdto.getMoney()-50));
										System.out.println("퇴근을 완료했습니다");
										System.out.println();
										break;
									}
								}
								
							}
							
							
						}
					
					
					
					
					}
				
			}
		}	
				
	}
}


			
		
				
		
 
	
	

