package controller.handler;

import java.util.HashMap;
import java.util.Map;

import controller.common.Action;
import controller.page.user.GamePageAction;
import controller.page.user.JoinPageAction;
import controller.page.user.LoginPageAction;
import controller.page.user.MainPageAction;
import controller.page.user.MakeTitlePageAction;
import controller.page.user.MypageAction;
import controller.page.user.PwCheckPageAcion;
import controller.page.user.ResultPageAction;
import controller.page.user.SponsorPageAction;
import controller.page.user.TitleListPageAction;
import controller.page.user.WishListPageAction;
import controller.user.GameAction;
import controller.user.JoinAction;
import controller.user.LoginAction;
import controller.user.LogoutAction;
import controller.user.MakeTitleAction;
import controller.user.MyPageUpdateAction;
import controller.user.PwCheckAcion;
import controller.user.ResultAction;
import controller.user.SmsCheckAction;
import controller.user.SponsorAction;
import controller.user.WriteCommentAction;

public class HandlerMapper {

	private Map<String, Action> mappings;

	public HandlerMapper() {
		System.out.println("111");
		mappings = new HashMap<String, Action>();
		mappings.put("/main.do", new MainPageAction());
		mappings.put("/joinPage.do", new JoinPageAction());
		mappings.put("/join.do", new JoinAction());
		mappings.put("/loginPage.do", new LoginPageAction());
		mappings.put("/login.do", new LoginAction());
		mappings.put("/logout.do", new LogoutAction());
//		mappings.put("/wish.do", new WishAction()); // 찜하기 기능 객체 넣기
		mappings.put("/wishListPage.do", new WishListPageAction()); // 찜하기 페이지 이동 객체 넣기
		mappings.put("/makeTitle.do", new MakeTitleAction());
		mappings.put("/makeTitlePage.do", new MakeTitlePageAction());
		mappings.put("/result.do", new ResultAction());
		mappings.put("/resultPage.do", new ResultPageAction());
		mappings.put("/writeComment.do", new WriteCommentAction());
		mappings.put("/titleListPage.do", new TitleListPageAction());
		mappings.put("/myPage.do", new MypageAction());
		mappings.put("/mypageUpdate.do", new MyPageUpdateAction());
		mappings.put("/pwCheck.do", new PwCheckAcion());
		mappings.put("/pwCheckPage.do", new PwCheckPageAcion());
		mappings.put("/game.do", new GameAction());
		mappings.put("/gamePage.do", new GamePageAction());
		mappings.put("/sponsorPage.do", new SponsorPageAction());
		mappings.put("/sponsor.do", new SponsorAction());
		mappings.put("/smsCheck.do", new SmsCheckAction());
		// 밑으로 관리자 부분 작성해주시면 됩니다.~ 작성자 조지훈 !!!
	}

	public Action getAction(String commend) {
		return mappings.get(commend);
	}

}
