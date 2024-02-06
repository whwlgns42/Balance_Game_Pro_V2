package controller.page.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.suggestion.SuggestionDAO;
import model.suggestion.SuggestionDTO;

public class UserSuggestionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		SuggestionDTO sugDTO = new SuggestionDTO();
		SuggestionDAO sugDAO = new SuggestionDAO();
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");

		sugDTO.setLoginId(loginId); // 건의하는 유저 아이디
		sugDTO.setSuggestion(request.getParameter("suggestion")); // 건의사항 내용
		sugDTO.setTitle(request.getParameter("title")); // 건의사항 제목

		boolean flag = sugDAO.insert(sugDTO);
		System.out.println("건의사항 결과" + flag);
		if (!flag) {
			forward.setPath("alert.do");
			forward.setRedirect(false);
			request.setAttribute("status", "failClose");
			request.setAttribute("msg", "전송에 실패했습니다");
			//request.setAttribute("redirect", "main.do");
			return forward;
		}
		forward.setPath("alert.do");
		request.setAttribute("status", "close");
		request.setAttribute("msg", "건의사항이 발송되었습니다.");
		forward.setRedirect(false);
		return forward;
	}
}
