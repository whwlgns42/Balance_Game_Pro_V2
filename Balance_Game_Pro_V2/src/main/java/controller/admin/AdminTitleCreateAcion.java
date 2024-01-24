package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.question.QuestionDAO;
import model.question.QuestionDTO;

public class AdminTitleCreateAcion implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      //문제생성
      ActionForward forward = new ActionForward();
      //관리자페이지에서 문제를 작성
      //뷰에서 문제를 작성하면 parameter값으로 받아옴 
      //해당 DTO에 set함 
      //작성한 것을 모델의 insert함
      
      QuestionDTO qDTO = new QuestionDTO();
      QuestionDAO qDAO = new QuestionDAO();
      HttpSession session = request.getSession();
      
      qDTO.setSearchCondition("관리자문제생성");
      qDTO.setTitle(request.getParameter("title"));
      qDTO.setAnswer_A(request.getParameter("answer_A"));
      qDTO.setAnswer_B(request.getParameter("answer_B"));
      qDTO.setWriter((String)session.getAttribute("member"));
      qDTO.setExplanation(request.getParameter("explanation"));
      qDTO.setCategory(Integer.parseInt(request.getParameter("category")));
      boolean flag = qDAO.insert(qDTO);
      if(!flag) {
         forward.setPath("alert.do");
         forward.setRedirect(false);
         request.setAttribute("status", "fail");
         request.setAttribute("msg", "문제출제에 실패했습니다");
         request.setAttribute("redirect", "adminTitleManagementPage.do");
         return forward;
      }
      forward.setPath("alert.do");
      forward.setRedirect(false);
      request.setAttribute("status", "success");
      request.setAttribute("msg", "문제출제에 성공했습니다");
      request.setAttribute("redirect", "adminTitleManagementPage.do");
      return forward;
   }

}