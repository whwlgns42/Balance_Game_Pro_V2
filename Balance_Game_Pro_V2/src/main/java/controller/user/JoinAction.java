package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();

		request.setCharacterEncoding("UTF-8");
		memberDTO.setSearchCondition("회원가입");
		memberDTO.setLoginId(request.getParameter("loginId")); // 로그인 ID
		memberDTO.setmPw(request.getParameter("mPw")); // 비밀번호
		memberDTO.setName(request.getParameter("name")); // 이름
		memberDTO.setEmail(request.getParameter("email")); // 이메일
		memberDTO.setAddress(request.getParameter("address")); // 주소
		memberDTO.setGender(request.getParameter("gender"));  // 성별
		memberDTO.setAge(Integer.parseInt(request.getParameter("age"))); // 나이

		boolean memberJoin = memberDAO.insert(memberDTO);
		if (memberJoin) {
			request.setAttribute("msg", memberDTO.getName() + "님 환영합니다.");
			forward.setPath("alert.do");
			forward.setRedirect(false);
		} else {
			System.out.println(memberJoin);
			forward.setPath("alert.do");
			request.setAttribute("msg", "오류가 발생하였습니다.");
			forward.setRedirect(false);
		}
		return forward;
	}

}
