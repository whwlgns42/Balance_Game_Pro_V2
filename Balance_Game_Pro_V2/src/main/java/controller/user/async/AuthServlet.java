package controller.user.async;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;



@WebServlet("/authServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AuthServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Random rand = new Random();
		int randNum = rand.nextInt(900000) + 100000;

		String phone = (String) request.getParameter("cellPhone");
		String authNumber = String.valueOf(randNum);

		response.setCharacterEncoding("UTF-8");

		// 생성된 인증번호를 text 형식으로 전송
		response.getWriter().write(authNumber);

//      돈 나갈예정이라 막아놓음
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCS6ZHPOOM5UZPWE", "UG5ONIDCC9V05JCNXNXFSMYYZOMHCYOH", "https://api.coolsms.co.kr"); // Message
		Message message = new Message();
		message.setFrom("01087937953");
		message.setTo(phone);
		message.setText(authNumber);

		try { // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
			messageService.send(message);
		} catch (NurigoMessageNotReceivedException exception) { // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
			System.out.println(exception.getFailedMessageList());
			System.out.println(exception.getMessage());
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

	}

}
