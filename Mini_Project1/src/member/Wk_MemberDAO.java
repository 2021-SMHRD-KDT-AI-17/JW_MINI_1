package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Wk_MemberDAO {

	Connection wk_conn= null;
	PreparedStatement wk_psmt= null;
	ResultSet wk_rs = null; 
	
	
	public void wk_getConn(){
		
		
		//ojdbc 경로 
		//URI
		//계정 ID
		//계정 비번
		

		
		try {
			//  [Java 프로그램과 데이터베이스를 연결하는과정] -> JDBC
			Class.forName("oracle.jdbc.driver.OracleDriver");			 // try문에 들어와야하는구만		
			//	1.JDBC 드라이버 동적 로딩 = 오라클jar 라이브러리 클래스 경로   ****외우세요 뭐하는놈인지
			

			String url="jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe"; // 오라클 디벨로퍼 새접속시 세부정보-> 호스트이름 포트번호
			String user = "campus_23K_AI17_p1_4"; //DB연결 계정
			String password = "smhrd4"; //DB연결 비번

			
			// 2.데이터베이스 연결 = db경로,계정정보를 보내 DB에서 connection 객체를 반환 받음 
			// - Connection객체 : DB연결,종료,SQL실행 등의 기능을 제공하는 객체 
			
			wk_conn = DriverManager.getConnection(url, user, password); // db연결주소 ,계정정보 
			
			// getConnection 연결시 또 예외처리를 해야하므로 try/catch 안에 또 t/c 넣기보다는 catch 문만 추가

	
		
			if(wk_conn != null) {
				System.out.println("DB연결 성공");
			}else {
				System.out.println("DB연결실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void wk_db_closd() {

		try {
			if(wk_rs != null) {wk_rs.close();}
			if(wk_psmt != null) {wk_psmt.close();}
			if(wk_conn != null) {wk_conn.close();}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("디비Close.");
	}
	
	
	public void wokerLogin(){
		
		try {
			
			
			wk_getConn();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			wk_db_closd();
			
			
		}
		
		
	}
	public void wokerJoin(){
		
	}
	public void wokerUpdate(){
		
	}
	public void workerDelete() {
		
	}
	public void workerRank() {
		
	}
	
	
	
	
}
