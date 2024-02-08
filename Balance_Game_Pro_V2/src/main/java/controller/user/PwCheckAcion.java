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

public class PwCheckAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		HttpSession session = request.getSession();
		memberDTO.setSearchCondition("2차인증");
		memberDTO.setLoginId((String) session.getAttribute("loginId"));
		System.out.println(session.getAttribute("loginId"));
		memberDTO.setmPw(request.getParameter("mPw"));
		MemberDTO loginData = memberDAO.selectOne(memberDTO);
		System.out.println(loginData);

		if (loginData == null) {
			// 인증실패
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "비밀번호가 맞지 않습니다 다시 확인해주세요");
			request.setAttribute("redirect", "pwCheckPage.do");
		} else if (loginData != null) {
			// 인증 성공
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "success");
			request.setAttribute("msg", loginData.getLoginId() + "님 인증 성공하였습니다.");
			request.setAttribute("redirect", "myPage.do");
			
		}
		return forward;
	}

}
