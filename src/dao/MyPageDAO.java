package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Member;
import vo.Product;

import static db.Jdbcutil.*;

public class MyPageDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
//	private MemberDAO() {}
	
	private static MyPageDAO memberDAO;
	
	public static MyPageDAO getInstance() {
		if(memberDAO == null) {
			memberDAO = new MyPageDAO();
		}
		
		return memberDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	/*회원 정보 수정*/
	public int memberUpdate(Member member) {
		sql = "update member set pw = ?, address_number =?, address=?, address_contents=?,"
			+ " email = ?, birth=?, gender = ? where id = ? ";
		int memberUpdateCount=0;
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,member.getPw());
			pstmt.setString(2,member.getAddress_number());
			pstmt.setString(3,member.getAddress());
			pstmt.setString(4,member.getAddress_contents());
			pstmt.setString(5,member.getEmail());
			pstmt.setString(6,member.getBirth());
			pstmt.setString(7,member.getGender());
			pstmt.setString(8,member.getId());
			
			memberUpdateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("memberUpdate 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return memberUpdateCount;
	}
	
	/*회원 탈퇴*/
	public int memberDelete(String id) {
		sql="delete from member where id = ?";
		int memberDeleteCount=0;
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			memberDeleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("memberDelete 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return memberDeleteCount;
	}
	
	/*돈 입금*/
	public int MemberSetMoney(String money, String id) {
		sql = "update member set money = money + ? where id = ?";
		int MemberSetMoneyCount=0;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, money);
			pstmt.setString(2, id);
			
			MemberSetMoneyCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("MemberSetMoney 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return MemberSetMoneyCount;
	}
	
	/*돈입금 session에 다시 담을 회원 조회*/
	public Member MemberSelect(String id) {
		sql = "select * from member where id = ?";
		Member member = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			member = new Member(
					rs.getString("id"),
					rs.getString("pw"),
					rs.getString("name"),
					rs.getString("address_number"),
					rs.getString("address"),
					rs.getString("address_contents"),
					rs.getString("email"),
					rs.getString("birth"),
					rs.getString("gender"),
					rs.getString("grade"),
					rs.getString("id_code"),
					rs.getString("join_member"),
					rs.getInt("money"),
					rs.getInt("point"),
					rs.getString("author"));
			}
			
		} catch (Exception e) {
			System.out.println("MemberSelect 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}
	
	/*회원 전체 조회*/
	public ArrayList<Member> getListMember() {
		sql = "select * from member";
		ArrayList<Member> memberList = new ArrayList<Member>();
		Member member= null;
		try {
			pstmt=con.prepareStatement(sql);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setAddress_number(rs.getString("address_number"));
				member.setAddress(rs.getString("address"));
				member.setAddress_contents(rs.getString("address_contents"));
				member.setEmail(rs.getString("email"));
				member.setBirth(rs.getString("birth"));
				member.setGender(rs.getString("gender"));
				member.setGrade(rs.getString("grade"));
				member.setId_code(rs.getString("id_code"));
				member.setJoin_member(rs.getString("join_member"));
				member.setMoney(rs.getInt("money"));
				member.setPoint(rs.getInt("point"));
				member.setAuthor(rs.getString("author"));
				
				memberList.add(member);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListMember 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return memberList;
	}

	/*관리자 물건 등록(수정중)*/
	public int insertProduct(Product product) {
		sql="select concat('2',date_format(sysdate(),'%Y%m%d'),count(Product_name)) from Product";
		String product_code="";
		String product_inout="i";
		int insertCount=0;
		try {
			pstmt = con.prepareStatement(sql);
			
			rs= pstmt.executeQuery();
			if(rs.next()) product_code = rs.getString(1);
			
			sql ="insert into Product values(?,?,?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, product_code);
			pstmt.setString(2, product.getProduct_name());
			pstmt.setInt(3, product.getProduct_kcal());
			pstmt.setString(4, product.getProduct_allergy());
			pstmt.setInt(5, product.getProduct_price());
			pstmt.setString(6, product_inout);
			pstmt.setString(7, product.getProduct_date());
			pstmt.setInt(8, 0);
			
			insertCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertProduct 오류 : "+e);
			e.printStackTrace();
		} finally {
			
		}
		return insertCount;
	}

	/*물건 조회*/
	public ArrayList<Product> getListProduct() {
		sql = "select * from Product";
		ArrayList<Product> productList = new ArrayList<Product>();
		Product product= null;
		try {
			pstmt=con.prepareStatement(sql);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				product = new Product();
				
				product.setProduct_code(rs.getString("product_code"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_kcal(Integer.parseInt(rs.getString("product_kcal")));
				product.setProduct_allergy(rs.getString("product_allergy"));
				product.setProduct_price(Integer.parseInt(rs.getString("product_price")));
				product.setProduct_choice(rs.getString("product_choice"));
				product.setProduct_inout(rs.getString("product_inout"));
				product.setProduct_date(rs.getString("product_date"));
				product.setProduct_count(Integer.parseInt(rs.getString("product_count")));
				
				productList.add(product);
				} while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListProduct 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return productList;
	}

}
