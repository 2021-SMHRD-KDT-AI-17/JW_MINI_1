package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Wk_MemberDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

//=========================================================================================	

	public void getConn() {
		try {
			// [Java 프로그램과 데이터베이스를 연결하는과정] -> JDBC
			Class.forName("oracle.jdbc.driver.OracleDriver"); // try문에 들어와야하는구만
			// 1.JDBC 드라이버 동적 로딩 = 오라클jar 라이브러리 클래스 경로 ****외우세요 뭐하는놈인지

			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe"; // 오라클 디벨로퍼 새접속시 세부정보-> 호스트이름 포트번호
			String user = "campus_23K_AI17_p1_4"; // DB연결 계정
			String password = "smhrd4"; // DB연결 비번

			// 2.데이터베이스 연결 = db경로,계정정보를 보내 DB에서 connection 객체를 반환 받음
			// - Connection객체 : DB연결,종료,SQL실행 등의 기능을 제공하는 객체

			conn = DriverManager.getConnection(url, user, password); // db연결주소 ,계정정보
			// getConnection 연결시 또 예외처리를 해야하므로 try/catch 안에 또 t/c 넣기보다는 catch 문만 추가

			if (conn != null) {
				System.out.println("DB연결 성공");
			} else {
				System.out.println("DB연결실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// =========================================================================================

	public void closd() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("디비Close.");
	}

//=========================================================================================	

	public void wkLogin() {

		try {

			getConn();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			closd();

		}

	}

	// 회사원- 회원 가입
	public int wokerJoin(Wk_MemberDTO dto) {

		int cnt = 0;
		try {
			getConn();

			String sql = "insert into worker(id,pw,name) values(?,?,?)";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getNick());
			
			cnt=psmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("취업 성공. 당장 출근하세요 !");
				return cnt;
			} else {
				System.out.println("서류 탈락.");
				return cnt;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			closd();
		}
		return cnt;
	}// 끝

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

	// =========================================================================================

	public void workerList() {

		try {
			getConn();

			String sql = "select * from worker";

			psmt = conn.prepareStatement(sql);
//			psmt.set

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

}// 클레스 끝
