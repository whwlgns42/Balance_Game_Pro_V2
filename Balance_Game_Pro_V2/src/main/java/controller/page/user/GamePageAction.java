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
		System.out.println(loginId + "게임페이지 들어올때 로그인 아이디");
		
		qDto.setWriter(loginId);
//		
//		qDto=qDao.selectOne(qDto);  나중에 주석풀기
		
//		테스트 코드
		qDto.setqId(1);
		qDto.setTitle("게임테스트");
		qDto.setAnswer_A("답변11");
		qDto.setAnswer_B("답변222");
		qDto.setExplanation("게임설명");
		qDto.setSave(1);
//		테스트 코드
		
		System.out.println("GamePageAction : "+qDto.getSave());
		
		request.setAttribute("data", qDto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("game.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
