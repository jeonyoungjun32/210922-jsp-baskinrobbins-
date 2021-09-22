package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.action.Action;
import mypage.action.ActionForward;
import mypage.action.AdminMemberListProAction;
import mypage.action.AdminProductInserProAction;
import mypage.action.AdminProductListProAction;
import mypage.action.MemberMypageDeleteProAction;
import mypage.action.MemberMypageMoneyProAction;
import mypage.action.MemberMypageUpdateProAction;

/**
 * Servlet implementation class BKFrontController
 */
@WebServlet("*.bg")
public class MyPageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String RequestURI = request.getRequestURI();
		String conextPath = request.getContextPath();
		String command = RequestURI.substring(conextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		System.out.println(command);
		if(command.equals("/index.bg")) {//메인화면
			forward = new ActionForward();
			forward.setPath("index.jsp");
		} else if (command.equals("/memberMypage.bg")) {//내 정보 요청
			forward = new ActionForward();
			forward.setPath("/mypage/mypage.jsp");
		} else if(command.equals("/memberMypageForm.bg")) {//내 정보 수정 폼
			forward = new ActionForward();
			forward.setPath("/mypage/mypageupdate.jsp");
		} else if (command.equals("/memberMypageUpdatePro.bg")) {//내 정보 수정 '요청'
			action = new MemberMypageUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberMypageUpdateSuccess.bg")) {//내 정보 수정 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/memberMypageUpdateSuccess.jsp");
		} else if (command.equals("/memberLeaveForm.bg")) {//내 정보 탈퇴 폼
			forward = new ActionForward();
			forward.setPath("mypage/mypageLeave.jsp");
		} else if (command.equals("/memberLeavePro.bg")) {//내 정보 탈퇴 요청
			action = new MemberMypageDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberMypageDeleteSuccess.bg")) {//내 정보 탈퇴 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/memberMypageDeleteSuccess.jsp");
		} else if (command.equals("/memberLeaveForm.bg")) {//내 포인트 폼
			forward = new ActionForward();
			forward.setPath("mypage/mypageLeave.jsp");
		} else if (command.equals("/memberMoneyForm.bg")) {//돈 입금 페이지 폼
			forward = new ActionForward();
			forward.setPath("mypage/mypageMoney.jsp");
		} else if (command.equals("/memberMoneyPro.bg")) {//돈 입금 페이지 요청
			action = new MemberMypageMoneyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberMypageMoneySuccess.bg")) {//돈 입금 성공 폼
			forward = new ActionForward();
			forward.setPath("mypage/memberMypageMoneySuccess.jsp");
		} else if (command.equals("/memberPointForm.bg")) {//돈 포인트 폼
			forward = new ActionForward();
			forward.setPath("mypage/mypagePoint.jsp");
		} 
		/*관리자 페이지 수정중*/
		else if (command.equals("/adimnMemberListPro.bg")) {//관리자 회원 전체 리스트 조회 폼 + 요청
			action = new AdminMemberListProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminProductInserForm.bg")) {//물건 등록 폼
			forward = new ActionForward();
			forward.setPath("mypage/adminProductInsertForm.jsp");
		} else if (command.equals("/adminProductInserPro.bg")) {//물건 등록 요청 (만드는 중)
			action = new AdminProductInserProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminProductListPro.bg")) {//물건 리스트 폼 + 요청
			action = new AdminProductListProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		} 
	}
}
