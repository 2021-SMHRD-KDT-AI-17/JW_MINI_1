package play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkDAO {

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
	
			
			public int overtime(PlayDTO dto) {
				
				int cnt =0;
				
				try {
					getConn();
					
					// sql 문 적기!!
					
					String sql = "update worker_mohp set hp = hp-20, money = money+30 where id = ?";
					
					conn.prepareStatement(sql); //sql 넘겨주기!
					
					psmt.setString(1, dto.getId()); // -->  ?  채우기!
					
					
					cnt = psmt.executeUpdate(); //--> 리턴 받아야하는게 cnt!!
					
					
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					close();
				}
			
				return cnt;
			}
			//========== 야근 선택!!!
			
			public int mistake(PlayDTO dto) {
				
				int cnt =0;
				
				try {
					getConn();
					
					// sql 문 적기!!
					
					String sql = "update worker_mohp set hp = hp-10, money = money-10 where id = ?";
					
					conn.prepareStatement(sql); //sql 넘겨주기!
					
					psmt.setString(1, dto.getId()); // -->  ?  채우기!
					
					
					cnt = psmt.executeUpdate(); //--> 리턴 받아야하는게 cnt!!
					
					
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					close();
				}
			
				return cnt;
			}
			//========== 업무 실수 선택!!!
			
			
			public int trouble(PlayDTO dto) {
				
				int cnt =0;
				
				try {
					getConn();
					
					// sql 문 적기!!
					
					String sql = "update worker_mohp set hp = hp-30 where id = ?";
					
					conn.prepareStatement(sql); //sql 넘겨주기!
					
					psmt.setString(1, dto.getId()); // -->  ?  채우기!
					
					
					cnt = psmt.executeUpdate(); //--> 리턴 받아야하는게 cnt!!
					
					
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					close();
				}
			
				return cnt;
			}
			//========== 혼나기 선택!!!
			
			
			public int normalWork(PlayDTO dto) {
				
				int cnt =0;
				
				try {
					getConn();
					
					// sql 문 적기!!
					
					String sql = "update worker_mohp set money = money+20 where id = ?";
					
					conn.prepareStatement(sql); //sql 넘겨주기!
					
					psmt.setString(1, dto.getId()); // -->  ?  채우기!
					
					
					cnt = psmt.executeUpdate(); //--> 리턴 받아야하는게 cnt!!
					
					
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					close();
				}
			
				return cnt;
			}
			//========== 정상업무 선택!!!
			
			
			
			
}
