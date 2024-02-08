package model.suggestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.util.JDBCUtil;

public class SuggestionDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	//모든 건의사항 SQL
	//건의 사항 최신순으로 정렬 하여 보내준다
	//건의 사항 중 탈되한 사용자가 있을경우 NULL이 나오기 때문에
	//NULL인 경우 탈퇴한 사용자를 붙여 가져온다
	private static final String SELECTALL = "SELECT S.SUGID, NVL(S.LOGIN_ID,'탈퇴한 사용자') AS LOGIN_ID, S.TITLE ,NVL(M.NAME,'탈퇴한 사용자') AS NAME\r\n"
			+ "			FROM SUGGESTION S\r\n"
			+ "			LEFT OUTER JOIN MEMBER M ON M.LOGIN_ID =S.LOGIN_ID\r\n"
			+ "			ORDER BY S.REG_DATE DESC";

	//모든 건의사항 SQL + 이름으로 검색
	//LIKE문으로 입력된 문자열을 포함하는 이름을 찾아 가져온다
	private static final String SELECTALL_USER_NAME="SELECT S.SUGID, NVL(S.LOGIN_ID,'탈퇴한 사용자') AS LOGIN_ID, S.TITLE ,NVL(M.NAME,'탈퇴한 사용자') AS NAME\r\n"
			+ "			FROM SUGGESTION S\r\n"
			+ "			LEFT OUTER JOIN MEMBER M ON M.LOGIN_ID =S.LOGIN_ID\r\n"
			+ "			WHERE M.NAME LIKE '%'||?||'%'\r\n"
			+ "			ORDER BY S.REG_DATE DESC";
	
	//모든 건의사항 SQL + 아이디로 검색
	//LIKE문으로 입력된 문자열을 포함하는 이름을 찾아 가져온다
	private static final String SELECTALL_USER_LOGIN_ID="SELECT S.SUGID, NVL(S.LOGIN_ID,'탈퇴한 사용자') AS LOGIN_ID, S.TITLE ,NVL(M.NAME,'탈퇴한 사용자') AS NAME\r\n"
			+ "			FROM SUGGESTION S\r\n"
			+ "			LEFT OUTER JOIN MEMBER M ON M.LOGIN_ID =S.LOGIN_ID\r\n"
			+ "			WHERE S.LOGIN_ID LIKE '%'||?||'%'\r\n"
			+ "			ORDER BY S.REG_DATE DESC";
	
	//건의 사항 상세 보기 SQL
	private static final String SELECTONE = "SELECT SUGID, NVL(LOGIN_ID,'탈퇴한 사용자') AS LOGIN_ID, TITLE,SUGGESTION FROM SUGGESTION WHERE SUGID=?";
	
	//건의사항 추가 SQL
	private static final String INSERT = "INSERT INTO SUGGESTION (SUGID, LOGIN_ID,SUGGESTION,TITLE) VALUES((SELECT NVL(MAX(SUGID),0) + 1 FROM SUGGESTION),?,?,?)";
	
	//건의사항 제거 SQL
	private static final String DELETE = "DELETE FROM SUGGESTION WHERE SUGID=?";

	public ArrayList<SuggestionDTO> selectAll(SuggestionDTO sDTO) {

		conn = JDBCUtil.connect();
		ArrayList<SuggestionDTO> datas = new ArrayList<SuggestionDTO>();
		try {
			//조회하여 가져오는 데이터는 모두 같기 때문에 하나의 while으로 사용
			if (sDTO.getSearchCondition().equals("전체조회")) {
				pstmt = conn.prepareStatement(SELECTALL);
				
			}else if(sDTO.getSearchCondition().equals("이름조회")) {
				pstmt = conn.prepareStatement(SELECTALL_USER_NAME);
				pstmt.setString(1, sDTO.getName());
				
				
			}else if(sDTO.getSearchCondition().equals("아이디조회")) {
				pstmt = conn.prepareStatement(SELECTALL_USER_LOGIN_ID);
				pstmt.setString(1, sDTO.getLoginId());
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SuggestionDTO data = new SuggestionDTO();
				data.setSugId(rs.getInt("SUGID"));
				data.setTitle(rs.getString("TITLE"));
				data.setLoginId(rs.getString("LOGIN_ID"));
				data.setName(rs.getString("NAME"));
				datas.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datas;

	}

	public SuggestionDTO selectOne(SuggestionDTO sDTO) {
		// 건의사항 상세보기
		conn = JDBCUtil.connect();
		SuggestionDTO data = null;
		try {
			pstmt = conn.prepareStatement(SELECTONE);
			pstmt.setInt(1, sDTO.getSugId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new SuggestionDTO();
				data.setSugId(rs.getInt("SUGID"));
				data.setTitle(rs.getString("TITLE"));
				data.setLoginId(rs.getString("LOGIN_ID"));
				data.setSuggestion(rs.getString("SUGGESTION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	public boolean insert(SuggestionDTO sDTO) {
		// 건의 사항 추가
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, sDTO.getLoginId());
			pstmt.setString(2, sDTO.getSuggestion());
			pstmt.setString(3, sDTO.getTitle());
			int rs = pstmt.executeUpdate();
			if (rs <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;

	}

	private boolean update(SuggestionDTO sDTO) {
		return false;
	}

	public boolean delete(SuggestionDTO sDTO) {
		//건의사항 삭제
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, sDTO.getSugId());
			int rs = pstmt.executeUpdate();
			if (rs <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

}
