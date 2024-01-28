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
	
	
	public int readBook(PlayDTO dto) { // 자기계발서 읽기 선택시 money가 50 증가
			
		int cnt = 0;
		
		try {
			
			getConn();
			
			String sql = "update WORKER_MoHp set MONEY = "
						+ "(select money from worker_mohp where id = ?)+50 where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getId());
			cnt = psmt.executeUpdate();
			
			
			if(cnt>0) { // 앞의 sql 문이 실행되면 cnt_date 컬럼 값에도 1 증가 시키기
				String cntdate_plus = "update WORKER_MoHp set CNT_DATE = "
									+ "(select cnt_date from worker_mohp where id = ?)+1 where id = ?";
				psmt = conn.prepareStatement(cntdate_plus);
				
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getId());
				
				psmt.executeUpdate();
				System.out.println("MONEY가 +50 증가 했습니다!");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close();
		}
		
		return cnt; // 실행여부 리털
	}
	// ======================== 자기계발서 읽기 ========================================
	
	public PlayDTO consult(PlayDTO dto) { // 1:1 컨설턴트시 1~50 사이 랜덤으로 money가 증가
		
		Random rd = new Random();
		PlayDTO pdto = new PlayDTO();
		int cnt = 0;
		int ran = rd.nextInt(49+1); // money에 더해질 랜덤수 ( 1~50 )
		
		try {
			
			getConn();
			
			String sql= "update WORKER_MoHp set MONEY = " // worker_mohp money 컬럼에 1~50 랜덤수 증가시키기
							 + "(select money from worker_mohp where id = ?)+? where id = ?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setInt(2, ran);
			psmt.setString(3, dto.getId());
			
			cnt = psmt.executeUpdate();
			
			if(cnt>0) { // 앞의 sql 문이 실행되면 cnt_date 컬럼 값에도 1 증가 시키기
				String cntdate_plus = "update WORKER_MoHp set CNT_DATE = "
									+ "(select cnt_date from worker_mohp where id = ?)+1 where id = ?";
				psmt = conn.prepareStatement(cntdate_plus);
				
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getId());
				
				psmt.executeUpdate();
			
				System.out.println("Money가 +" + ran +" 증가 하였습니다!");
			}
			pdto.setCnt(cnt); // 실행 여부와 랜덤수 ran pdto로 묶어 반환위한 과정
			pdto.setRan(ran);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close();
		}
		
		return pdto; // pdto 반환
	}
	public int property(PlayDTO dto) { // 임장 다니기 선택시, money가 20 증가
		
		int cnt = 0;
		
		try {
			
			getConn();

			
			String sql = "update WORKER_MoHp set MONEY = "
						+ "(select money from worker_mohp where id = ?)+20 where id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getId());
			cnt = psmt.executeUpdate();
			
			if(cnt>0) { // 앞의 sql 문이 실행되면 cnt_date 컬럼 값에도 1 증가 시키기
				String cntdate_plus = "update WORKER_MoHp set CNT_DATE = "
									+ "(select cnt_date from worker_mohp where id = ?)+1 where id = ?";
				psmt = conn.prepareStatement(cntdate_plus);
				
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getId());
				
				psmt.executeUpdate();
				
				System.out.println("Money가 +20 증가했습니다!");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close();
		}
		
		return cnt; // 실행여부 리털
	}
		
}
