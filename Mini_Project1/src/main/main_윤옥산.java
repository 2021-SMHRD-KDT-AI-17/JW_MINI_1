package main;

import java.util.Scanner;

import member.Wk_MemberDAO;
import member.Wk_MemberDTO;
import play.PlayDTO;

public class main_윤옥산 {
	public static void main(String[] args) {
	
	
	Scanner sc= new Scanner(System.in);
	Wk_MemberDAO mdao= new Wk_MemberDAO();
	Wk_MemberDTO mdto= new Wk_MemberDTO();

////	가입 테스트
//	while(true) {
//	System.out.print("가입할 아이디 입력 :");
//	String joinId=sc.nextLine();
//	mdto.setId(joinId);
//	if(!mdao.checkId(joinId)) { break;}
////	 checkId() 메서드 호출시 true= 중복 false=중복없음
//	}
//	
//	System.out.print("가입할 비밀번호 입력 :");
//	String joinPw=sc.nextLine();
//	mdto.setPw(joinPw);
//	
//	System.out.print("가입할 이름 입력 :");
//	String joinName=sc.nextLine();
//	mdto.setName(joinName);
//	mdao.wokerJoin(mdto);
	
//	=========================================

	
//	회원리스트 테스트 코드
//	ArrayList<Wk_MemberDTO> arrList= new ArrayList<>();
//	arrList=mdao.workerList();
//	
//	for(int i = 0; i<arrList.size(); i++) {
//		System.out.println("아이디 : " +arrList.get(i).getId());
//		System.out.println("비번 : " +arrList.get(i).getName());
//		System.out.println("날짜 : " +arrList.get(i).getInDate());
//	}
	
	
//	=========================================		
	
	//로그인 - 로그인시 play쪽에서 아이디 전달 받을 메소드 필요
	
	
 while(true){
	System.out.print("아이디 입력 : ");
	String logId=sc.nextLine();
	mdto.setId(logId);
	System.out.print("비밀번호 입력 : ");
	String logPw=sc.nextLine();
	mdto.setPw(logPw);
	PlayDTO pdto= new PlayDTO();
	pdto=mdao.wkLogin(mdto);
	if(pdto.getId()!=null) {
	System.out.println(pdto.getId());
	System.out.println(pdto.getHp());
	System.out.println(pdto.getMoney());
	System.out.println(pdto.getCnt_date());
	break;
	}
	
	
 }
 
 
	}
}
