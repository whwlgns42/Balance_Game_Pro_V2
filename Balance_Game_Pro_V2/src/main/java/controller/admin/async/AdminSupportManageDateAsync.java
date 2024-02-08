package controller.admin.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.support.SupportDAO;
import model.support.SupportDTO;

@WebServlet("/AdminSupportManageDateAsync.do")
public class AdminSupportManageDateAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminSupportManageDateAsync() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		SupportDTO sDTO = new SupportDTO();
		SupportDAO sDAO = new SupportDAO();
	
		sDTO.setSearchCondition("최신순");
		ArrayList<SupportDTO> sDateDatas = sDAO.selectAll(sDTO);
		System.out.println("[로그: 최신순 ]"+sDateDatas);
		
		if(sDateDatas == null) {
			out.print("실패");
			return;
		}
		out.print(gson.toJson(sDateDatas));
		System.out.println("[로그: 최신순]"+gson.toJson(sDateDatas));
	}
}
