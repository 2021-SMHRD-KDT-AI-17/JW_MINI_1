package main;

import member.Wk_MemberDAO;

public class main_print {

	public static void main(String[] args) {

		
		
		Wk_MemberDAO w_mdao= new Wk_MemberDAO();
		
		w_mdao.wk_getConn();
		
		w_mdao.wk_db_closd();
		
		
		
	}	
}
