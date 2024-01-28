package View;

import java.util.Scanner;

import member.Wk_MemberDAO;
import member.Wk_MemberDTO;
import play.PlayDTO;

public class Play_View {
	Scanner sc = new Scanner(System.in);
	Wk_MemberDAO mdao = new Wk_MemberDAO();
	Wk_MemberDTO mdto = new Wk_MemberDTO();
	PlayDTO savePdto; //playStart로 넘어온 값을 유지하기위해 
//	WorkView wv = new WorkView();


//로그인>> 메인 선택창
	int selectNum = 0;
	public void playStart(PlayDTO getPdto) {
		savePdto=getPdto;
		while (true) {

            System.out.print("아이디 :"+getPdto.getId()+" ");
            System.out.print("생명력 :"+getPdto.getHp()+" ");
            System.out.print("Money :"+getPdto.getMoney()+" ");
            System.out.println("Day + :"+getPdto.getCnt_date()+" ");
			System.out.print("[1]일하기 [2]휴식 [3]자기개발 [4]쇼핑 [5]랭킹 [6]정보수정 [7]게임종료");
			  
			selectNum = sc.nextInt();

			if (selectNum == 1) {//일하기

			} else if (selectNum == 2) {// 휴식
				
				
			} else if (selectNum == 3) {// 자기개발

			} else if (selectNum == 4) {// 쇼핑

			} else if (selectNum == 5) {// 랭킹
			    mdao.workerRank();
			    System.out.println("===============================");
			    
			} else if (selectNum == 6) {// 내정보
				while (true) {
					System.out.print("[1]비번,네임수정 [2] 사직서제출 [3]돌아가기");// 돌아가기 PlayView로 돌아가야함 Pdto돌려줘야함
				    int selectNum = 0;
				        selectNum = sc.nextInt();
				    if(selectNum==1) {
				    	System.out.println("회원수정");
						String logId;
						 while(true){
							 System.out.println("??");
							 
							 System.out.print("아이디 입력: ");
							 logId = sc.next();
							 mdto.setId(logId);
							 sc.nextLine(); // 엔터 키를 소비하기 위해 추가

							 System.out.print("비밀번호 입력: ");
							 String logPw = sc.nextLine(); // 이 부분에서 엔터 키를 읽어오도록 변경
							 mdto.setPw(logPw);
							 savePdto=mdao.wkLogin(mdto,2);
						

						if(savePdto.getId()!=null) {
							while(true) {
								System.out.print("변경할 비번 :");
								String changePw=sc.nextLine();
								System.out.println("변경할 이름 :");
								String changeName=sc.nextLine();
								
								mdto.setPw(changePw);
								mdto.setName(changeName);
								mdto.setId(mdto.getId());
								mdao.wokerUpdate(mdto);
								break;
							}
							
							}else {
								System.out.println("들어온 pdto 값이 없음");
							}
						break;
						 }
				    	
				    }else if(selectNum==2) {
				    	
//				   	 회원삭제 
						System.out.println("회원삭제");
						 while(true){
							System.out.println("삭제를 원하시면 계정정보 확인 해주시기 바랍니다.");
							System.out.print("아이디 입력 : ");
							String logId=sc.next();
							mdto.setId(logId);
							sc.nextLine();
							
							System.out.print("비밀번호 입력 : ");
							String logPw=sc.nextLine();
							mdto.setPw(logPw);
							savePdto=mdao.wkLogin(mdto,3);
							
						if(savePdto.getId()!=null) {
							while(true) {
								
								System.out.println("퇴사 하시겠습니까? [1]예 /[2] 아니요");
								int num=sc.nextInt();
								if(num==1) {
									mdto.setId(mdto.getId());
									mdao.workerDelete(mdto);
									System.out.println("언제든 기회가 열려있습니다. GoodLuck ! ! ! ! ");
									System.exit(0);
								}else if(num==2){
									System.out.println("그래 잘 생각했어 밖은 지옥이야  .. 오늘 소주한잔 하자고 ");
									break;
								}else {
									System.out.println("장난치지말고 자리에 가서 일해 ");
									break;
								}
								
								break;
							}
							break;
							}
						 }
						
				    }else if(selectNum==3) {
				    	break;
				    }
				}

				
			} else if(selectNum==7){
				System.out.println("굿바이");
				System.out.println("종료되었습니다");
				System.exit(0);
			}
			    
		}
	}
	
	


	
	
	
	
	public void UpdateMember_View() {

		
		}
}
