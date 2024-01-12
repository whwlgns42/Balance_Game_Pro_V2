package model.save;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Util.JDBCUtil;

public class SaveDAO {

	public ArrayList<SaveDTO> selectAll(SaveDTO sDTO) {

		if(sDTO.getSearchCondition().equals("찜문제")) {
			//박현구
		}
		
		return null;

	}

	public SaveDTO selectOne(SaveDTO sDTO) {

		return null;
	}

	public boolean insert(SaveDTO sDTO) {
		// 찜하기
		return false;
	}

	public boolean update(SaveDTO sDTO) {
		return false;
	}

	public boolean delete(SaveDTO sDTO) {
		if (sDTO.getSearchCondition().equals("qm찜삭제")) {
			// 박현구
		} else if (sDTO.getSearchCondition().equals("s찜삭제")) {
			// 박현구
		}

		return false;
	}

}
