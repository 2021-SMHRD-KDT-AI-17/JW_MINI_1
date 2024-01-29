package play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import member.Wk_MemberDTO;

public class GoTohomeDAO {
	
	
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	PlayDTO pdto = new PlayDTO();
	
	
	public void getConn() {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			String user = "campus_23K_AI17_p1_4"; // DB 연결 계정
			String password ="smhrd4"; // DB 연결 비밀번호
			conn = DriverManager.getConnection(url, user, password); 
			if (conn != null) {System.out.println("DB연결 성공");
			} else {System.out.println("DB연결실패");	}
		} catch (Exception e) {	e.printStackTrace();}
	} //getConn()
	
	// ======================================================================

	public void close() {
		try {
			if (rs != null) {rs.close();}
			if (psmt != null) {	psmt.close();}
			if (conn != null) {
				conn.close();}
		} catch (Exception e) {e.printStackTrace();	}
		System.out.println("디비Close.");
	}//closd()

	// ==========================================================================
	
	
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
	
	
	public PlayDTO goBus(PlayDTO dto){ // 버스퇴근시 hp가 20 감소하고, cnt_date는 +1 되면서
								   // 첫 화면으로 돌아가는 기능
		int cnt = 0;
		
		try {
			getConn();
		
			String sql = "update WORKER_MoHp set  hp= ?,money =?, sum_opp = ?, work_opp= ?, cnt_date =? where id = ? ";
			
			psmt = conn.prepareStatement(sql); //sql 넘겨주기!
			
			psmt.setInt(1, dto.getHp()); // main 출력 클래스에서 받아온 dto들의 값을 가져옴
			psmt.setInt(2, dto.getMoney());
			psmt.setInt(3, dto.getSum_opp());
			psmt.setInt(4, dto.getWork_opp());
			psmt.setInt(5, dto.getCnt_date());	
			psmt.setString(6, dto.getId());
			
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
	//   ======================== 버스 퇴근 ==========================================
		
	public int goWalk(PlayDTO dto) {// 걸어가기 퇴근시 hp가 20 감소하고, cnt_date는 +1 되면서
		   							// 첫 화면으로 돌아가는 기능
		
		int cnt =0;
		
		try {
			
			getConn();
			String sql = "update WORKER_MoHp set HP = "
					+ "(select hp from worker_mohp where id = ?)-20 where id = ?";
			
			psmt = conn.prepareStatement(sql);
		
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getId());
			cnt = psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("HP가 -20 감소 했습니다!");
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	public int goTaxi(PlayDTO dto) { // 택시스퇴근시 money가 50 감소하고, cnt_date는 +1 되면서
		   							 // 첫 화면으로 돌아가는 기능
		
		int cnt =0;
		
		try {
			
			getConn();
			
		
			String sql = "update WORKER_MoHp set MONEY = "
					+ "(select money from worker_mohp where id = ?)-50 where id = ?";
			
			psmt = conn.prepareStatement(sql);
		
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getId());
			cnt = psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("Money가 -50 감소 했습니다!");
				System.out.println();
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
}
