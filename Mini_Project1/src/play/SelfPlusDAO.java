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
		
	
	// ========== selfPlus =====================
	
	public PlayDTO selfPlus(PlayDTO dto) { // 
			
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
				
			cnt  = psmt.executeUpdate();
			
			if(cnt>0) {
				pdto = save(dto.getId()); // save 함수에 main 출력 클래스에서 받아온 id 값을
										  //  매개변수로 넘겨주고 실행
			}
			
			
//			String sql = "update WORKER_MoHp set  hp= ?,money =?  where id = ? ";
//			
//			psmt = conn.prepareStatement(sql);
//			
//			psmt.setInt(1, dto.getHp());
//			psmt.setInt(2, dto.getMoney());
//			psmt.setInt(2, dto.getMoney());
//			cnt = psmt.executeUpdate();
//			
//			
//			if(cnt>0) { // 앞의 sql 문이 실행되면 cnt_date 컬럼 값에도 1 증가 시키기
//				String cntdate_plus = "update WORKER_MoHp set CNT_DATE = "
//									+ "(select cnt_date from worker_mohp where id = ?)+1 where id = ?";
//				psmt = conn.prepareStatement(cntdate_plus);
//				
//				psmt.setString(1, dto.getId());
//				psmt.setString(2, dto.getId());
//				
//				psmt.executeUpdate();
//				System.out.println("MONEY가 +50 증가 했습니다!");				
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close();
		}
		
		return pdto; // 실행여부 리털
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
//			pdto.setCnt(cnt); // 실행 여부와 랜덤수 ran pdto로 묶어 반환위한 과정
//			pdto.setRan(ran);
		
			
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
