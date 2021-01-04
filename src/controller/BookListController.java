package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.BookDAO;
import book.BookVO;

@WebServlet("/BookList.do")
public class BookListController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doProcess(req, resp);
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doProcess(req, resp);
	}

public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	
	BookDAO instance = BookDAO.getInstance();
	ArrayList<BookVO> listVO = instance.list;
	
	
	if(instance.first) {
		instance.firstBook();	
	}
	
	String msg = "selectBook.jsp"; //selectBook.jsp
	if(req.getParameter("result")!=null){
		int result = Integer.parseInt(req.getParameter("result"));
		
		if(result > 0) {
			msg = "selectBook.jsp?result=1";
		}else {
			msg = "selectBook.jsp?result=0";
		}
	}
			
	if(req.getParameter("del")!=null){
		int result = Integer.parseInt(req.getParameter("del"));
		
		if(result > 0) {
			msg = "selectBook.jsp?del=1";
		}else {
			msg = "selectBook.jsp?del=0";
		}
	}
			
	if(req.getParameter("up") != null){
		int result = Integer.parseInt(req.getParameter("up"));
		
		if(result > 0) {
			msg = "selectBook.jsp?up=1";
		}else {
			msg = "selectBook.jsp?up=0";
		}
	}
	
	if(req.getParameter("cancel") != null) {
		msg = "selectBook.jsp";
	}
	
	req.setAttribute("list", listVO);
	RequestDispatcher rd = req.getRequestDispatcher(msg);
	rd.forward(req, resp);
}
}
