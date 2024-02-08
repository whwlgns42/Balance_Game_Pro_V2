package controller.user.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.answer.AnswerDAO;
import model.answer.AnswerDTO;
import model.question.QuestionDAO;
import model.question.QuestionDTO;


@WebServlet("/answerAsync.do")
public class AnswerAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AnswerAsync() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("들어옴");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		request.getParameter("qId");
		request.getParameter("loginId");
		request.getParameter("answer");
		AnswerDAO aDAO=new AnswerDAO();
		AnswerDTO aDTO =new AnswerDTO();
		aDTO.setqId(Integer.parseInt(request.getParameter("qId")));
		aDTO.setLoginId(request.getParameter("loginId"));
		aDTO.setAnswer(request.getParameter("answer"));
		aDTO.setSearchCondition("답변저장");
		if(aDAO.insert(aDTO)) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}

		QuestionDAO qDAO=new QuestionDAO();
		QuestionDTO qDTO=new QuestionDTO();
		qDTO.setSearchCondition("문제상세조회");
		qDTO.setqId(Integer.parseInt(request.getParameter("qId")));
		qDTO.setLoginId(request.getParameter("loginId"));

		Gson gson=new Gson();
		out.print(gson.toJson(qDAO.selectOne(qDTO)));



	}

}
