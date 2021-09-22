package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.action.Action;
import login.action.ActionForward;
import mypage.action.AdminProductListProAction;
import product.action.IceCakeListProAction;
import product.action.CoffeeListProAction;

/**
 * Servlet implementation class ProductIceController
 */
@WebServlet("*.PI")
public class ProductIceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductIceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String RequestURI = request.getRequestURI();
		String conextPath = request.getContextPath();
		String command = RequestURI.substring(conextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		System.out.println(command);
		if(command.equals("/index.PI")) {//메인 화면
			forward = new ActionForward();
			forward.setPath("/index.jsp");
		}  else if (command.equals("/iceCakeList.PI")) {//아이스케이크 리스트 폼 + 요청
			action = new IceCakeListProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CoffeeList.PI")) {//아이스커피 리스트 폼 + 요청
			action = new CoffeeListProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}

}
