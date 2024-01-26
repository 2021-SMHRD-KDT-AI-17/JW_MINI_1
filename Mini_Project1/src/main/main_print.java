package main;

import member.Wk_MemberDAO;
import member.Wk_MemberDTO;

public class main_print {

	public static void main(String[] args) {

		
		
		Wk_MemberDAO mdao= new Wk_MemberDAO();
		Wk_MemberDTO mdto= new Wk_MemberDTO();

		mdto.setId("test");
		mdto.setPw("testpw");
		mdto.setNick("testNick");
		
		mdao.wokerJoin(mdto);
		
		
		
	}	
}
