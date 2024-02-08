 package controller.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;

public class AlertPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		if(request.getParameter("status") != null) {
			if(request.getParameter("status").equals("success")  ) {
				request.setAttribute("status", request.getParameter("status"));
				request.setAttribute("msg", request.getParameter("msg"));
				request.setAttribute("redirect", request.getParameter("redirect"));
			}
			else {
				request.setAttribute("status", request.getParameter("status"));
				request.setAttribute("msg", request.getParameter("msg"));
				request.setAttribute("redirect", request.getParameter("redirect"));
			}
		}
		
		forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;		
		
	}

}
