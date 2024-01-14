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


public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
	
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		HttpSession session = request.getSession();
		memberDTO.setSearchCondition("로그인");
		memberDTO.setLoginId(request.getParameter("loginId"));
		memberDTO.setmPw(request.getParameter("mPw"));
		MemberDTO loginData =  memberDAO.selectOne(memberDTO);
		if(loginData != null) {
			// 로그인 성공
			forward.setPath("alert.do");
			request.setAttribute("status", "success");
			session.setAttribute("loginId", loginData.getLoginId());
			request.setAttribute("msg", loginData.getLoginId() + "님 로그인 하셨습니다.");
			request.setAttribute("redirect", "main.do");
			forward.setRedirect(false);
		}else {
			// 로그인 실패
			forward.setPath("alert.do");
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "로그인 정보가 틀렸습니다 다시 확인해주세요");
			request.setAttribute("redirect", "loginPage.do");
			forward.setRedirect(false);
		}
		return forward;
	}

}
