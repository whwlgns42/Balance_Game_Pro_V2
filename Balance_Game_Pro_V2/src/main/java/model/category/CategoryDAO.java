package model.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.util.JDBCUtil;

public class CategoryDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	//카테고리 전부 가져오는 SQL
	private static final String SELECTALL="SELECT CGID,CATEGORY FROM CATEGORY";

	public ArrayList<CategoryDTO> selectAll(CategoryDTO sDTO) {
		// SELECTALL : 모든 카테고리 가져오기 
		ArrayList<CategoryDTO> datas=new ArrayList<CategoryDTO>();

		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL);

			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				CategoryDTO data=new CategoryDTO();
				data.setCgId(rs.getInt("CGID"));
				data.setCategory(rs.getString("CATEGORY"));
				datas.add(data);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}

		return datas;

	}

	private CategoryDTO selectOne(CategoryDTO sDTO) {

		return null;
	}

	private boolean insert(CategoryDTO sDTO) {

		return false;
	}

	private boolean update(CategoryDTO sDTO) {
		return false;
	}

	private boolean delete(CategoryDTO sDTO) {

		return false;
	}

}
