package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MyPageDAO;
import vo.Member;
import vo.Product;

public class ProductService {
	
	//물건 등록 서비스
	public boolean insertProduct(Product product) throws Exception {
		
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		int insertCount = myPageDAO.insertProduct(product);
		
		boolean insertSuccess = false;
		if(insertCount > 0) {
			commit(con);
			insertSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		
		return insertSuccess;
	}

	//물건 리스트
	public ArrayList<Product> getListProduct() throws Exception {
		Connection con = getConnection();
		
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		
		myPageDAO.setConnection(con);
		
		ArrayList<Product> productList = myPageDAO.getListProduct();
		
		close(con);
		
		return productList;
	}
}
