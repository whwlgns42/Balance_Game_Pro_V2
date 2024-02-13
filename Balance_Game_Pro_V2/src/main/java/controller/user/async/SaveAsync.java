package controller.user.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.save.SaveDAO;
import model.save.SaveDTO;

@WebServlet("/saveAsync")
public class SaveAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveAsync() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		SaveDAO sDAO = new SaveDAO();
		SaveDTO sDTO = new SaveDTO();
		sDTO.setqId(Integer.parseInt(request.getParameter("qId")));
		sDTO.setLoginId(request.getParameter("loginId"));
		
		boolean flag=false; 
		String src="";
		if (sDAO.selectOne(sDTO) == null) {
			flag = sDAO.insert(sDTO);
			if (flag) {
				src="찜o.png";
			}
		}else {
			sDTO.setSearchCondition("qm찜삭제");
			flag= sDAO.delete(sDTO);
			if (flag) {
				src="찜x.png";
			}
		}
		
		if(flag) {
			System.out.println(src);
			out.print(src);
		}
		else{
			out.print("실패");
		}

	}

}
