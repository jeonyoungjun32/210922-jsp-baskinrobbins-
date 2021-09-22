package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.action.Action;
import login.action.ActionForward;
import svc.ProductService;
import vo.Product;

public class CoffeeListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Product> productList = new ArrayList<Product>();
		
		ProductService productService =new ProductService();
				
		productList = productService.getListProduct();
		
		ActionForward forward = null;
		
		request.setAttribute("productList", productList);
		forward = new ActionForward();
		forward.setPath("CoffeeList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
