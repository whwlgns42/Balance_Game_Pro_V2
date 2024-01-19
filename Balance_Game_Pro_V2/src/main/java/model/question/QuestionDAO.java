package model.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Util.JDBCUtil;

public class QuestionDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL_TRUE ="SELECT\r\n"
			+ "    Q.QID, Q.TITLE, C.CATEGORY,\r\n"
			+ "    COALESCE(S.SID, 0) AS SAVE_SID \r\n"
			+ "FROM\r\n"
			+ "    QUESTIONS Q\r\n"
			+ "JOIN\r\n"
			+ "    CATEGORY C ON Q.CATEGORY = C.CGID\r\n"
			+ "LEFT JOIN\r\n"
			+ "    SAVE S ON S.QID = Q.QID AND S.LOGIN_ID = '?'\r\n"
			+ "WHERE Q.Q_ACCESS = 'T'";
	private static final String SELECTALL_FALSE ="SELECT Q.QID,Q.TITLE,C.CATEGORY \r\n"
			+ "FROM QUESTIONS Q\r\n"
			+ "JOIN CATEGORY C ON Q.CATEGORY =C.CGID\r\n"
			+ "WHERE Q_ACCESS='F'";
	
	
	// 질문생성 SQL
	private static final String INSERT = "INSERT INTO QUESTIONS (QID, WRITER, TITLE, ANSWER_A, ANSWER_B, EXPLANATION) VALUES((SELECT NVL(MAX(QID),0) + 1 FROM QUESTIONS),?,?,?,?,?)";

	// TODO SELECT_ONE : 가져올 문제테이블의 정보를 무작위로 정렬해서 가져와서 맨위에 있는 한개의 행의 데이터만 조회 (랜덤으로 한개의 문제 정보 가져오기) 찜확인 추가
	private static final String SELECT_ONE_RANDOM = "SELECT COALESCE(S.SID, 0) AS SAVE_SID,\r\n"
			+ "       Q.QID, Q.TITLE, Q.ANSWER_A, Q.ANSWER_B, Q.WRITER, Q.EXPLANATION, C.CATEGORY\r\n"
			+ "FROM \r\n"
			+ "    (SELECT QID,TITLE,ANSWER_A,ANSWER_B,WRITER,EXPLANATION,CATEGORY,Q_ACCESS FROM QUESTIONS ORDER BY DBMS_RANDOM.VALUE) Q\r\n"
			+ "JOIN \r\n"
			+ "    CATEGORY C ON Q.CATEGORY = C.CGID\r\n"
			+ "LEFT JOIN\r\n"
			+ "    SAVE S ON S.QID = Q.QID AND S.LOGIN_ID = ?\r\n"
			+ "WHERE ROWNUM = 1 AND  Q.Q_ACCESS = 'T'";

	private static final String SELECT_ONE_DETAIL="SELECT Q.QID,Q.TITLE,Q.ANSWER_A,Q.ANSWER_B,Q.EXPLANATION,C.CATEGORY,\r\n"
			+ "COUNT(CASE WHEN A.ANSWER = 'A' THEN 1 END) AS COUNT_A, \r\n"
			+ "COUNT(CASE WHEN A.ANSWER = 'B' THEN 1 END) AS COUNT_B,\r\n"
			+ "NVL(S.SID, 0) AS SAVE_SID\r\n"
			+ "FROM QUESTIONS Q\r\n"
			+ "JOIN ANSWERS A ON A.QID=Q.QID\r\n"
			+ "JOIN CATEGORY C ON Q.CATEGORY = C.CGID\r\n"
			+ "LEFT JOIN\r\n"
			+ "    SAVE S ON S.QID = Q.QID AND S.LOGIN_ID = 'user'\r\n"
			+ "WHERE Q.QID=1\r\n"
			+ "GROUP BY Q.QID, Q.TITLE, Q.ANSWER_A, Q.ANSWER_B, Q.EXPLANATION, C.CATEGORY,S.SID";
	
	// 문제의 테이블의 정보를 모두 조회
	public ArrayList<QuestionDTO> selectAll(QuestionDTO qDTO) {
		
		ArrayList<QuestionDTO> datas=new ArrayList<QuestionDTO>();

		conn=JDBCUtil.connect();
		try {
			
			if (qDTO.getSearchCondition().equals("문제전체조회")) {
				// 모델
				// 승인된 문제

				pstmt=conn.prepareStatement(SELECTALL_TRUE);
				pstmt.setString(1, qDTO.getWriter());
				
				ResultSet rs=pstmt.executeQuery();

				while(rs.next()) {
					QuestionDTO data=new QuestionDTO();
					data.setqId(rs.getInt("QID"));
					data.setTitle(rs.getString("TITLE"));
					data.setS_category(rs.getString("CATEGORY"));
					data.setSave(rs.getInt("SAVE_SID"));
					datas.add(data);
				}

				rs.close();
				
			} else if (qDTO.getSearchCondition().equals("승인문제조회")) {
				// 모델
				pstmt=conn.prepareStatement(SELECTALL_FALSE);
				
				ResultSet rs=pstmt.executeQuery();

				while(rs.next()) {
					QuestionDTO data=new QuestionDTO();
					data.setqId(rs.getInt("QID"));
					data.setTitle(rs.getString("TITLE"));
					data.setS_category(rs.getString("CATEGORY"));
					datas.add(data);
				}

				rs.close();
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}

		return datas;

	}

	// 가져올 문제테이블의 정보를 무작위로 정렬해서 가져와서 맨위에 있는 한개의 행의 데이터만 조회
	public QuestionDTO selectOne(QuestionDTO qDTO) {
		QuestionDTO data = null;
		conn = JDBCUtil.connect();
		try {
		if (qDTO.getSearchCondition().equals("문제상세조회")) {
			// 박현구
			pstmt = conn.prepareStatement(SELECT_ONE_DETAIL);
			pstmt.setInt(1, qDTO.getqId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new QuestionDTO();
				data.setqId(rs.getInt("QID"));
				data.setTitle(rs.getString("TITLE"));
				
				//data.setWriter(rs.getString("WRITER"));
	
				data.setAnswer_A(rs.getString("ANSWER_A"));
				data.setAnswer_B(rs.getString("ANSWER_B"));
				
				data.setExplanation(rs.getString("EXPLANATION"));
				data.setS_category(rs.getString("CATEGORY"));
				
				data.setAnswerCntA(rs.getInt("COUNT_A"));
				data.setAnswerCntB(rs.getInt("COUNT_B"));
				data.setSave(rs.getInt("SAVE_SID"));
				
			}
			rs.close();	
			
		} else if (qDTO.getSearchCondition().equals("질문랜덤생성")) {
			// 박현구
			
				pstmt = conn.prepareStatement(SELECT_ONE_RANDOM);
				pstmt.setString(1, qDTO.getWriter());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new QuestionDTO();
					data.setqId(rs.getInt("QID"));
					data.setWriter(rs.getString("WRITER"));
					data.setTitle(rs.getString("TITLE"));
					
					data.setAnswer_A(rs.getString("ANSWER_A"));
					data.setAnswer_B(rs.getString("ANSWER_B"));
					
					data.setExplanation(rs.getString("EXPLANATION"));
					
					data.setSave(rs.getInt("SAVE_SID"));
					
					
				}
				rs.close();	
		}

		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}

	public boolean insert(QuestionDTO qDTO) {
		// 조지훈
		if (qDTO.getSearchCondition().equals("문제생성")) {
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(INSERT);
				pstmt.setString(1, qDTO.getWriter()); // 작성자 == loginId
				pstmt.setString(2, qDTO.getTitle()); // 문제제목
				pstmt.setString(3, qDTO.getAnswer_A()); // 답변A
				pstmt.setString(4, qDTO.getAnswer_B()); // 답변B
				pstmt.setString(5, qDTO.getExplanation()); // 문제설명
				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (qDTO.getSearchCondition().equals("관리자문제생성")) {
			// 모델
		}

		return true;
	}

	public boolean update(QuestionDTO qDTO) {
		if (qDTO.getSearchCondition().equals("문제수정")) {
			// 모델
		} else if (qDTO.getSearchCondition().equals("승인")) {
			// 전은주
		}

		return false;
	}

	// 댓글 삭제하기 TODO 추후 구현 예정
	public boolean delete(QuestionDTO qDTO) {
		// 문제삭제
		// 모델

		return false;
	}

}
