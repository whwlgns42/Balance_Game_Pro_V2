package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;



public class MyPageUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		memberDTO.setLoginId((String)session.getAttribute("loginId"));
		memberDTO.setName(request.getParameter("name"));
		memberDTO.setEmail(request.getParameter("email"));
		memberDTO.setSearchCondition("회원변경");;
		boolean myInfoUpdate = memberDAO.update(memberDTO);
		if(myInfoUpdate) {
			// 내정보 변경 성공
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("result", "success");
			request.setAttribute("msg", "정보가 수정되었습니다.");
			request.setAttribute("redirect", "main.do");
			
		}else  {
			// 내정보 변경 실패
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("result", "fail");
			request.setAttribute("msg", "정보 변경에 실패하였습니다.");
			request.setAttribute("redirect", "myPage.do");
			
		}
		return forward;
	}

}
