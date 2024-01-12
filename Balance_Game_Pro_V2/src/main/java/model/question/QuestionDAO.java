package model.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Util.JDBCUtil;

public class QuestionDAO {

	 // 문제의 테이블의 정보를 모두 조회
	public ArrayList<QuestionDTO> selectAll(QuestionDTO qDTO) {
		
		return null;

	}

	// 가져올 문제테이블의 정보를 무작위로 정렬해서 가져와서 맨위에 있는 한개의 행의 데이터만 조회
	public QuestionDTO selectOne(QuestionDTO qDTO) { 
		
		return null;
	} 

	// 크롤링한 문제 추가하기
	public boolean insert(QuestionDTO qDTO) {
		
		return false;
	}
    public boolean update(QuestionDTO qDTO) {
   	 return false;
   }
   // 댓글 삭제하기 TODO 추후 구현 예정
   public boolean delete(QuestionDTO qDTO) {
   	 return false;
   }

}
