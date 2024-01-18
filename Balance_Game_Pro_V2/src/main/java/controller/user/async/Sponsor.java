package controller.user.async;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Lifecycle.SingleUse;


import controller.common.ActionForward;
import model.support.SupportDAO;
import model.support.SupportDTO;

/**
 * Servlet implementation class Sponsor
 */
@WebServlet("/sponsor")
public class Sponsor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sponsor() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.getAttribute("loginId");

		SupportDTO supportDTO = new SupportDTO();
		SupportDAO supportDAO = new SupportDAO();
		supportDTO.setLoginId(request.getParameter("loginId"));
		supportDTO.setAmount(Integer.parseInt(request.getParameter("amount")));
		supportDTO.setSearchCondition("후원");
		System.out.println("데이터 : " + supportDTO.getLoginId()); // 데이터옴?
		System.out.println("파라미터 후원 금액" + supportDTO.getAmount());
		boolean support = supportDAO.insert(supportDTO);
		if (support) {
			// 후원성공
			System.out.println("후원성공");
		} else {
			// 후원실패
			System.out.println("후원실패");
		}
	}

}
