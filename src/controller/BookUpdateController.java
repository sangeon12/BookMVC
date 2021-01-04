package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.BookDAO;
import book.BookVO;

@WebServlet("/BookUpdate.do")
public class BookUpdateController extends HttpServlet{
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
	
	req.setCharacterEncoding("utf-8");
	BookDAO instance = BookDAO.getInstance();   //MemberDAO, MemberVO 인스턴와 생성자를 만든다.
	BookVO vo = new BookVO();
	
	String msg = null;
	vo.setBcode(Integer.parseInt(req.getParameter("bcode")));
	vo.setBtitle(req.getParameter("btitle"));
	vo.setBwriter(req.getParameter("bwriter"));
	vo.setBpub(Integer.parseInt(req.getParameter("bpub")));   //수정할 정보를 MemberVO생성자에 set으로 다 넣어준다
	vo.setBprice(Integer.parseInt(req.getParameter("bprice")));
	vo.setBdate(Date.valueOf(req.getParameter("bdate")));
	try {
		
		int update = instance.updateBook(vo);
		
		if(update > 0){
			msg = "BookList.do?up=1"; 
		}else{							
			msg = "BookList.do?up=0";	
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	
	RequestDispatcher rd = req.getRequestDispatcher(msg);
	rd.forward(req, resp);
}
}
