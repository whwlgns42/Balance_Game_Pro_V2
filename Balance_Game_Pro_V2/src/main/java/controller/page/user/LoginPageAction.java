package controller.page.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;


public class LoginPageAction implements Action {

		
		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			ActionForward forward = new ActionForward();
			forward.setPath("login.jsp");
			forward.setRedirect(true);
			return forward;
		}


}
