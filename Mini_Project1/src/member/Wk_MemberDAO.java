package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Wk_MemberDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

//=========================================================================================	

	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe"; 
			String user = "campus_23K_AI17_p1_4"; 
			String password = "smhrd4"; 
			conn = DriverManager.getConnection(url, user, password); 
			if (conn != null) {System.out.println("DB연결 성공");
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
		System.out.println("디비Close.");
	}//closd()

//============================= 로  그  인====================================	

	public void wkLogin() {
		try {
			getConn();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closd();
		}
	}//wkLogin

//============================= 가 입 ====================================	
	public int wokerJoin(Wk_MemberDTO dto) {
		int cnt = 0;
		try {
			getConn();
			String sql = "insert into worker(id,pw,name) values(?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
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

			if (conn != null) {
				System.out.println("DB연결 성공");
			} else {
				System.out.println("DB연결실패");
			}
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

			if (conn != null) {
				System.out.println("DB연결 성공");
			} else {
				System.out.println("DB연결실패");
			}
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

			if (conn != null) {
				System.out.println("DB연결 성공");
			} else {
				System.out.println("DB연결실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closd();
		}
	}
// ==========================회원 리스트=====================================

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

}// 클레스 끝
