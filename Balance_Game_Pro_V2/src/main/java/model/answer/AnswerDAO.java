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

	public ArrayList<AnswerDTO> selectAll(AnswerDTO aDTO) { // TODO 이용자가 풀었던 문제에대한 답변 정보 전체 조회

		return null;
	}

	public AnswerDTO selectOne(AnswerDTO aDTO) { // TODO 이용자가 문제를 풀었던건지 조회하기

		return null;
	}
	
	public boolean insert(AnswerDTO aDTO) { 	// TODO INSERT : 문제를 풀때 유저의 정보와 문제번호, 문제의 답변을 저장
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