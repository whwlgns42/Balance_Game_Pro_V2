package controller.page.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class AdminMemberDetailPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		mDTO.setLoginId(request.getParameter("loginId"));
		mDTO.setSearchCondition("유저조회");
		
		MemberDTO member = mDAO.selectOne(mDTO);
		System.out.println(member + "<<<<<<<111");
		if (member == null) {
			forward.setPath("alert.do");
			request.setAttribute("status", "fail");
			request.setAttribute("fail", "회원이 존재하지 않습니다.");
			request.setAttribute("redirect", "adminPage.do");
			return forward;
	    } 
		
		forward.setPath("adminMemberDetail.jsp");
		forward.setRedirect(false);
		request.setAttribute("member", member);
	    return forward;
	}
	
	
	
	
	
	

}
