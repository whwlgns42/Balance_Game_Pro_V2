package controller.handler;

import java.util.HashMap;
import java.util.Map;

import controller.admin.AdminMemberDeleteAction;
import controller.admin.AdminTitleAccessAcion;
import controller.admin.AdminTitleCreateAcion;
import controller.admin.AdminTitleDeleteAcion;
import controller.admin.AdminTitleRefuseAcion;
import controller.admin.AdminTitleUpdateAcion;
import controller.common.Action;
import controller.page.AlertPageAction;
import controller.page.admin.AdminMemberDetailPageAction;
import controller.page.admin.AdminMemberManagementPageAcion;
import controller.page.admin.AdminPageAcion;
import controller.page.admin.AdminTitleAccessPageAcion;
import controller.page.admin.AdminTitleDetailPageAcion;
import controller.page.admin.AdminTitleDetaileAccessPageAcion;
import controller.page.admin.AdminTitleManagementPageAcion;
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
import controller.user.WishAction;

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
      mappings.put("/wish.do", new WishAction());
      mappings.put("/wishListPage.do", new WishListPageAction());
      mappings.put("/makeTitle.do", new MakeTitleAction());
      mappings.put("/makeTitlePage.do", new MakeTitlePageAction());
      mappings.put("/result.do", new ResultAction());
      mappings.put("/resultPage.do", new ResultPageAction());
      // mappings.put("/writeComment.do",);
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
      // 관리자
      mappings.put("/adminPage.do", new AdminPageAcion());
      mappings.put("/adminTitleManagementPage.do", new AdminTitleManagementPageAcion());
      mappings.put("/adminTitleDetailPage.do", new AdminTitleDetailPageAcion());
      mappings.put("/adminMemberManagementPage.do", new AdminMemberManagementPageAcion());
      mappings.put("/adminMemberDetailPage.do", new AdminMemberDetailPageAction());
      mappings.put("/adminTitleDetaileAccessPage.do", new AdminTitleDetaileAccessPageAcion());
      mappings.put("/adminTitleAccessPage.do", new AdminTitleAccessPageAcion());
      mappings.put("/adminTitleAccess.do", new AdminTitleAccessAcion());
      mappings.put("/adminTitleRefuse.do", new AdminTitleRefuseAcion());
      mappings.put("/adminTitleCreate.do", new AdminTitleCreateAcion());
      mappings.put("/adminTitleDelete.do", new AdminTitleDeleteAcion());
      mappings.put("/adminTitleUpdate.do", new AdminTitleUpdateAcion());
      mappings.put("/adminMemberDelete.do", new AdminMemberDeleteAction());
      mappings.put("/alert.do", new AlertPageAction());

   }

   public Action getAction(String commend) {
      return mappings.get(commend);
   }

}