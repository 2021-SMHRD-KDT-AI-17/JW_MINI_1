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
			if (conn != null) {System.out.println("");
			} else {System.out.println("");	}
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
		System.out.println("");
	}//closd()

//============================= 로  그  인====================================	

	public PlayDTO wkLogin(Wk_MemberDTO mdto,int login_Or_Update_Delete) {
		//입력값이 1이면 로그인 2면 수정 3이면 삭제
	    try {
	    	String sql = "SELECT w.id, wm.hp, wm.money, wm.cnt_date, wm.sum_opp, wm.work_opp " +
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
				if (login_Or_Update_Delete == 1) {
					System.out.println("로그인 성공");
					pdto.setId(rs.getString(1));
					pdto.setHp(rs.getInt(2));
					pdto.setMoney(rs.getInt(3));
					pdto.setCnt_date(rs.getInt(4));
					pdto.setSum_opp(rs.getInt(5));
					pdto.setWork_opp(rs.getInt(6));
				} else if (login_Or_Update_Delete == 2 || login_Or_Update_Delete == 3) {
					System.out.println("정보확인 성공");
					pdto.setId(mdto.getId());

				}
				

				
				
			} else if (login_Or_Update_Delete == 1) {
				System.out.println("로그인 실패");
			} else if (login_Or_Update_Delete == 2 || login_Or_Update_Delete == 3) {
				System.out.println("정보확인 실패");
			}
			
	    } catch (Exception e) {e.printStackTrace();
	    } finally {closd();}
	    return pdto;
	}

//============================= 가 입 ====================================	
	public int wokerJoin(Wk_MemberDTO dto) {
		int cnt = 0;
		try {
			getConn();
			String sql = "INSERT ALL " +
		             "INTO worker(id, pw,name) VALUES(?, ?, 0) " +
		             "INTO worker_mohp(id, hp, money, cnt_date,sum_opp,work_opp) VALUES(?, ?, ?, ?,5,3) " +
		             "SELECT * FROM dual";
			 // 가입시 hp = 100, money = 0, 일한날짜 = 0 으로 초기세팅
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getId());
			psmt.setInt(4, 100);
			psmt.setInt(5, 0);
			psmt.setInt(6, 0);
			
			cnt=psmt.executeUpdate();
			if (cnt > 0) {System.out.println("취업 성공. 당장 출근하세요 !");return cnt;} 
			else {System.out.println("서류 탈락.");return cnt;}
		} catch (Exception e) {e.printStackTrace();
		} finally {closd();	}
		return cnt;
	}// wokerJoin

	
//============================= 수 정 ====================================
	
	public void wokerUpdate(Wk_MemberDTO mdto) {
		int cnt=0;
		try {
			String sql="update worker set pw=?,name=? where id=?";
			getConn();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, mdto.getPw());
			psmt.setString(2, mdto.getName());
			psmt.setString(3, mdto.getId());
			cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("수정완료");
			}else {
				System.out.println("변경되지 않음");
			}
			

		} catch (Exception e) {e.printStackTrace();
		} finally {closd();}
		
	}

	
//============================= 삭 제 ====================================	
	public void workerDelete(Wk_MemberDTO mdto) {
	    int cnt = 0;
	    try {
	        getConn();

	        // 각 DELETE 문을 별도로 처리
	        String deleteWorkerSQL = "DELETE FROM worker WHERE id=?";
	        String deleteWorkerMohpSQL = "DELETE FROM worker_mohp WHERE id=?";

	        
	        // 첫 번째 DELETE 문 실행 fk 키 먼저 삭제 
	        psmt = conn.prepareStatement(deleteWorkerMohpSQL);
	        psmt.setString(1, mdto.getId());
	        cnt = psmt.executeUpdate();

	        // 두 번째 DELETE 문 실행
	        if (cnt > 0) {  // 첫 번째 DELETE 문이 성공했을 때만 두 번째 DELETE 문 실행
	            psmt = conn.prepareStatement(deleteWorkerSQL);
	            psmt.setString(1, mdto.getId());
	            cnt = psmt.executeUpdate();

	            if (cnt > 0) {
	                System.out.println("퇴사가 완료되었습니다.");
	            } else {
	                System.out.println("퇴사가 완료되지 않았습니다.2");
	            }
	        } else {
	            System.out.println("퇴사가 완료되지 않았습니다.1");
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
			String sql="select id,money,cnt_date from worker_mohp where rownum <= 10 order by money desc";
			psmt=conn.prepareStatement(sql);	
			rs=psmt.executeQuery();
					
			System.out.println("=============랭킹확인==============");
			int cnt=0;
			while(rs.next()) {
				cnt++;
				System.out.println(cnt+"위 부자 아이디 : "+rs.getString(1));
				System.out.println("Money : "+rs.getInt(2));
				System.out.println("근무일수 : "+rs.getString(3));
				System.out.println();
			}
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
	
	// ==========================중복 체크=====================================
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
