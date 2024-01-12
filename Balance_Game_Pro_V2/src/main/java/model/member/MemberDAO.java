package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Util.JDBCUtil;

public class MemberDAO {
 
	public ArrayList<MemberDTO> selectAll(MemberDTO mDTO) { // 전체 검색
		return null;
	}

	public MemberDTO selectOne(MemberDTO mDTO) { // 단일 검색
		
	
		return null;

	}

	public boolean insert(MemberDTO mDTO) { // 회원가입 (추후 구현 예정)
	
		return false;
	}

	public boolean update(MemberDTO mDTO) { // 개인정보 변경 (추후 구현 예정)
		
		return false;
	}

	public boolean delete(MemberDTO mDTO) { // 회원탈퇴
		
		return false;
	}

}
