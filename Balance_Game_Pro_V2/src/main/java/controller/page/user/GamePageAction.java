package controller.page.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class GamePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Integer> list;
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		System.out.println("컨트롤 리스트"+session.getAttribute("qList"));
		if (session.getAttribute("qList") == null) {
			list = new ArrayList<Integer>();
		} else {
			list = (ArrayList<Integer>) session.getAttribute("qList");
		}

		QuestionDTO qDto = new QuestionDTO();
		QuestionDAO qDao = new QuestionDAO();
		String loginId = (String) session.getAttribute("loginId");
		
		qDto.setLoginId(loginId!=null?loginId:"");
		qDto.setSearchCondition("문제전체조회");
		System.out.println("그래"+qDao.selectAll(qDto));
		if(qDao.selectAll(qDto).size()>list.size()) {
		while (true) {
			int i=0;
			qDto.setSearchCondition("질문랜덤생성");
			
			// System.out.println(loginId);
			qDto.setLoginId(loginId);
			qDto = qDao.selectOne(qDto);
			for (i=0; i < list.size(); i++) {
				if (list.get(i) == qDto.getqId()) {
					
					System.out.println("중복 아이디 : "+qDto.getqId());
					break;
				}
			}
			// System.out.println("GamePageAction : "+qDto.getSave());
			if (list.size() <= i) {
				break;
			}
		}
		list.add(qDto.getqId());
		session.setAttribute("qList", list);
		request.setAttribute("data", qDto);
		forward.setPath("game.jsp");
		forward.setRedirect(false);
		}
		else {
			forward.setPath("main.do");
			forward.setRedirect(true);
			session.removeAttribute("qList");
		}
		

		

		return forward;
	}

}
