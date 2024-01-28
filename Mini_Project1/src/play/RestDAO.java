package play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestDAO {

	//필드로 선언!! & 공통으로 사용하는 세변수는 "필드"로써 선언
		private Connection conn = null;
		private PreparedStatement psmt = null;
		private ResultSet rs = null;
			
			// 공통적으로 작성하고 있는 코드를 메서드로 분리!!
			public void getConn() {
				try {
						// [Java프로그램과 데이터 베이스를 연결하는 과정]
			           // 1. jdbc 드라이버 동적 로딩
			           // - Java 프로그램이 실행될 때 JDBC드라이버(클래스)를 불러오는 과정
			           
			           Class.forName("oracle.jdbc.driver.OracleDriver");
			           
			           String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			           String user = "campus_23K_AI17_p1_4"; // DB 연결 계정
			           String password = "smhrd4"; // DB연결 비밀번호
			           
			           // 2. 데이터베이스 연결
			           // - url, user, password 정보를 전달
			           // - 성공적으로 연결이 된 경우 Connection객체를 반환
			           // - Connection객체: DB연결, 종료, SQL실행 등의 기능을 제공하는 객체
			           conn = DriverManager.getConnection(url, user, password);
			           
			           if(conn != null) {
			              System.out.println("DB연결 성공!");
			           }else {
			              System.out.println("DB연결 실ㅍㅐ...");
			           }
				} catch (Exception e) {
					e.printStackTrace();  //오류 출력!!
				}
			}
			// ===============DB 연결까지
			
			public void close() {
				 // 4. 데이터 베이스 연결 종료(연결된 객체의 역순으로 종료
		        // - 데이터베이스 관련 작업이 끝난 경우 연결되어 있는 모든 객체는 
		        //   반드시 종료해줘야 한다. 
		        
		        try {
		           if(rs != null) {rs.close(); }
		           if(psmt != null) {psmt.close(); }
		           if(conn != null) {conn.close(); }
		        }
		        catch (SQLException e) {
		           e.printStackTrace();
		        }
		        System.out.println("DB연결 종료!");
		     }
			// ===========DB 연결 종료
			//--------------------------------------------------------------------------

			
		public int sleep(PlayDTO dto) {
				
			int cnt =0;
				
			try {
				getConn();
					
				// sql 문 적기!!
					
				String sql =  "update WORKER_MoHp set hp = (select hp from worker_MoHp where id = ?)+40 "
					     + "where id = ? ";
					
				psmt = conn.prepareStatement(sql); //sql 넘겨주기!
					
				psmt.setString(1, dto.getId()); // -->  ?  채우기!
				psmt.setString(2, dto.getId()); 	
					
				cnt = psmt.executeUpdate(); //--> 리턴 받아야하는게 cnt!!
				
				if(cnt>0) {
					System.out.println("HP가 +40 증가 했습니다!!!!!");
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				close();
			}
			
			return cnt;
		}
			//========== 취침 선택!!!		
		
		//**** 쇼핑 결과시 돈 랜덤 해야함!!
		public int shopping(PlayDTO dto) {
			
			int cnt =0;
				
			try {
				getConn();
					
				// sql 문 적기!!
					
				String sql = "update WORKER_MoHp set money = (select hp from worker_MoHp where id = ?)-30 "
					     + "where id = ? ";  //money 랜덤 변경***
					
				psmt = conn.prepareStatement(sql); //sql 넘겨주기!
					
				psmt.setString(1, dto.getId()); // -->  ?  채우기!
				psmt.setString(2, dto.getId());	
					
				cnt = psmt.executeUpdate(); //--> 리턴 받아야하는게 cnt!!
				
				if(cnt>0) {
					System.out.println("MONEY가 -30 감소 했습니다!!!!!");
				}
				
					
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				close();
			}
			
			return cnt;
		}
			//========== 쇼핑 선택!!!	
		
		public int eat(PlayDTO dto) {
			
			int cnt =0;
				
			try {
				getConn();
					
				// sql 문 적기!!
					
				String sql = "update WORKER_MoHp set hp = (select hp from worker_MoHp where id = ?)+30, "
					     + "money = (select money from worker_MoHp where id = ?) - 20 "
					     + "where id = ? ";
					
				psmt = conn.prepareStatement(sql); //sql 넘겨주기!
					
				psmt.setString(1, dto.getId()); // -->  ?  채우기!
				psmt.setString(2, dto.getId());
				psmt.setString(3, dto.getId());
					
				cnt = psmt.executeUpdate(); //--> 리턴 받아야하는게 cnt!!
				
				if(cnt>0) {
					System.out.println("HP가 +30 증가하고, money가 -20 감소 하였습니다!!!");
				}
				
					
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				close();
			}
			
			return cnt;
		}
			//========== 식사(외식) 선택!!!		
		
		
			
}
