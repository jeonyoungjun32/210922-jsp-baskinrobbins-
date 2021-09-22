package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

public class MemberLoginService {
	
	public Member getMember(String id, String pw) throws Exception {
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		Member member = memberDAO.memberLogin(id, pw);
		close(con);
		
		return member;
	}
	
	//아이디 검색
	public String getMemberId(String member_name, String member_email) throws Exception {
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		String getId = memberDAO.memberIDFind(member_name, member_email);
		close(con);
		
		return getId;
	}
	
	//아이디 검색후 비밀번호 수정
	public boolean getMemberIDRevise(String id, String pw) throws Exception {
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		int memberIDReviseCtn = memberDAO.memberIDRevise(id, pw);
		
		boolean memberIDReviseSuccess = false;
		if(memberIDReviseCtn > 0) {
			commit(con);
			memberIDReviseSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return memberIDReviseSuccess;
	}
	
	//비밀번호 찾을 아이디 검색
	public String getMemberFind(String id, String email) {
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		String pw = memberDAO.memberIDFindPW(id, email);
		
		close(con);
		
		return pw;
	}

}
