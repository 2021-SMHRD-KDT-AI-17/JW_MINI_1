package main;

import View.Home_View;

public class main_윤옥산 {
	public static void main(String[] args) {
	
	
		Home_View hv=new Home_View();
		hv.startHome();

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
	
	
// while(true){
//	System.out.print("아이디 입력 : ");
//	String logId=sc.nextLine();
//	mdto.setId(logId);
//	System.out.print("비밀번호 입력 : ");
//	String logPw=sc.nextLine();
//	mdto.setPw(logPw);
//	PlayDTO pdto= new PlayDTO();
//	pdto=mdao.wkLogin(mdto);
//	if(pdto.getId()!=null) {
//	System.out.println(pdto.getId());
//	System.out.println(pdto.getHp());
//	System.out.println(pdto.getMoney());
//	System.out.println(pdto.getCnt_date());
//	break;
//	}
	
//	-=-------------랭킹
//	mdao.workerRank();
//	------------회원수정
//	System.out.println("회원수정");
//	
//	 while(true){
//	System.out.print("아이디 입력 : ");
//	String logId=sc.nextLine();
//	mdto.setId(logId);
//	
//	System.out.print("비밀번호 입력 : ");
//	String logPw=sc.nextLine();
//	mdto.setPw(logPw);
//	pdto=mdao.wkLogin(mdto,2);
//	
//	if(pdto.getId()!=null) {
//		while(true) {
//			System.out.print("변경할 비번 :");
//			String changePw=sc.nextLine();
//			System.out.println("변경할 이름 :");
//			String changeName=sc.nextLine();
//			
//			mdto.setPw(changePw);
//			mdto.setName(changeName);
//			mdto.setId(mdto.getId());
//			mdao.wokerUpdate(mdto);
//			break;
//		}
//		break;
//		}
//	 }
	 
	 
//	 회원삭제 
//		System.out.println("회원삭제");
//		 while(true){
//			System.out.println("삭제를 원하시면 계정정보 확인 해주시기 바랍니다.");
//			System.out.print("아이디 입력 : ");
//			String logId=sc.nextLine();
//			mdto.setId(logId);
//			
//			System.out.print("비밀번호 입력 : ");
//			String logPw=sc.nextLine();
//			mdto.setPw(logPw);
//			pdto=mdao.wkLogin(mdto,3);
//			
//		if(pdto.getId()!=null) {
//			while(true) {
//				
//				System.out.println("퇴사를 원할경우 , 사직서를 제출 바랍니다.");
//				System.out.println("사직 사유 : ");
//				System.out.println("퇴사 하시겠습니까? [1]예 /[2] 아니요");
//				int num=sc.nextInt();
//				if(num==1) {
//					System.out.println("언제든 기회가 열려있습니다. GoodLuck ! ! ! ! ");
//				}else if(num==2){
//					System.out.println("그래 잘 생각했어 밖은 지옥이야  .. 오늘 소주한잔 하자고 ");
//				}else {
//					System.out.println("장난치지말고 자리에 가서 일해 ");
//				}
//				mdto.setId(mdto.getId());
//				mdao.workerDelete(mdto);
//				break;
//			}
//			break;
//			}
//		 }
		 
		 
		 
		 
 
	}
}
