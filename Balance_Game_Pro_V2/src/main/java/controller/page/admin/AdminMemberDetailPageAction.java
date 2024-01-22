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
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		memberDTO.setLoginId(request.getParameter("loginId"));
		memberDTO.setSearchCondition("유저조회");
		/* request.getParameter("loginId"); */
		System.out.println(request.getParameter("loginId"));
		
		MemberDTO member = memberDAO.selectOne(memberDTO);
		System.out.println(member + "<<<<<<<111");
		if (member != null) {
	        System.out.println("데이터 있음");
	        request.setAttribute("member", member);
	        forward.setPath("adminMemberDetail.jsp");
	        forward.setRedirect(false);
	    } else {
	        System.out.println("데이터 없음");
	        forward.setPath("alert.do");
	        request.setAttribute("status", "fail");
	        request.setAttribute("fail", "회원이 존재하지 않습니다.");
	        request.setAttribute("redirect", "adminPage.do");
	    }

	    return forward;
	}
	
	
	
	
	
	

}
