package controller.user.async;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

@WebServlet("/idCheckAsync")
public class IdCheckAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IdCheckAsync() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		memberDTO.setLoginId(request.getParameter("loginId"));
		memberDTO.setSearchCondition("중복확인");
		MemberDTO idDoubleCheckRes = memberDAO.selectOne(memberDTO);
		response.setContentType("application/json;charset=UTF-8");
		if (idDoubleCheckRes == null) {
			System.out.println("사용 가능한 아이디");
			response.getWriter().print(idDoubleCheckRes != null ? 1 : 0);
		} else {
			System.out.println(idDoubleCheckRes.getLoginId() + " 는 중복된 아이디 ");
			response.getWriter().print(idDoubleCheckRes != null ? 1 : 0);
		}
	}

}
