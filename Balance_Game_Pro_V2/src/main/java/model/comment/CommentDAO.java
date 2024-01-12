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
    	return null;

    }
    	
    // 댓글 하나 출력하기 TODO 추후 구현 예정
    public CommentDTO selectOne(CommentDTO cDTO) {
    	return null;
    }
	
    // 댓글 추가하기
    public boolean insert(CommentDTO cDTO) {
      return false;
    }
    // 댓글 수정하기 TODO 추후 구현 예정
    public boolean update(CommentDTO cDTO) {
    	 return false;
    }
    // 댓글 삭제하기 TODO 추후 구현 예정
    public boolean delete(CommentDTO cDTO) {
    	 return false;
    }

}