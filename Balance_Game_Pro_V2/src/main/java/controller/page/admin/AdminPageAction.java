package controller.page.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;
import model.question.QuestionDAO;
import model.question.QuestionDTO;
import model.suggestion.SuggestionDAO;
import model.suggestion.SuggestionDTO;
import model.support.SupportDAO;
import model.support.SupportDTO;

public class AdminPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();

		SuggestionDTO sugDTO = new SuggestionDTO();
		SuggestionDAO sugDAO = new SuggestionDAO();
		QuestionDTO qDTO = new QuestionDTO();
		QuestionDAO qDAO = new QuestionDAO();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		SupportDTO sDTO = new SupportDTO();
		SupportDAO sDAO = new SupportDAO();
		
		qDTO.setSearchCondition("문제승인갯수");
		qDTO.setqAccess("F");
		QuestionDTO qDTOApproveCnt = qDAO.selectOne(qDTO);
		
		qDTO.setSearchCondition("총문제수");
		qDTO.setqAccess("T");
		QuestionDTO qDTOTotalCnt = qDAO.selectOne(qDTO);

		mDTO.setSearchCondition("회원인원수");
		mDTO = mDAO.selectOne(mDTO);
		
		sDTO.setSearchCondition("총후원금액");
		sDTO = sDAO.selectOne(sDTO);
		
		sugDTO.setSearchCondition("전체조회");
		ArrayList<SuggestionDTO> sugDatas = sugDAO.selectAll(sugDTO);
		System.out.println(sugDatas + "sugDatas <<<<<<<<");

		if (sugDatas.size() <= 0) {
			System.out.println("건의사항 : null");
		}
		System.out.println("건의사항 데이터는 있음");
		System.out.println("sugDatas"+sugDatas);
		forward.setPath("adminMain.jsp");
		forward.setRedirect(false);
		request.setAttribute("sugDatas", sugDatas);
		request.setAttribute("qDTOApproveCnt", qDTOApproveCnt);
		request.setAttribute("qDTOTotalCnt", qDTOTotalCnt);
		request.setAttribute("mDTO", mDTO);
		request.setAttribute("sDTO", sDTO);
	
		return forward;
	}

}
