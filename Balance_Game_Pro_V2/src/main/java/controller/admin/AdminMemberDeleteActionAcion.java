package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class AdminMemberDeleteActionAcion implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 
		//한글코딩
		//뷰에서 보내준 회원 아이디를 셋팅해서 
		//모델의 멤버DAO delete로 보냄
		
		ActionForward forward = new ActionForward();
		forward.setPath("adminMemberManagementPage.do");
		forward.setRedirect(true);
		
		MemberDTO mDTO =new MemberDTO();
		MemberDAO mDAO =new MemberDAO();
		mDTO.setLoginId((String)request.getAttribute("loginId"));
		mDAO.delete(mDTO);
		
		return forward;
	}

}
