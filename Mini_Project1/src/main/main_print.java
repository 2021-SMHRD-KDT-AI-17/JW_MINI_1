package main;

import java.util.ArrayList;

import member.Wk_MemberDAO;
import member.Wk_MemberDTO;

public class main_print {

	public static void main(String[] args) {

		
		
		Wk_MemberDAO mdao= new Wk_MemberDAO();
		Wk_MemberDTO mdto= new Wk_MemberDTO();

		mdto.setId("444id");
		mdto.setPw("444pw");
		mdto.setName("444닉");
		
		mdao.wokerJoin(mdto);
		
		ArrayList<Wk_MemberDTO> arrList= new ArrayList<>();

		arrList=mdao.workerList();
		
		for(int i = 0; i<arrList.size(); i++) {
			System.out.println("아이디 : " +arrList.get(i).getId());
			System.out.println("비번 : " +arrList.get(i).getName());
			System.out.println("날짜 : " +arrList.get(i).getInDate());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}	
}
