package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;

public class AdminTitleCreateAcion implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//문제생성
		ActionForward forward = new ActionForward();
		forward.setPath("adminTitleManagementPage.do");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
