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
		if(qDTO.getSearchCondition().equals("문제전체조회")) {
			//모델
			//승인된 문제
		}
		else if(qDTO.getSearchCondition().equals("승인문제조회")) {
			//모델
		}
		
		return null;
	}

	// 가져올 문제테이블의 정보를 무작위로 정렬해서 가져와서 맨위에 있는 한개의 행의 데이터만 조회
	public QuestionDTO selectOne(QuestionDTO qDTO) { 
		
		if(qDTO.getSearchCondition().equals("문제상세조회")) {
			//박현구
		}
		
		return null;
	} 


	public boolean insert(QuestionDTO qDTO) {
		if(qDTO.getSearchCondition().equals("문제생성")) {
			//조지훈
		}
		else if(qDTO.getSearchCondition().equals("관리자문제생성")) {
			//모델
		}
		
		return false;
	}
    public boolean update(QuestionDTO qDTO) {
		if(qDTO.getSearchCondition().equals("문제수정")) {
			//모델
		}
		else if(qDTO.getSearchCondition().equals("승인")) {
			//전은주
		}
    	
   	 return false;
   }
   // 댓글 삭제하기 TODO 추후 구현 예정
   public boolean delete(QuestionDTO qDTO) {
	   //문제삭제
	   //모델
	   
   	 return false;
   }

}
