package controller.user.async.idCheck;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberDTO;

public class IdCheckAsync {

	public static void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();

		memberDTO.setLoginId(request.getParameter("loginId"));
		memberDTO.setSearchCondition("중복확인");
		MemberDTO idDoubleCheckRes = memberDAO.selectOne(memberDTO);
		
		 response.setContentType("application/json;charset=UTF-8");
		if (idDoubleCheckRes == null) {
			System.out.println("사용 가능한 아이디");
//			forward.setPath("main.do");
//			forward.setRedirect(true);
			response.getWriter().print(idDoubleCheckRes != null ? 1 : 0);
		} else {
			System.out.println("중복된 아이디임");
//			forward.setPath("joinPage.do");
//			forward.setRedirect(true);
			response.getWriter().print(idDoubleCheckRes != null ? 1 : 0);
		}
	}
}
