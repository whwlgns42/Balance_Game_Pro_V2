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

public class ResignAction implements Action { // 회원탈퇴 기능

	// 손성용
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();

		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();

		String memberLoginId = request.getParameter("loginId");

		// SearchCondition
		memberDTO.setLoginId(memberLoginId);		
		
		// DLETE
		if (memberDAO.delete(memberDTO)) {
			
			// 트리거 추가 완료
			// UPDATE => NULL
//			questionDAO.update(questionDTO);
//			answerDAO.update(answerDTO);
//			commentDAO.update(commentDTO);
//			saveDAO.update(saveDTO);
//			supportDAO.update(supportDTO);
			
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "success");
			request.setAttribute("msg", "밸런스를 이용해주셔서 감사합니다");
			request.setAttribute("redirect", "main.do");
			session.invalidate();			
		} else {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "fail");
			request.setAttribute("msg", "회원탈퇴가 취소되었습니다");
			request.setAttribute("redirect", "main.do");
		}

		return forward;
	}
}
