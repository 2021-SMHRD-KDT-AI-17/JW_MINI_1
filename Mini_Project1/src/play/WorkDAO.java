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
			PlayDTO pdto = new PlayDTO();
			
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

		     }
			// ===========DB 연결 종료
			//--------------------------------------------------------------------------
	
			public void hp(String id) {
				
				
				try {
					getConn();
					
					// sql 문 적기!!
					
					String sql = "update worker_mohp set hp = 100  where id = ? ";
					
					psmt = conn.prepareStatement(sql); //sql 넘겨주기!
					
					psmt.setString(1,id); // --> 매개변수로 id 받은 값으로 쿼리문 실행

					psmt.executeUpdate();

					
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					close();
				}
			
		}
	//  =============  main 출력 클래스에서 갱신된 테이블 값 받아오기 위한 메서드  ==================
			
			public PlayDTO save(String id) {
				
				
				try {
					getConn();
					
					// sql 문 적기!!
					
					String sql = "select * from worker_mohp where id = ? ";
					
					psmt = conn.prepareStatement(sql); //sql 넘겨주기!
					
					psmt.setString(1,id); // --> 매개변수로 id 받은 값으로 쿼리문 실행

					rs = psmt.executeQuery();
					
					if(rs.next()) { // 실행 됐을 때 pdto에 id, hp, money 값 setter 이용해 값을 초기화 해줌
						pdto.setId(rs.getString(1));
						pdto.setHp(rs.getInt(2));
						pdto.setMoney(rs.getInt(3));
						pdto.setCnt_date(rs.getInt(4));
						pdto.setSum_opp(rs.getInt(5));
						pdto.setWork_opp(rs.getInt(6));
					
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					close();
				}
			
				return pdto; // pdto = id, hp, money 담긴 상태로 리텅
			}
		
			//========== 야근 선택!!!		
			
			public PlayDTO  overtime(PlayDTO dto) {
				
				int cnt =0;
				
				try {
					getConn();
					
					// sql 문 적기!!
					
					String sql = "update WORKER_MoHp set  hp= ?,money =?, sum_opp = ?, work_opp= ?, cnt_date =? where id = ? ";
					
					psmt = conn.prepareStatement(sql); //sql 넘겨주기!
					
					psmt.setInt(1, dto.getHp()); // main 출력 클래스에서 받아온 dto들의 값을 가져옴
					psmt.setInt(2, dto.getMoney());
					psmt.setInt(3, dto.getSum_opp());
					psmt.setInt(4, dto.getWork_opp());
					psmt.setInt(5, dto.getCnt_date());	
					psmt.setString(6, dto.getId());
						
					cnt  = psmt.executeUpdate();
					
					if(cnt>0) {
						pdto = save(dto.getId()); // save 함수에 main 출력 클래스에서 받아온 id 값을
												  //  매개변수로 넘겨주고 실행
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					close();
				}
			
				return pdto; //  save에서 실행하고 넘겨받은 pdto 리턴
			}
			
			//========== 업무 실수 선택!!!
				
			
			public PlayDTO mistake(PlayDTO dto) {
			
				int cnt = 0;
				
				try {
					getConn();
					
					// sql 문 적기!!
					
					String sql = "update WORKER_MoHp set  hp= ?,money =?  where id = ? ";
					
					psmt = conn.prepareStatement(sql); //sql 넘겨주기!
					
					psmt.setInt(1, dto.getHp()); // main 출력 클래스에서 받아온 dto들의 값을 가져옴
					psmt.setInt(2, dto.getMoney());
					psmt.setString(3, dto.getId());
						
					cnt  = psmt.executeUpdate();
					
					if(cnt>0) {
						pdto = save(dto.getId()); // save 함수에 main 출력 클래스에서 받아온 id 값을
												  //  매개변수로 넘겨주고 실행
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					close();
				}
			
				return pdto;
			}
	
			//========== 혼나기 선택!!! 		
			
			public PlayDTO trouble(PlayDTO dto) {
				
				int cnt =0;
				
				try {
					getConn();
					
					// sql 문 적기!!
					String sql = "update WORKER_MoHp set  hp= ? where id = ? ";
					
					psmt = conn.prepareStatement(sql); //sql 넘겨주기!
					
					psmt.setInt(1, dto.getHp()); // main 출력 클래스에서 받아온 dto들의 값을 가져옴
					psmt.setString(2, dto.getId());
						
					cnt  = psmt.executeUpdate();
					
					if(cnt>0) {
						pdto = save(dto.getId()); // save 함수에 main 출력 클래스에서 받아온 id 값을
												  //  매개변수로 넘겨주고 실행
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					close();
				}
			
				return pdto;
			}
			
			//========== 정상업무 선택!!!
			
			public PlayDTO normalWork(PlayDTO dto) {
				
				int cnt =0;
				
				try {
					getConn();
					
					// sql 문 적기!!
					
					String sql = "update WORKER_MoHp set money = ? where id = ? ";
					
					psmt = conn.prepareStatement(sql); //sql 넘겨주기!
					
					psmt.setInt(1, dto.getMoney()); // main 출력 클래스에서 받아온 dto들의 값을 가져옴
					psmt.setString(2, dto.getId());
					
					cnt = psmt.executeUpdate(); 
					
					if(cnt>0) {
						pdto = save(dto.getId()); // save 함수에 main 출력 클래스에서 받아온 id 값을
						  //  매개변수로 넘겨주고 실행
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					close();
				}
			
				return pdto;
			}
		
			
			
			
			
}
