package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.Product;

public class AdminProductInserProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Product product = new Product();
		
		product.setProduct_code(request.getParameter("product_code"));
		product.setProduct_name(request.getParameter("product_name"));
		product.setProduct_kcal(Integer.parseInt(request.getParameter("product_kcal")));
		product.setProduct_allergy(request.getParameter("product_allergy"));
		product.setProduct_price(Integer.parseInt(request.getParameter("product_price")));
		product.setProduct_date(request.getParameter("product_date"));
		
		ProductService productService = new ProductService();
		
		boolean insertSuccess = productService.insertProduct(product);
		
		ActionForward forward = null;
		
		if(!insertSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('등록실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('등록성공');");
			out.print("</script>");
			forward = new ActionForward();
			forward.setPath("adminProductList.bg");
		}
		
		return forward;
	}

}
