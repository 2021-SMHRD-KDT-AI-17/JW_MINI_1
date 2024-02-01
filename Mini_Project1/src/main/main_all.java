package main;

import java.util.Random;
import java.util.Scanner;

import member.Wk_MemberDAO;
import member.Wk_MemberDTO;
import play.PlayDTO;
import play.PLAYDAO;

public class main_all {

   public static void main(String[] args) {
      
      Wk_MemberDAO mdao = new Wk_MemberDAO(); //  회원가입, 로그인, 랭킹 보기 기능 사용위한 생성자 선언
      Wk_MemberDTO mdto = new Wk_MemberDTO(); //  정보 담기 위한 DTO 생성자 선언
      
      
      PLAYDAO pdao = new PLAYDAO(); // 일하기,휴식,자기계발, 퇴근 기능 사용위한 생성자 선언
    
      PlayDTO pdto= new PlayDTO();
      
      Scanner sc = new Scanner(System.in);
      int count = 1;
      String input_id="";
      


      ascii a = new ascii();

      a.textTitle();
      
      
      while(true) {
     	
    	  
     	 if(count == 0) { 
     		  pdao.hp(input_id); // id의 hp 속성값이 = 0 일경우
         	 System.out.println(" 과로로 쓰러졌습니다! 회복하고 오세요!");
         	 a.gameover();
         	 count = 1;
     	 }
         System.out.println("======== 환영합니다! 아래 메뉴에서 선택해주세요 ========"); // 시작 메뉴
         System.out.println("\t  [1]입사지원 [2]로그인 [3] 랭킹  \t");
         int choice = sc.nextInt();
         
            if(choice ==1 ) { // **회원가입
               System.out.print(" 이름 입력 :");
               String joinId=sc.next();
               mdto.setId(joinId); // true(중복), false(중복 x)로 리턴 됨
            
            if(!mdao.checkId(joinId)) {
            //checkId() 메서드 호출시 true= 중복 false=중복없음
          
            } 
               System.out.print("가입할 비밀번호 입력 :");
               String joinPw=sc.next();
               mdto.setPw(joinPw);
          

               mdao.wokerJoin(mdto);
            }
            
            
            if(choice ==2) { // ** 로그인
               
               System.out.print(" 이름 입력 : ");
               String logId = sc.next();
               
               System.out.print("비밀번호 입력 : ");
               String logPw = sc.next();
              	
               
               Wk_MemberDTO logdto = new Wk_MemberDTO();
               logdto.setId(logId); // 입력받은 아이디 log_dto에 담기
               logdto.setPw(logPw); // 입력받은 패스워드 log_dto 담기
               
               
               pdto = mdao.wkLogin(logdto, 1); // 로그인 정보가 담긴 logdto를 pdto에 할당
               
             
               if(pdto.getId()!=null) { // ID 일치시 ID, HP, MONEY, SUM_OPP, WORK_OPP 값 출력
            	   
            	   a.introText(); // 인트로 설명
                   a.gameRule();  // 게임룰 설명
                    
                  System.out.print("이름 : " + pdto.getId()) ;
                  System.out.print("    HP : " + pdto.getHp()+"\t");
                  System.out.print("Money : " + pdto.getMoney()+ "\t");
                  System.out.print("근무일수 : " + pdto.getCnt_date()+ "\t");
                  System.out.println("총 기회 : " + pdto.getSum_opp()+ "\t" + "일하기 기회 : "  + pdto.getWork_opp() + "\n");
               
                  
                  
//                  System.out.println("하루 일하기3번 까지 선택 가능하며, 총 5번 선택 가능합니다.\n"
//                                  + "5번 초과 수행시 또는 퇴근하기를 선택해야 게임이 종료가 됩니다. \n"); // 게임룰 설명
//                  int money = pdto.getMoney();
//                  int hp = pdto.getHp();
//                  String id = pdto.getId();
//                  int sum_opp = pdto.getSum_opp();
//                  int work_opp = pdto.getWork_opp();
                  
                  int money = pdto.getMoney();
                  int hp = pdto.getHp();
                  String id = pdto.getId();
                  int cnt_date = pdto.getCnt_date();
                  int sum_opp = pdto.getSum_opp();
                  int work_opp = pdto.getWork_opp();
                  
                  while(true) {
                	 
                	  a.bracketS(); // 대괄호 아스키코드
                	 System.out.println(" ** 회사 출근! 할 일을 골라주세요 **");
                     System.out.println(" [1]일하기 [2]자기계발 [3]휴식 [4]퇴근하기");
                     a.bracketE(); // 대괄호 아스키코드
                     int select = sc.nextInt();
                     
                     if(pdto.getHp()<=0) { // 현재 로그인된 ID의 HP 속성 값이 0이하일 경우 
                    	 hp = 100;		 	// HP = 100 으로 재할당 후 종료 -> 시작지점에서 count의 값 여부에 따라
                    	 pdto.setHp(hp);	// if문 실행
                    	 input_id = pdto.getId();
                    	 count = 0;
                    	 break;
                     }
                     

                     //pdto = mdao.wkLogin(logdto, 1); // update된 테이블 worker_mohp 조회위해 생성자 생성

                     
                     if(select ==1) { // ** 일하기 
                        
                        if(sum_opp <= 0) { // SUM_OPP의 속성값이 0이하일 경우
                        				   // SUM_OPP =5, WORK_OPP = 3으로 재할당, CNT_DATE(일한 날 수) 를 1씩 더해줌
                           sum_opp = 5;
                           work_opp =3;
                           pdto.setCnt_date(cnt_date=cnt_date+1);  
                           pdto.setSum_opp(sum_opp);
                           pdto.setWork_opp(work_opp);
                           
                           pdto = pdao.overtime(pdto);
                           System.out.println("기회가 모두 소진됐습니다! 다시 로그인 해주세요 \n");
                           break;
                        }
                        
                        if(work_opp <= 0) {	// WORK_OPP 가 0이하면 첫 화면으로
                           System.out.println("오늘은 일할 수 없습니다! \n");
                           break;
                        }
//                        
//                        sum_opp =  sum_opp-1;
//                        work_opp = work_opp-1;

                              
                        System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                         + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                         + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp());
                        
                     
                        Random rd = new Random();
                        int r = rd.nextInt(4)+1; // 1~4 랜덤수 발생
                        //System.out.println("랜덤 발생");
                        //pdto.setId(pdto); // 로그인한 id pdto에 담기
                        // System.out.println(r);

                     
        
                        if(r==1) { // 1일때는 야근하기 ( money +20 , hp - 20)
                              
                           a.overWork(); // 야근 이미지
                           money +=20;
                           pdto.setMoney(money);
                           hp -=20;
                           pdto.setHp(hp);
                           
                           sum_opp =  sum_opp-1; // 총 기회 1번 감소
                           pdto.setSum_opp(sum_opp);
                           work_opp = work_opp-1;	// 일할 기회 1번 감소
                           pdto.setWork_opp(work_opp);
   
                  
                           pdto = pdao.overtime(pdto); 
                           money = pdto.getMoney();
                           hp = pdto.getHp();
                           pdto.setHp(hp);
                           pdto.setMoney(money);
                        
                              System.out.println(" hp-20 감소, money +20 증가! ");
                              System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                               + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                               + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp() + "\n");
                           
                        }else if(r==2) { // 2일때는 업무실수 ( money -10 , hp -10)
                           
                           
                           a.mistake(); // 업무 실수 이미지 출력
                           money -=10;
                           hp -=10;
                           
                           pdto.setMoney(money);
                           pdto.setHp(hp);
                           
                           sum_opp =  sum_opp-1; // 총 기회 1번 감소
                           pdto.setSum_opp(sum_opp);
                           work_opp = work_opp-1; // 일할 기회 1번 감소
                           pdto.setWork_opp(work_opp);
   
                  
                           pdto = pdao.overtime(pdto);
                           
                           money = pdto.getMoney();
                           hp = pdto.getHp();
                           pdto.setHp(hp);
                           pdto.setMoney(money);
                           
                           pdto = pdao.overtime(pdto);
                        
                              System.out.println("hp -10, money -10 감소!");
                              System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                               + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                               + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp() + "\n");

                           
                        }else if(r==3) { // 3일때는 혼나기 ( hp-20)
                              
                           
                           a.punish(); // 혼나기 이미지
                        	
                           hp -=20;
                           pdto.setHp(hp);
                           
                           pdto.setMoney(money);
                           
                           pdto.setHp(hp);
                           sum_opp =  sum_opp-1; // 총 기회 1번 감소
                           pdto.setSum_opp(sum_opp);
                           work_opp = work_opp-1;	// 일할 기회 1번 감소
                           pdto.setWork_opp(work_opp);
   
                  
                           pdto = pdao.overtime(pdto);
                           money = pdto.getMoney();
                           hp = pdto.getHp();
                           pdto.setHp(hp);
                           pdto.setMoney(money);
                           
                           pdto = pdao.overtime(pdto);
                        
                              System.out.println(" hp-20 감소! ");
                              System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                               + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                               + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                              
                        }else { // 4일때는 정상업무 ( money +15 )
                           
                           a.workImage(); // 정상업무 이미지 출력
                           money +=15;
                           
                           pdto.setMoney(money);
                           
                           pdto.setHp(hp);
                           sum_opp =  sum_opp-1; // 총 기회 1번 감소
                           pdto.setSum_opp(sum_opp);
                           work_opp = work_opp-1; // 일할 기회 1번 감
                           pdto.setWork_opp(work_opp);
   
                  
                           pdto = pdao.overtime(pdto);
                           money = pdto.getMoney();
                           hp = pdto.getHp();
                           pdto.setHp(hp);
                           pdto.setMoney(money);
                  
                           pdto = pdao.overtime(pdto);
                        
                              System.out.println(" money +15 증가!");
                              System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                               + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                               + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                           }
                           
                        }
                     if(select == 2) { // ** 자기계발 하기
                        
                        
                        if(sum_opp <= 0) {
                           sum_opp = 5;
                           work_opp =3;
                           pdto.setCnt_date(cnt_date=cnt_date+1);  
                           pdto.setSum_opp(sum_opp);
                           pdto.setWork_opp(work_opp);
                           
                           pdto = pdao.overtime(pdto);
                           System.out.println("기회가 모두 소진됐습니다! 다시 로그인 해주세요");
                           break;
                        }
                        
                        System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                               + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : "
                                + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp());
                         //      pdto = mdao.wkLogin(logdto, 1);
                               System.out.println("[1]자기계발서 읽기 [2] 1:1 컨설턴트 받기 [3]임장 다니기");
                               int input = sc.nextInt();
                               
                               if(input ==1) { // 1 일때 자기계발서 money +30
                                     
                            	  a.readPaper(); // 자기계발서 이미지 
                            	   
                                  money +=30;
                                  pdto.setMoney(money);
                              
                                  sum_opp =  sum_opp-1;
                                  pdto.setSum_opp(sum_opp);
                        
                  
                                  pdto = pdao.overtime(pdto);
                                  money = pdto.getMoney();
                                  hp = pdto.getHp();
                                  pdto.setHp(hp);
                                  pdto.setMoney(money);
                
                         
                                  pdto = pdao.overtime(pdto);
                               
                                   System.out.println("money +30 증가!");
                                   System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                                                   + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                                                   + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                                  
                                  
                               }else if(input ==2) {  // 2 1:1 컨설트 받기  money +1~20 <랜덤>
                                  
                            	   	Random rd = new Random();
                            	   	int consultrd=rd.nextInt(20)+1;
                                  
                            	   	
                            	   	a.meet(); // 1:1 컨설트 받기 이미지
                            	   	
                            	   	money +=consultrd;
                            	   	pdto.setMoney(money);
	                
		                            sum_opp =  sum_opp-1;
			                        pdto.setSum_opp(sum_opp);
			                        
			                       
			                        pdto = pdao.overtime(pdto);
			                        money = pdto.getMoney();
			                        hp = pdto.getHp();
			                        pdto.setHp(hp);
			                        pdto.setMoney(money);
			                           
			                        pdto = pdao.overtime(pdto);
	                           
                                     System.out.println(" money "+"+"+ consultrd  +" 증가!");
                                     System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                                                    + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                                                    + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                                  
                               }else if(input ==3) {  // 3 임장다니기 money +20
                                  
                            	  a.imjang(); // 임장 다니기 
                            	  money +=20;
                                  pdto.setMoney(money);
                
                                  sum_opp =  sum_opp-1;
                                  pdto.setSum_opp(sum_opp);
                        
                         
                                  pdto = pdao.overtime(pdto);
                                  money = pdto.getMoney();
                                  hp = pdto.getHp();
                                  pdto.setHp(hp);
                                  pdto.setMoney(money);
                           

                         
                                  pdto = pdao.overtime(pdto);
                               
                                   System.out.println(" money +20 증가!");
                                   System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                                                  + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                                                  + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                                  }
                               }
                            
                            
                            
                            if (select == 3) { // ** 휴식
                               
                         
                              if(sum_opp <= 0) {
                            	  sum_opp = 5;
                            	  work_opp =3;
                            	  pdto.setCnt_date(cnt_date=cnt_date+1);  
                            	  pdto.setSum_opp(sum_opp);
	                              pdto.setWork_opp(work_opp);
	                              
	                              pdto = pdao.overtime(pdto);
	                              System.out.println("기회가 모두 소진됐습니다! 다시 로그인 해주세요");
	                              break;
                               }	
                               
                               
                               System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                                       + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                                       + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                               
                               
                               
                               System.out.println("[1]취침 [2] 쇼핑 [3]식사");
                               
                               int input = sc.nextInt();
                               
                               if(input ==1) { // 취침 선택시  hp + 40
                                  
                            	   a.sleep(); // 취침 이미지 출력
                            	   
                            	   
                            	  hp +=40;
                                  pdto.setHp(hp);
                                  
                                  sum_opp =  sum_opp-1;
                                  pdto.setSum_opp(sum_opp);
                        
                         
                                  pdto = pdao.overtime(pdto);
                                  money = pdto.getMoney();
                                  hp = pdto.getHp();
                                  pdto.setHp(hp);
                                  pdto.setMoney(money);

                                  pdto = pdao.overtime(pdto);
                               
                                   System.out.println(" hp +40 증가!");
                                   System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                                                    + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                                                    + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                                  }
                               
                               	else if(input ==2) { // 쇼핑 선택시  money 랜덤 일단 -1~-20
                                  
                                  Random rd1 = new Random();
                                  int shoprd=rd1.nextInt(20)+1;
                                  
                                  a.shopping(); // 쇼핑 이미지 출력
                                  money -=shoprd;
                                  pdto.setMoney(money);
                                  
                                  sum_opp =  sum_opp-1;
                                  pdto.setSum_opp(sum_opp);
                        
                         
                                  pdto = pdao.overtime(pdto);
                                  money = pdto.getMoney();
                                  hp = pdto.getHp();
                                  pdto.setHp(hp);
                                  pdto.setMoney(money);
                
                         
                                  pdto = pdao.overtime(pdto);
                               
                                   System.out.println("money " +"-"+shoprd+ " 감소!");
                                   System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                                                    + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                                                    + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                                  
                                  }
                               
                               else if(input == 3) { // 식사 선택시  hp +30  money -20
                                  
                            	   
                            	  a.eat(); // 식사 이미지 출력
                            	   
                                  hp +=30;
                                  money -=20;
                                  pdto.setHp(hp);
                                  pdto.setMoney(money);
                                  
                                  sum_opp =  sum_opp-1;
                                  pdto.setSum_opp(sum_opp);
                        
                         
                                  pdto = pdao.overtime(pdto);
                                  money = pdto.getMoney();
                                  hp = pdto.getHp();
                                  pdto.setHp(hp);
                                  pdto.setMoney(money);
                                  
                                  pdto = pdao.overtime(pdto);
                               
                                   System.out.println("hp +30 증가, money -20 감소!");
                                    System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                                                    + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                                                    + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                               }
                               
                            }
                            
                                 
                            if(select  ==4 ) { // ** 퇴근하기 
                               
                               if(sum_opp <= 0) {
                              sum_opp = 5;
                              work_opp =3;
                              pdto.setCnt_date(cnt_date=cnt_date+1);  
                              pdto.setSum_opp(sum_opp);
                              pdto.setWork_opp(work_opp);
                              
                              pdto = pdao.overtime(pdto);
                              System.out.println("기회가 모두 소진됐습니다! 다시 로그인 해주세요");
                              break;
                              }
                               
                          
                               System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                               + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                               + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
          
                               pdto = mdao.wkLogin(logdto, 1);
                               System.out.println(" [1]버스타기 [2]걸어가기 [3]택시타기"); // 로그인 성공 후 선택
                               int input = sc.nextInt();
                               
                               if(input ==1) { // 버스 타기 선택시  hp -10 
                                 
                            	  a.bus(); // 버스 이미지 출력
                            	   
                            	  hp -=10;
                                  pdto.setHp(hp);
                                  
                                  sum_opp =  sum_opp-1;
                                  pdto.setSum_opp(sum_opp);
                        
                         
                                  pdto = pdao.overtime(pdto);
                                  money = pdto.getMoney();
                                  hp = pdto.getHp();
                                  pdto.setHp(hp);
                                  pdto.setMoney(money);

                                  pdto = pdao.overtime(pdto);
                               
                                  System.out.println("hp -10 감소!");
                                  System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                                                 + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                                                 + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                                   break;
                                  }
                               
                               else if(input ==2) { // 걸어가기 선택시  hp -20
                                  
                                  a.walkHome(); // 걸어가기 이미지
                            	   
                            	  hp -=20;
                                  pdto.setHp(hp);

                                  sum_opp =  sum_opp-1;
                                  pdto.setSum_opp(sum_opp);
                        
                         
                                  pdto = pdao.overtime(pdto);
                                  money = pdto.getMoney();
                                  hp = pdto.getHp();
                                  pdto.setHp(hp);
                                  pdto.setMoney(money);
                                  
                                  
                                  pdto = pdao.overtime(pdto);
                               
                                     System.out.println("hp -20 감소!");
                                     System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                                                    + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                                                    + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                                     
                                     break;
                                  }
                               
                               else if(input == 3) { // 택시타기 선택시   money -50
                                  
                            	  a.taxi(); // 택시 이미지
                                  
                                  money -=50;
                                  pdto.setMoney(money);
                
                                  sum_opp =  sum_opp-1;
                                  pdto.setSum_opp(sum_opp);
                        
                         
                                  pdto = pdao.overtime(pdto);
                                  money = pdto.getMoney();
                                  hp = pdto.getHp();
                                  pdto.setHp(hp);
                                  pdto.setMoney(money);
                                  
                         
                                  pdto = pdao.overtime(pdto);
                               
                                     System.out.println("money -50 감소!");
                                     System.out.println("이름 : " + pdto.getId() + " \t" + "HP : "+ pdto.getHp()
                                                    + " \t"+ "Money : " +pdto.getMoney() + "\t" + "근무일수 : " + pdto.getCnt_date() + "\t " + "총 기회 : " 
                                                    + pdto.getSum_opp() + "\t" + "일할 기회 : " + pdto.getWork_opp()+ "\n");
                                     break;
                               }       
                            }         
                  }              
            }
         } 
          if(choice == 3) { // ** 랭킹보기
                
                System.out.println("10위까지의 랭킹 출력");
             
                mdao.workerRank();
           }                      
            
      }
   }
}

   

   
         
      
            
      
 
   
   
