package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;

import dao.MyPageDAO;
import vo.Member;

public class MemberMoneyAndPointService {
	
	//돈 입금
	public boolean setMoney(String money, String id) throws Exception {
		
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		int MemberSetMoneyCount = myPageDAO.MemberSetMoney(money, id);
		
		boolean MemeberSetMoneySuccess = false;
		if(MemberSetMoneyCount> 0) {
			commit(con);
			MemeberSetMoneySuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return MemeberSetMoneySuccess;
	}

	public Member setMember(String id) throws Exception{
		
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		Member member = myPageDAO.MemberSelect(id);
		close(con);
		return member;
	}
}
