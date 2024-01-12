package model.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Util.JDBCUtil;

public class CommentDAO {

    // 댓글 전체 출력하기 
    public ArrayList<CommentDTO> selectAll(CommentDTO cDTO) {
    	if(cDTO.getSearchCondition().equals("댓글조회")) {
			//모델
		}
    	
    	
    	return null;

    }
    	
    // 댓글 하나 출력하기 TODO 추후 구현 예정
    public CommentDTO selectOne(CommentDTO cDTO) {
    	return null;
     }
	
    // 댓글 추가하기
    public boolean insert(CommentDTO cDTO) {
    	if(cDTO.getSearchCondition().equals("댓글생성")) {
			//모델
		}
    	
      return false;
    }
    // 댓글 수정하기 TODO 추후 구현 예정
    public boolean update(CommentDTO cDTO) {
    	if(cDTO.getSearchCondition().equals("댓글수정")) {
			//모델
		}
    	
    	 return false;
    }
    // 댓글 삭제하기 TODO 추후 구현 예정
    public boolean delete(CommentDTO cDTO) {
    	//댓글 삭제
    	 return false;
    }

}