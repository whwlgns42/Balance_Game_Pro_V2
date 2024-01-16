package model.answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Util.JDBCUtil;

//
//
public class AnswerDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	private final String SELECTALL_MYLIST = "SELECT Q.TITLE, Q.ANSWER_A, Q.ANSWER_B, Q.WRITER FROM QUESTIONS Q JOIN ANSWERS A ON Q.QID=A.QID JOIN MEMBER M ON A.LOGIN_ID = ?";

	public ArrayList<AnswerDTO> selectAll(AnswerDTO aDTO) { // TODO 이용자가 풀었던 문제에대한 답변 정보 전체 조회
		// 전은주
		// 한글코딩
		// answerDTO가 지금 fk를 다 가지고 있기 때문에 answerDAO에서 작성
		// 질문 테이블의 pk와 답변 테이블의 질문 fk와 비교 - join문
		// 맞다면 질문 테이블의 login_id와 멤버테이블의 fk를 비교 - join문
		// 그거에 맞는 질문 목록을 출력
		// 질문목록  - qid를 가지고 와야해....

		conn = JDBCUtil.connect();
		ArrayList<AnswerDTO> datas = new ArrayList<AnswerDTO>();
		
		if (aDTO.getSearchCondition().equals("myList")) {
			try {
				pstmt = conn.prepareStatement(SELECTALL_MYLIST);
				pstmt.setString(1, aDTO.getLoginId());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					AnswerDTO answerDTO = new AnswerDTO();
					answerDTO.setAnsTitle(rs.getString("TITLE"));
					datas.add(answerDTO);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		return datas;
	}

	public AnswerDTO selectOne(AnswerDTO aDTO) { // TODO 이용자가 문제를 풀었던건지 조회하기
		if (aDTO.getSearchCondition().equals("선택결과")) {
			// 박현구
		}

		return null;
	}

	public boolean insert(AnswerDTO aDTO) { // TODO INSERT : 문제를 풀때 유저의 정보와 문제번호, 문제의 답변을 저장
		if (aDTO.getSearchCondition().equals("답변저장")) {
			// 박현구
		}

		return false;
	}

	public boolean update(AnswerDTO aDTO) {
		return false;
	}

	// 댓글 삭제하기 TODO 추후 구현 예정
	public boolean delete(AnswerDTO aDTO) {
		return false;
	}

}