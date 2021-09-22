package svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.Jdbcutil.*;
import vo.Member;

public class MemberJoinService {
	
	public boolean JoinMember(Member member) throws Exception {
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		int insertmember = memberDAO.insertMember(member);
		
		boolean isInsertSucess = false;
		if(insertmember >0 ) {
			commit(con);
			isInsertSucess= true;
		} else {
			rollback(con);
		}
		close(con);
		return isInsertSucess;
	}
	
	public int IdCheck(String id) throws Exception {
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		int idCheckCtn = memberDAO.idCheckMember(id);
		
		close(con);
		return idCheckCtn;
	}
	
	/*우편 여기에 넣기*/
}
