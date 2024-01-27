package main;

import java.util.Scanner;

import member.Wk_MemberDAO;
import member.Wk_MemberDTO;
import play.PlayDTO;
import play.RestDAO;
import play.WorkDAO;

public class main_고희청 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		Wk_MemberDAO mdao= new Wk_MemberDAO();
		Wk_MemberDTO mdto= new Wk_MemberDTO();
		
		WorkDAO wdao = new WorkDAO();
		RestDAO rdado = new RestDAO();
		PlayDTO dto = new PlayDTO();
		
		int result=wdao.overtime(dto);
		
	}

}
