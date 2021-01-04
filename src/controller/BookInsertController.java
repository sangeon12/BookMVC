package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.BookDAO;
import book.BookVO;

@WebServlet("/BookInsert.do")
public class BookInsertController extends HttpServlet{
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
	req.setCharacterEncoding("utf-8");//request를 UTF-8로 받아줌
	
	int bcode = Integer.parseInt(req.getParameter("bcode"));//request로 bcode를 받아옴
	String btitle = req.getParameter("btitle");//request로 btitle를 받아옴
	String bwriter = req.getParameter("bwriter");//request로 bwriter를 받아옴
	int bpub = Integer.parseInt(req.getParameter("bpub"));//request로 bpub를 받아옴
	int bprice = Integer.parseInt(req.getParameter("bprice"));//request로 bprice를 받아옴
	Date bdate = Date.valueOf(req.getParameter("bdate"));//request로 bdate를 받아옴
	
	BookDAO instance = BookDAO.getInstance();//BookDAO로 인스턴스를 받아옴
	BookVO vo = new BookVO();//BookVO생성자를 만들어줌
	vo.setBcode(bcode);//vo set으로 bcode넣어줌
	vo.setBtitle(btitle);//vo set으로 btitle를 넣어줌
	vo.setBwriter(bwriter);//vo set으로 bwriter를 넣어줌
	vo.setBpub(bpub);//vo set으로 bpub를 넣어줌
	vo.setBprice(bprice);//vo set으로 bprice를 넣어줌
	vo.setBdate(bdate);//vo set으로 bdate를 넣어줌
	
	String msg = "";//값을 넘겨줄 경로를 저장하는 변수
	try {//sql문 실행을 위해 try catch문을 써줌
		int result = instance.insertBook(vo);//
		if(result > 0) {
			msg = "BookList.do?result=1";
		}else {
			msg = "BookList.do?result=0";
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	RequestDispatcher rd = req.getRequestDispatcher(msg);
	rd.forward(req, resp);
}
}
