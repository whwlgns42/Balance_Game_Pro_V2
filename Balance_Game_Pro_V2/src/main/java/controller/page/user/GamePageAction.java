package controller.page.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;



public class GamePageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		QuestionDTO qDto=new QuestionDTO();
		QuestionDAO qDao =new QuestionDAO();
		qDto.setSearchCondition("질문랜덤생성");
		
		HttpSession session=request.getSession();
		String loginId= (String)session.getAttribute("loginId");
		System.out.println(loginId);
		
		qDto.setWriter(loginId);
		qDto=qDao.selectOne(qDto);
		System.out.println("GamePageAction : "+qDto.isSave());
		
		request.setAttribute("data", qDto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("game.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
