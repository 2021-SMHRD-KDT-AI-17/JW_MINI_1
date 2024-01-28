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
	
	
	public int goBus(PlayDTO dto){ // 버스퇴근시 hp가 20 감소하고, cnt_date는 +1 되면서
								   // 첫 화면으로 돌아가는 기능
		int cnt = 0;
		
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
