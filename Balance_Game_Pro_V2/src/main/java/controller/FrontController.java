package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import controller.handler.HandlerMapper;



@WebServlet("*.do")
public class FrontController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   private HandlerMapper handler;
   
   public FrontController() {
      super();
      handler = new HandlerMapper();
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

      String uri = request.getRequestURI();
      String cp = request.getContextPath();
      String commend = uri.substring(cp.length());
      Action action = handler.getAction(commend);
      ActionForward forward = action.execute(request, response);
      if (forward == null) { // 에러 상황
    	  System.out.println("에러페이지 요청");
      }

      if (forward.isRedirect()) {
         response.sendRedirect(forward.getPath());
      }
      else {
         RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
         dispatcher.forward(request, response);
      }

   }

}