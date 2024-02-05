package model.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.util.JDBCUtil;

public class QuestionDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	private static final String SELECTALL_TRUE = "SELECT Q.QID, Q.TITLE, C.CATEGORY, NVL(S.SID, 0) AS SAVE_SID \r\n"
			+ "FROM QUESTIONS Q \r\n"
			+ "JOIN CATEGORY C ON Q.CATEGORY = C.CGID \r\n"
			+ "LEFT OUTER JOIN SAVE S ON S.QID = Q.QID AND S.LOGIN_ID = ?\r\n"
			+ "WHERE Q.Q_ACCESS = 'T' ORDER BY Q.QID ASC";
	
	private static final String SELECTALL_FALSE = "SELECT Q.QID,Q.TITLE,C.CATEGORY \r\n" + "FROM QUESTIONS Q\r\n"
			+ "JOIN CATEGORY C ON Q.CATEGORY =C.CGID\r\n" + "WHERE Q_ACCESS='F'";

	
	  private static final String SELECTALL_CRAWLLING = "SELECT Q.QID, Q.TITLE, Q.LOGIN_ID, Q.ANSWER_A, Q.ANSWER_B , EXPLANATION FROM QUESTIONS Q";
	   
	   private static final String SELECTALL_ADMIN_TRUE = "SELECT Q.QID, Q.TITLE, Q.LOGIN_ID, Q.ANSWER_A, Q.ANSWER_B, EXPLANATION, REG_DATE FROM QUESTIONS Q WHERE Q_ACCESS = 'T' ";
	   
	   private static final String SELECTALL_ADMIN_FALSE = "SELECT Q.QID, Q.TITLE, Q.LOGIN_ID, Q.ANSWER_A, Q.ANSWER_B, EXPLANATION, REG_DATE FROM QUESTIONS Q WHERE Q_ACCESS = 'F' ";


	
	
	// 질문생성 SQL
	private static final String INSERT = "INSERT INTO QUESTIONS (QID, LOGIN_ID, TITLE, ANSWER_A, ANSWER_B, EXPLANATION) VALUES((SELECT NVL(MAX(QID),0) + 1 FROM QUESTIONS),?,?,?,?,?)";

	private static final String INSERT_ADMIN = "INSERT INTO QUESTIONS (QID, LOGIN_ID, TITLE, ANSWER_A, ANSWER_B, EXPLANATION,CATEGORY,Q_ACCESS) \r\n"
			+ "VALUES((SELECT NVL(MAX(QID),0) + 1 FROM QUESTIONS),?,?,?,?,?,?,'T')";

	// TODO SELECT_ONE : 가져올 문제테이블의 정보를 무작위로 정렬해서 가져와서 맨위에 있는 한개의 행의 데이터만 조회 (랜덤으로
	// 한개의 문제 정보 가져오기) 찜확인 추가
	private static final String SELECT_ONE_RANDOM = "SELECT NVL(S.SID, 0) AS SAVE_SID,\r\n"
			+ "       Q.QID, Q.TITLE, Q.ANSWER_A, Q.ANSWER_B, Q.LOGIN_ID, Q.EXPLANATION, C.CATEGORY\r\n"
			+ "FROM \r\n"
			+ "    (SELECT QID,TITLE,ANSWER_A,ANSWER_B,LOGIN_ID,EXPLANATION,CATEGORY,Q_ACCESS FROM QUESTIONS ORDER BY DBMS_RANDOM.VALUE) Q\r\n"
			+ "LEFT OUTER JOIN \r\n"
			+ "    CATEGORY C ON Q.CATEGORY = C.CGID\r\n"
			+ "LEFT OUTER JOIN\r\n"
			+ "    SAVE S ON S.QID = Q.QID AND S.LOGIN_ID = ?\r\n"
			+ "WHERE ROWNUM = 1 AND  Q.Q_ACCESS = 'T'";

	private static final String SELECT_ONE_DETAIL = "SELECT Q.QID,Q.TITLE,Q.ANSWER_A,Q.ANSWER_B,Q.EXPLANATION,C.CATEGORY,Q.CATEGORY AS CATEGORY_PK,\r\n"
			+ "COUNT(CASE WHEN A.ANSWER = 'A' THEN 1 END) AS COUNT_A, \r\n"
			+ "COUNT(CASE WHEN A.ANSWER = 'B' THEN 1 END) AS COUNT_B,\r\n" + "NVL(S.SID, 0) AS SAVE_SID\r\n"
			+ "FROM QUESTIONS Q\r\n" + "JOIN ANSWERS A ON A.QID=Q.QID\r\n"
			+ "JOIN CATEGORY C ON Q.CATEGORY = C.CGID\r\n" + "LEFT JOIN\r\n"
			+ "    SAVE S ON S.QID = Q.QID AND S.LOGIN_ID = ?\r\n" + "WHERE Q.QID=?\r\n"
			+ "GROUP BY Q.QID, Q.TITLE, Q.ANSWER_A, Q.ANSWER_B, Q.EXPLANATION, C.CATEGORY,S.SID,Q.CATEGORY";

	 private static final String SELECT_ONE_ADMIN = "SELECT QID, TITLE, LOGIN_ID, ANSWER_A, ANSWER_B, EXPLANATION, CATEGORY, REG_DATE, Q_ACCESS FROM QUESTIONS Q WHERE Qid = ? ";

	
	private static final String UPDATE = "UPDATE QUESTIONS \r\n"
			+ "SET TITLE=?,ANSWER_A=?,ANSWER_B=?,CATEGORY=? \r\n" + "WHERE QID=?";

	   private static final String UPDATE_ACCESS = "UPDATE QUESTIONS SET Q_ACCESS='T' WHERE QID=?";
	   

		// 회원탈퇴시 'Question'을 null 값으로 변경
		private static final String QS_UPDATE = "UPDATE QUESTIONS SET LOGIN_ID = NULL WHERE LOGIN_ID = ?";
	   
	private static final String DELETE="DELETE FROM QUESTIONS WHERE QID=?";
	
	//문제 개수
	private static final String SELECT_CNT="SELECT COUNT(1) AS CNT FROM QUESTIONS WHERE Q_ACCESS=?";
	
	// 문제의 테이블의 정보를 모두 조회
	public ArrayList<QuestionDTO> selectAll(QuestionDTO qDTO) {

		ArrayList<QuestionDTO> datas = new ArrayList<QuestionDTO>();

		conn = JDBCUtil.connect();
		try {

			if (qDTO.getSearchCondition().equals("문제전체조회")) {
				// 모델
				// 승인된 문제

				pstmt = conn.prepareStatement(SELECTALL_TRUE);
				pstmt.setString(1, qDTO.getLoginId());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					QuestionDTO data = new QuestionDTO();
					data.setqId(rs.getInt("QID"));
					data.setTitle(rs.getString("TITLE"));
					data.setS_category(rs.getString("CATEGORY"));
					data.setSave(rs.getInt("SAVE_SID"));
					datas.add(data);
				}

				rs.close();

			} else if (qDTO.getSearchCondition().equals("승인문제조회")) {
				// 모델
				pstmt = conn.prepareStatement(SELECTALL_FALSE);

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					QuestionDTO data = new QuestionDTO();
					data.setqId(rs.getInt("QID"));
					data.setTitle(rs.getString("TITLE"));
					data.setS_category(rs.getString("CATEGORY"));
					datas.add(data);
				}

				rs.close();
			}else if(qDTO.getSearchCondition().equals("크롤링")){
	            System.out.println("로그 qDAO: 크롤링");
	            //크롤링 조회
	            pstmt = conn.prepareStatement(SELECTALL_CRAWLLING);
	            ResultSet rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	               QuestionDTO data = new QuestionDTO();
	               data.setAnswer_A(rs.getString("ANSWER_A"));
	               data.setAnswer_B(rs.getString("ANSWER_B"));
	               data.setqId(rs.getInt("QID"));
	               data.setTitle(rs.getString("TITLE"));
	               data.setLoginId(rs.getString("LOGIN_ID"));
	               data.setExplanation(rs.getString("EXPLANATION"));
	               datas.add(data);
	            }
	            rs.close();
	         }else if(qDTO.getSearchCondition().equals("관리자문제전체조회")) {
	            pstmt = conn.prepareStatement(SELECTALL_ADMIN_TRUE);
	            ResultSet rs = pstmt.executeQuery();
	            while(rs.next()) {
	               QuestionDTO data = new QuestionDTO();
	               data.setAnswer_A(rs.getString("ANSWER_A"));
	               data.setAnswer_B(rs.getString("ANSWER_B"));
	               data.setqId(rs.getInt("QID"));
	               data.setTitle(rs.getString("TITLE"));
	               data.setLoginId(rs.getString("LOGIN_ID"));
	               data.setExplanation(rs.getString("EXPLANATION"));
	               
	               datas.add(data);
	            }
	            rs.close();
	         }else if(qDTO.getSearchCondition().equals("관리자승인문제조회")) {
	            pstmt = conn.prepareStatement(SELECTALL_ADMIN_FALSE);
	            ResultSet rs = pstmt.executeQuery();
	            while(rs.next()) {
	               QuestionDTO data = new QuestionDTO();
	               data.setAnswer_A(rs.getString("ANSWER_A"));
	               data.setAnswer_B(rs.getString("ANSWER_B"));
	               data.setqId(rs.getInt("QID"));
	               data.setTitle(rs.getString("TITLE"));
	               data.setLoginId(rs.getString("LOGIN_ID"));
	               data.setExplanation(rs.getString("EXPLANATION"));
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
				pstmt.setString(1, qDTO.getLoginId()); // 작성자x , 로그인ID o 
				pstmt.setInt(2, qDTO.getqId());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new QuestionDTO();
					data.setqId(rs.getInt("QID"));
					data.setTitle(rs.getString("TITLE"));

					// data.setLOGIN_ID(rs.getString("LOGIN_ID"));

					data.setAnswer_A(rs.getString("ANSWER_A"));
					data.setAnswer_B(rs.getString("ANSWER_B"));

					data.setExplanation(rs.getString("EXPLANATION"));
					data.setS_category(rs.getString("CATEGORY"));
					data.setCategory(rs.getInt("CATEGORY_PK"));

					data.setAnswerCntA(rs.getInt("COUNT_A"));
					data.setAnswerCntB(rs.getInt("COUNT_B"));
					data.setSave(rs.getInt("SAVE_SID"));

				}
				rs.close();

			} else if (qDTO.getSearchCondition().equals("질문랜덤생성")) {
				// 박현구

				pstmt = conn.prepareStatement(SELECT_ONE_RANDOM);
				pstmt.setString(1, qDTO.getLoginId());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new QuestionDTO();
					data.setqId(rs.getInt("QID"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setTitle(rs.getString("TITLE"));

					data.setAnswer_A(rs.getString("ANSWER_A"));
					data.setAnswer_B(rs.getString("ANSWER_B"));

					data.setExplanation(rs.getString("EXPLANATION"));

					data.setSave(rs.getInt("SAVE_SID"));

				}
				rs.close();
			}else if(qDTO.getSearchCondition().equals("관리자문제상세조회")) {
	            pstmt = conn.prepareStatement(SELECT_ONE_ADMIN);
	            pstmt.setInt(1, qDTO.getqId());
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	               data = new QuestionDTO();
	               data.setqId(rs.getInt("QID"));
	               data.setLoginId(rs.getString("LOGIN_ID"));
	               data.setTitle(rs.getString("TITLE"));
	               data.setAnswer_A(rs.getString("ANSWER_A"));
	               data.setAnswer_B(rs.getString("ANSWER_B"));
	               data.setExplanation(rs.getString("EXPLANATION"));
	               data.setCategory(rs.getInt("CATEGORY"));
	               data.setqAccess(rs.getString("Q_ACCESS"));
	            }
	         }else if(qDTO.getSearchCondition().equals("문제승인갯수")) {
	        	 pstmt = conn.prepareStatement(SELECT_CNT);
	        	 pstmt.setString(1, qDTO.getqAccess());
	        	 ResultSet rs = pstmt.executeQuery();
	        	 if (rs.next()) {
		               data = new QuestionDTO();
		               data.setCnt(rs.getInt("CNT"));
		            }
	         }else if(qDTO.getSearchCondition().equals("총문제수")) {
	        	 pstmt = conn.prepareStatement(SELECT_CNT);
	        	 pstmt.setString(1, qDTO.getqAccess());
	        	 ResultSet rs = pstmt.executeQuery();
	        	 if (rs.next()) {
		               data = new QuestionDTO();
		               data.setCnt(rs.getInt("CNT"));
		            }
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
		conn = JDBCUtil.connect();
		try {
			if (qDTO.getSearchCondition().equals("문제생성")) {

				pstmt = conn.prepareStatement(INSERT);
				pstmt.setString(1, qDTO.getLoginId()); // 작성자 == loginId
				pstmt.setString(2, qDTO.getTitle()); // 문제제목
				pstmt.setString(3, qDTO.getAnswer_A()); // 답변A
				pstmt.setString(4, qDTO.getAnswer_B()); // 답변B
				pstmt.setString(5, qDTO.getExplanation()); // 문제설명
				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}

			} else if (qDTO.getSearchCondition().equals("관리자문제생성")) {
				// 모델
				pstmt = conn.prepareStatement(INSERT_ADMIN);
				pstmt.setString(1, qDTO.getLoginId()); // 작성자 == loginId
				pstmt.setString(2, qDTO.getTitle()); // 문제제목
				pstmt.setString(3, qDTO.getAnswer_A()); // 답변A
				pstmt.setString(4, qDTO.getAnswer_B()); // 답변B
				pstmt.setString(5, qDTO.getExplanation()); // 문제설명
				pstmt.setInt(6, qDTO.getCategory());// 카테고리
				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean update(QuestionDTO qDTO) {

		conn = JDBCUtil.connect();
		try {

			if (qDTO.getSearchCondition().equals("문제수정")) {
				// 모델
				pstmt=conn.prepareStatement(UPDATE);
				pstmt.setString(1, qDTO.getTitle());
				pstmt.setString(2, qDTO.getAnswer_A());
				pstmt.setString(3, qDTO.getAnswer_B());
				pstmt.setInt(4, qDTO.getCategory());
				pstmt.setInt(5, qDTO.getqId());
				int rs=pstmt.executeUpdate();
				if(rs<=0) {
					return false;
				}
			} else if (qDTO.getSearchCondition().equals("승인")) {
	            // 전은주
	            pstmt = conn.prepareStatement(UPDATE_ACCESS);
	            pstmt.setInt(1, qDTO.getqId());
	            int rs = pstmt.executeUpdate();
	            if(rs <=0) {
	               return false;
	            }
			} else if (qDTO.getSearchCondition().equals("question_null")) {
				// 손성용
				pstmt = conn.prepareStatement(QS_UPDATE);
				pstmt.setString(1, qDTO.getLoginId());
				int rs = pstmt.executeUpdate();
				if (rs <= 0) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean delete(QuestionDTO qDTO) {
		// 문제삭제
		// 모델
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DELETE);
			pstmt.setInt(1, qDTO.getqId());
			int rs=pstmt.executeUpdate();
			if(rs<=0) {
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
