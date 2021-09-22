package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import vo.Member;

public class AdminMemberListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<Member> memberList = new ArrayList<Member>();
				
		HttpSession session = request.getSession(false);
		
		Member member = (Member)session.getAttribute("member");
		
		MemberInfoService memberInfoService =new MemberInfoService();
		
		memberList = memberInfoService.getListMember();
		
		ActionForward forward = null;
		
		if(!member.getAuthor().equalsIgnoreCase("2")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('관리자가 아닙니다');");
			out.print("history.back();");
			out.print("</script>");
		} else if (member.getAuthor().equalsIgnoreCase("2")) {
			request.setAttribute("memberList", memberList);
			forward = new ActionForward();
			forward.setPath("mypage/adminMemberList.jsp");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
