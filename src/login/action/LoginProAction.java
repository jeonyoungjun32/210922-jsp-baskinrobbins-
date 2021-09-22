package login.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberLoginService;
import vo.Member;

public class LoginProAction implements Action {

	//수정중
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		MemberLoginService memberLoginService = new MemberLoginService();
		Member member = memberLoginService.getMember(id, pw);
		
		if(member != null) {
			session.setAttribute("member",member );
			forward = new ActionForward("/index.jsp",false);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인 실패');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		return forward;
	}

}
