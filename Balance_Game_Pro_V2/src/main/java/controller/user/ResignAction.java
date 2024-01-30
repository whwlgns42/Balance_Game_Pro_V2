package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.answer.AnswerDAO;
import model.answer.AnswerDTO;
import model.comment.CommentDAO;
import model.comment.CommentDTO;
import model.member.MemberDAO;
import model.member.MemberDTO;
import model.question.QuestionDAO;
import model.question.QuestionDTO;
import model.save.SaveDAO;
import model.save.SaveDTO;
import model.support.SupportDAO;
import model.support.SupportDTO;
import oracle.net.aso.u;

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
		QuestionDTO questionDTO = new QuestionDTO();
		QuestionDAO questionDAO = new QuestionDAO();
		AnswerDTO answerDTO = new AnswerDTO();
		AnswerDAO answerDAO = new AnswerDAO();
		CommentDTO commentDTO = new CommentDTO();
		CommentDAO commentDAO = new CommentDAO();
		SaveDTO saveDTO = new SaveDTO();
		SaveDAO saveDAO = new SaveDAO();
		SupportDTO supportDTO = new SupportDTO();
		SupportDAO supportDAO = new SupportDAO();

		String memberLoginId = request.getParameter("loginId");

		// SearchCondition
		memberDTO.setLoginId(memberLoginId);
		questionDTO.setWriter(memberLoginId);
		questionDTO.setSearchCondition("question_null");
		answerDTO.setLoginId(memberLoginId);
		answerDTO.setSearchCondition("answer_null");
		commentDTO.setLoginId(memberLoginId);
		commentDTO.setSearchCondition("comment_null");
		saveDTO.setLoginId(memberLoginId);
		saveDTO.setSearchCondition("save_null");
		supportDTO.setLoginId(memberLoginId);
		supportDTO.setSearchCondition("support_null");

		System.out.println("mDTO : " + memberDTO);
		
		
		// DLETE
		if (memberDAO.delete(memberDTO)) {
			
			// UPDATE => NULL
			questionDAO.update(questionDTO);
			answerDAO.update(answerDTO);
			commentDAO.update(commentDTO);
			saveDAO.update(saveDTO);
			supportDAO.update(supportDTO);
			
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
