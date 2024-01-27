package play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class SelfPlusDAO {

	private Connection conn =null;
	private PreparedStatement psmt =null;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs != null) {rs.close();}
			if(rs != null) {psmt.close();}
			if( conn != null) {
				conn.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int readBook(PlayDTO dto) {
			
		int cnt = 0;
		
		try {
			
			getConn();
			
			String sql = "update WORKER_MoHp set MONEY = "
						+ "(select money from worker_mohp where id = ?)+50 where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getId());
			cnt = psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("MONEY가 +50 증가 했습니다!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close();
		}
		
		return cnt;
	}
	// ======================== 자기계발서 읽기 ========================================
	
	public int consult(PlayDTO dto) {
		
		Random rd = new Random(); 
		int cnt = 0;
		int ran = rd.nextInt(49+1); // money에 더해질 랜덤수 ( 1~50 )
		
		try {
			
			getConn();
			
			String sql = "update WORKER_MoHp set MONEY = "
						+ "(select money from worker_mohp where id = ?)+? where id = ?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setInt(2, ran);
			psmt.setString(3, dto.getId());
			
			cnt = psmt.executeUpdate();
			
			if(cnt >0) {
				System.out.println("Money가 +" + ran +" 증가 하였습니다!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close();
		}
		
		return cnt;
	}
	public int property(PlayDTO dto) {
		
		int cnt = 0;
		
		try {
			
			getConn();
			
			String sql = "update WORKER_MoHp set MONEY = "
						+ "(select money from worker_mohp where id = ?)+20 where id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getId());
			cnt = psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("Money가 +20 증가했습니다!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close();
		}
		
		return cnt;
	}
		
}
