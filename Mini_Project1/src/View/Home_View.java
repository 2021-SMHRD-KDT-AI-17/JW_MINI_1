package View;

import java.util.Scanner;

import member.Wk_MemberDAO;
import member.Wk_MemberDTO;
import play.PlayDTO;

public class Home_View {
	Scanner sc = new Scanner(System.in);
	Wk_MemberDAO mdao = new Wk_MemberDAO();
	Wk_MemberDTO mdto = new Wk_MemberDTO();
	Play_View pv= new Play_View();
	PlayDTO pdto = new PlayDTO();
	
	public void startHome() {
	while (true) {
	    System.out.print("[1]회원가입 [2]로그인 [3]게임소개 [0]종료  >> ");
	    int selectNum = 0;

	    try {
	        selectNum = sc.nextInt();
	    } catch (java.util.InputMismatchException e) {
	        System.out.println("입력값 오류");
	        sc.nextLine(); // Consume the remaining invalid input
	        continue;
	    }

	    sc.nextLine(); // Consume the newline character after nextInt()

	    if (selectNum == 1) { // 회원가입
	        while (true) {
	            System.out.print("가입할 아이디 입력 : ");
	            String joinId = sc.nextLine();
	            mdto.setId(joinId);

	            if (!mdao.checkId(joinId)) {
	                break;
	            }
	            // checkId() 메서드 호출시 true= 중복 false=중복없음
	        }

	        System.out.print("가입할 비밀번호 입력 : ");
	        String joinPw = sc.nextLine();
	        mdto.setPw(joinPw);

	        System.out.print("가입할 이름 입력 : ");
	        String joinName = sc.nextLine();
	        mdto.setName(joinName);
	        mdao.wokerJoin(mdto);

	    } else if (selectNum == 2) { // 로그인
	        while (true) {
	            System.out.print("아이디 입력 : ");
	            String logId = sc.nextLine();
	            mdto.setId(logId);

	            System.out.print("비밀번호 입력 : ");
	            String logPw = sc.nextLine();
	            mdto.setPw(logPw);

	            pdto = mdao.wkLogin(mdto, 1);

	            if (pdto.getId() != null) {
	                pv.playStart(pdto);
	                // playDTO로 넘겨줄 메서드
	                break;
	            }
	        }

	    } else if (selectNum == 3) { // 게임소개
	        // 게임소개
	        System.out.println("게임소개");
	       
	    } else if (selectNum == 0) { // 종료
	        System.out.println("게임을 종료합니다. 감사합니다!");
	        break;
	    } else {
	        System.out.println("입력값 오류");
	    }
	} // while end
	}
}
