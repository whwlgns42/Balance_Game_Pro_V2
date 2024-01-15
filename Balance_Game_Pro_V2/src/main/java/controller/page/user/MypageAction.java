package controller.page.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class MypageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		forward.setPath("myPage.jsp");
		forward.setRedirect(false);
		
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		memberDTO.setLoginId((String)session.getAttribute("loginId"));
		memberDTO.setSearchCondition("내정보");
		MemberDTO myPageData =  memberDAO.selectOne(memberDTO);
		System.out.println(myPageData.getLoginId());
		request.setAttribute("myPageData", myPageData);
		

//		SaveDTO saveDTO = new SaveDTO();
//		SaveDAO saveDAO = new SaveDAO();
//		saveDTO.setLoginId(myPageData.getLoginId());
//		saveDTO.setSearchCondition("찜문제");
//		ArrayList<SaveDTO> saveListDatas =  saveDAO.selectAll(saveDTO);
//		request.setAttribute("mySaveListDatas", saveListDatas);
		
		return forward;
	}

}
