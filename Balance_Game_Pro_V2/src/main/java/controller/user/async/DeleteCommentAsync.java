package controller.user.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.comment.CommentDAO;
import model.comment.CommentDTO;

@WebServlet("/deleteCommentAsync.do")
public class DeleteCommentAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCommentAsync() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("로그["+request.getParameter("cId")+"]");
		PrintWriter out = response.getWriter();
		
		CommentDTO cDTO = new CommentDTO();
		CommentDAO cDAO = new CommentDAO();
		
		cDTO.setcId(Integer.parseInt(request.getParameter("cId")));
		boolean flag = cDAO.delete(cDTO);
		
		if(!flag) {
			out.print(0);
		}else {
			out.print(1);
		}
		System.out.println("로그 flag[" +flag+"]");
		
	}

}
