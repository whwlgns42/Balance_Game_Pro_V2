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

	// 회원가입 SQL
	private static final String INSERT = "INSERT INTO QUESTIONS (QID, WRITER, TITLE, ANSWER_A, ANSWER_B, EXPLANATION) VALUES((SELECT NVL(MAX(QID),0) + 1 FROM QUESTIONS),?,?,?,?,?)";

	// 문제의 테이블의 정보를 모두 조회
	public ArrayList<QuestionDTO> selectAll(QuestionDTO qDTO) {
		if (qDTO.getSearchCondition().equals("문제전체조회")) {
			// 모델
			// 승인된 문제
		} else if (qDTO.getSearchCondition().equals("승인문제조회")) {
			// 모델
		}

		return null;
	}

	// 가져올 문제테이블의 정보를 무작위로 정렬해서 가져와서 맨위에 있는 한개의 행의 데이터만 조회
	public QuestionDTO selectOne(QuestionDTO qDTO) {

		if (qDTO.getSearchCondition().equals("문제상세조회")) {
			// 박현구
		} else if (qDTO.getSearchCondition().equals("질문랜덤생성")) {
			// 박현구
		}
		return null;
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
