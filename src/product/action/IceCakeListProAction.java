package product.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.action.Action;
import login.action.ActionForward;
import svc.ProductService;
import vo.Member;
import vo.Product;

public class IceCakeListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<Product> productList = new ArrayList<Product>();
		
		ProductService productService =new ProductService();
				
		productList = productService.getListProduct();
		
		ActionForward forward = null;
		
		request.setAttribute("productList", productList);
		forward = new ActionForward();
		forward.setPath("iceCakeList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
