package svc;
import static db.Jdbcutil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MyPageDAO;
import vo.Member;

public class MemberInfoService {
	//회원 전체 목록
	public ArrayList<Member> getListMember() throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		ArrayList<Member> memberList = myPageDAO.getListMember();
		
		close(con);
		
		return memberList;
	}
}
