package model.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Util.JDBCUtil;


public class SupportDAO {
	public ArrayList<SupportDTO> selectAll(SupportDTO sDTO) {
		
		if(sDTO.getSearchCondition().equals("후원목록")) {
			//모델
		}
		
		return null;

	}


	public SupportDTO selectOne(SupportDTO sDTO) { 
		
		return null;
	} 

	public boolean insert(SupportDTO sDTO) {
		if(sDTO.getSearchCondition().equals("후원")) {
			//모델
		}
		return false;
	}
   public boolean update(SupportDTO sDTO) {
  	 return false;
  }

  public boolean delete(SupportDTO sDTO) {
  	 return false;
  }
}
