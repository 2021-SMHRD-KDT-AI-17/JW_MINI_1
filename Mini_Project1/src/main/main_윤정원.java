package main;

import java.util.Scanner;

import member.Wk_MemberDAO;
import member.Wk_MemberDTO;
import play.GoTohomeDAO;
import play.PlayDTO;

public class main_윤정원 {

	public static void main(String[] args) {
		
		Wk_MemberDAO mdao = new Wk_MemberDAO();
		Wk_MemberDTO mdto = new Wk_MemberDTO();
		
		GoTohomeDAO gdao = new GoTohomeDAO();
		Scanner sc = new Scanner(System.in);
		int count = 0;
		
		 while(true){ // 로그인
			System.out.print("아이디 입력 : ");
			String logId=sc.nextLine();
			mdto.setId(logId);
			System.out.print("비밀번호 입력 : ");
			String logPw=sc.nextLine();
			mdto.setPw(logPw);
			
			PlayDTO pdto= new PlayDTO();
			pdto = mdao.wkLogin(mdto); // 로그인 정보가 담긴 mdto를 pdto에 할당
			if(pdto.getId()!=null) { // ID 일치시 ID, HP, MONEY 값 출력
				System.out.print("ID : " + pdto.getId() + "\t");
				System.out.print("HP : " + pdto.getHp()+"\t");
				System.out.println("Money : " + pdto.getMoney()+ "\t");
				
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
				else if(input ==2) {
					int cnt= gdao.goWalk(pdto);
					if(cnt > 0) {
						System.out.println("이름 : "  + pdto.getId() +  " HP : " + 
								(pdto.getHp()-20)+ " MONEY :  " + pdto.getMoney());
						System.out.println("퇴근을 완료했습니다");
						System.out.println();
						
					}
				}
				else if(input == 3) {
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
