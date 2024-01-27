package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import play.PlayDTO;

public class Wk_MemberDAO {
	

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	PlayDTO pdto = null;

//=========================================================================================	

	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe"; 
			String user = "campus_23K_AI17_p1_4"; 
			String password = "smhrd4"; 
			conn = DriverManager.getConnection(url, user, password); 
			if (conn != null) {System.out.println("\t \t \t \t \t \t \t \t \t DB연결 성공");
			} else {System.out.println("DB연결실패");	}
		} catch (Exception e) {	e.printStackTrace();}
	}//getConn()

// =========================================================================================

	public void closd() {
		try {
			if (rs != null) {rs.close();}
			if (psmt != null) {	psmt.close();}
			if (conn != null) {
				conn.close();}
		} catch (Exception e) {e.printStackTrace();	}
		System.out.println("\t \t \t \t \t \t \t \t \t 디비Close.");
	}//closd()

//============================= 로  그  인====================================	

	public PlayDTO wkLogin(Wk_MemberDTO mdto) {
	    try {
	        String sql = "SELECT w.id, wm.hp, wm.money, wm.cnt_date " +
	                     "FROM worker w " +
	                     "JOIN worker_mohp wm ON w.id = wm.id " +
	                     "WHERE w.id = ? AND w.pw = ?";

	        getConn();
	        psmt = conn.prepareStatement(sql);
	        psmt.setString(1, mdto.getId());
	        psmt.setString(2, mdto.getPw());
	        rs = psmt.executeQuery();
	        pdto= new PlayDTO();

	        if (rs.next()) {
	            System.out.println("로그인 성공");
	            
	            pdto.setId(rs.getString(1));
	            pdto.setHp(rs.getInt(2));
	            pdto.setMoney(rs.getInt(3));
	            pdto.setCnt_date(rs.getInt(4));
	            
	            
	        } else {
	            System.out.println("로그인 실패");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        closd();
	    }
	    return pdto;
	}

//============================= 가 입 ====================================	
	public int wokerJoin(Wk_MemberDTO dto) {
		int cnt = 0;
		try {
			getConn();
			String sql = "INSERT ALL " +
		             "INTO worker(id, pw, name) VALUES(?, ?, ?) " +
		             "INTO worker_mohp(id, hp, money, cnt_date) VALUES(?, ?, ?, ?) " +
		             "SELECT * FROM dual";
			 // 가입시 hp = 100, money = 0, 일한날짜 = 0 으로 초기세팅
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getId());
			psmt.setInt(5, 100);
			psmt.setInt(6, 0);
			psmt.setInt(7, 0);
			
			cnt=psmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("취업 성공. 당장 출근하세요 !");
				return cnt;
			} else {
				System.out.println("서류 탈락.");
				return cnt;
			}
		} catch (Exception e) {e.printStackTrace();
		} finally {closd();	}
		return cnt;
	}// wokerJoin

	
//============================= 수 정 ====================================	
	public void wokerUpdate() {
		try {
			closd();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closd();
		}
		
	}

	
//============================= 삭 제 ====================================	
	public void workerDelete() {
		try {
			getConn();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closd();
		}
	}


//============================= 랭 킹 ====================================	
	public void workerRank() {

		try {
			getConn();

			
			

		} catch (Exception e) {e.printStackTrace();
		} finally {closd();	}
	}
// ==========================회원 리스트=====================================
// - 메서드 호출시 아이디 이름 날짜 리턴
	public ArrayList<Wk_MemberDTO> workerList() {
		Wk_MemberDTO result=null;
		ArrayList<Wk_MemberDTO> listDTO= new ArrayList<>();
		try {
			getConn();
			String sql="select * from worker";
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();	
			while(rs.next()) {
				result = new Wk_MemberDTO();
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				String inDate=rs.getString(4);	
				result.setId(id);
				result.setName(name);
				result.setInDate(inDate);
				listDTO.add(result);
			}
	}catch(Exception e){e.printStackTrace();
	}finally {closd();}
	return listDTO;
	}//workerList()
	
	// ==========================회원 리스트=====================================
	// - 메서드 호출시 true= 중복 false=중복없음
		public boolean checkId(String joinId) {
			
			try {
		        getConn();
		        String sql = "SELECT COUNT(*) FROM worker WHERE id=?";
		        psmt = conn.prepareStatement(sql);
		        psmt.setString(1, joinId);
		        rs = psmt.executeQuery();

		        if (rs.next()) {
		            int count = rs.getInt(1);
		            if (count > 0) {
		                System.out.println("좀더 멋진 이름을 지어오게나 (중복)");
		                return true;
		            } else {
		                System.out.println("자네 이름이 좋군, 눈빛이 마음에 들어..");
		                return false;
		            }
		        }
		}catch(Exception e){e.printStackTrace();
		}finally {closd();}

			return false;
		}//workerList()

}// 클레스 끝
