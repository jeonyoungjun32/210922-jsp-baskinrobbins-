package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.action.Action;
import login.action.ActionForward;
import login.action.LogOutProAction;
import login.action.LoginFindIDProAction;
import login.action.LoginFindPWProAction;
import login.action.LoginIDFindPWReviseProAction;
import login.action.LoginIdCheckProAction;
import login.action.LoginJoinProAction;
import login.action.LoginProAction;

/**
 * Servlet implementation class BKFrontController
 */
@WebServlet("*.bk")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFrontController() {
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
		if(command.equals("/login.bk")) {//로그인 화면
			forward = new ActionForward();
			forward.setPath("/login/login.jsp");
		} else if(command.equals("/loginFindIDForm.bk")) {//아이디 찾기 창
			forward = new ActionForward();
			forward.setPath("/login/loginFindIDForm.jsp");
		} else if (command.equals("/loginFindIDPro.bk")) {//아이디 찾기 요청
			action = new LoginFindIDProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/loginIDFindPWReviseForm.bk")) {//아이디 찾은 후 비밀번호 변경 창
			forward = new ActionForward();
			forward.setPath("/login/loginIDFindPWReviseForm.jsp");
		} else if (command.equals("/loginIDFindPWRevisePro.bk")) {//아이디 찾은 후 비밀번호 변경 요쳥
			action = new LoginIDFindPWReviseProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/loginFindPWForm.bk")) {//비밀번호 찾을 아이디 찾기 창
			forward = new ActionForward();
			forward.setPath("/login/loginFindPWForm.jsp");
		} else if (command.equals("/loginFindPWPro.bk")) {//비밀번호 찾을 아이디 찾기 요청
			action = new LoginFindPWProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/loginIDFindPWReviseForm2.bk")) {//비밀번호 찾은 후 비밀번호 변경 창
			forward = new ActionForward();
			forward.setPath("/login/loginIDFindPWReviseForm2.jsp");
		}  else if (command.equals("/loginIdCheck.bk")) {//id 중복체크 폼
			forward = new ActionForward();
			forward.setPath("/login/joinCheckForm.jsp");
		} else if (command.equals("/idCheckFail.bk")) {//id 중복체크 성공 페이지
			forward = new ActionForward();
			forward.setPath("/login/idCheckFailForm.jsp");
		} else if (command.equals("/idCheckSuccess.bk")) {//id 중복체크 실패 페이지
			forward = new ActionForward();
			forward.setPath("/login/idCheckSuccessForm.jsp");
		} else if (command.equals("/loginIdCheckPro.bk")) {//id 중복체크 요청
			action = new LoginIdCheckProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/loginJoinForm.bk")) {//회원 가입 창
			forward = new ActionForward();
			forward.setPath("/login/joinForm.jsp");
		} else if (command.equals("/loginjoinPro.bk")) {//회원 가입 요청
			action = new LoginJoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/joinSuccess.bk")) {//회원 가입 성공 폼
			forward = new ActionForward();
			forward.setPath("/login/joinSuccess.jsp");
		} else if (command.equals("/loginPro.bk")) {//로그인 요청
			action = new LoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/logOutPro.bk")) {//로그아웃 요청
			action = new LogOutProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/index.bk")) {//내 메인 페이지 이동
			forward = new ActionForward();
			forward.setPath("index.jsp");
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
