package controller.handler;

import java.util.HashMap;
import java.util.Map;

import controller.admin.AdminMemberDeleteAction;
import controller.admin.AdminTitleAccessAction;
import controller.admin.AdminTitleCreateAction;
import controller.admin.AdminTitleDeleteAction;
import controller.admin.AdminTitleRefuseAction;
import controller.admin.AdminTitleUpdateAction;
import controller.common.Action;
import controller.page.AlertPageAction;
import controller.page.admin.AdminCreateTitlePageAction;
import controller.page.admin.AdminMemberDetailPageAction;
import controller.page.admin.AdminMemberManagementPageAction;
import controller.page.admin.AdminPageAction;
import controller.page.admin.AdminTitleAccessPageAction;
import controller.page.admin.AdminTitleDetailPageAction;
import controller.page.admin.AdminTitleDetaileAccessPageAction;
import controller.page.admin.AdminTitleManagementPageAction;
import controller.page.user.GamePageAction;
import controller.page.user.JoinPageAction;
import controller.page.user.LoginPageAction;
import controller.page.user.MainPageAction;
import controller.page.user.MakeTitlePageAction;
import controller.page.user.MypageAction;
import controller.page.user.PwCheckPageAcion;
import controller.page.user.ResultPageAction;
import controller.page.user.SponsorPageAction;
import controller.page.user.TitleDetailPageAction;
import controller.page.user.TitleListPageAction;
import controller.page.user.UserSuggestionAction;
import controller.page.user.UserSuggestionPageAction;
import controller.page.user.WishListDetailPageAction;
import controller.page.user.WishListPageAction;
import controller.user.GameAction;
import controller.user.JoinAction;
import controller.user.LoginAction;
import controller.user.LogoutAction;
import controller.user.MakeTitleAction;
import controller.user.MyPageUpdateAction;
import controller.user.PwCheckAcion;
import controller.user.ResignAction;
import controller.user.ResultAction;
import controller.user.SponsorAction;

public class HandlerMapper {
	// 멤버변수
	private Map<String, Action> mappings;

	public HandlerMapper() {

		this.mappings = new HashMap<String, Action>();

		// user
		mappings.put("/main.do", new MainPageAction());
		mappings.put("/joinPage.do", new JoinPageAction());
		mappings.put("/join.do", new JoinAction());
		mappings.put("/loginPage.do", new LoginPageAction());
		mappings.put("/login.do", new LoginAction());
		mappings.put("/logout.do", new LogoutAction());
		mappings.put("/resignAction.do", new ResignAction());
		mappings.put("/wishListPage.do", new WishListPageAction());
		mappings.put("/makeTitle.do", new MakeTitleAction());
		mappings.put("/makeTitlePage.do", new MakeTitlePageAction());
		mappings.put("/result.do", new ResultAction());
		mappings.put("/resultPage.do", new ResultPageAction());
		mappings.put("/titleDetailPage.do", new TitleDetailPageAction());
		mappings.put("/titleListPage.do", new TitleListPageAction());
		mappings.put("/myPage.do", new MypageAction());
		mappings.put("/mypageUpdate.do", new MyPageUpdateAction());
		mappings.put("/pwCheck.do", new PwCheckAcion());	
		mappings.put("/pwCheckPage.do", new PwCheckPageAcion());
		mappings.put("/game.do", new GameAction());
		mappings.put("/gamePage.do", new GamePageAction());
		mappings.put("/sponsorPage.do", new SponsorPageAction());
		mappings.put("/sponsor.do", new SponsorAction());
		mappings.put("/wishListDetailPage.do", new WishListDetailPageAction());
		mappings.put("/userSuggestionAction.do", new UserSuggestionAction());
		mappings.put("/userSuggestionPageAction.do", new UserSuggestionPageAction());
		
		// 관리자
		mappings.put("/adminPage.do", new AdminPageAction());
		mappings.put("/adminTitleManagementPage.do", new AdminTitleManagementPageAction());
		mappings.put("/adminTitleDetailPage.do", new AdminTitleDetailPageAction());
		mappings.put("/adminMemberManagementPage.do", new AdminMemberManagementPageAction());
		mappings.put("/adminMemberDetailPage.do", new AdminMemberDetailPageAction());
		mappings.put("/adminTitleDetaileAccessPage.do", new AdminTitleDetaileAccessPageAction());
		mappings.put("/adminTitleAccessPage.do", new AdminTitleAccessPageAction());
		mappings.put("/adminTitleAccess.do", new AdminTitleAccessAction());
		mappings.put("/adminTitleRefuse.do", new AdminTitleRefuseAction());
		mappings.put("/adminTitleCreate.do", new AdminTitleCreateAction());
		mappings.put("/adminTitleDelete.do", new AdminTitleDeleteAction());
		mappings.put("/adminTitleUpdate.do", new AdminTitleUpdateAction());
		mappings.put("/adminMemberDelete.do", new AdminMemberDeleteAction());
		mappings.put("/alert.do", new AlertPageAction());
		mappings.put("/adminCreateTitlePage.do", new AdminCreateTitlePageAction());
	}

	public Action getAction(String commend) {
		return mappings.get(commend);
	}

}