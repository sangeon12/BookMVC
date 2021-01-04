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

@WebServlet("/BookDelete.do")
public class BookDeleteController extends HttpServlet{
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
	req.setCharacterEncoding("utf-8");//request를 UTF-8로 받아준다
	
	BookDAO instance = BookDAO.getInstance();//BookDAO에서 인스턴를 받아온다
	int bcode = Integer.parseInt(req.getParameter("bcode"));//form태그롤 넘겨준 bcode를 받아온다
	
	String msg = null;//값을 넘겨줄 경로를 msg에 넣어준다
	
	try {//sql문을 사용하기 위해 try catch문을 쓴다
		if(instance.deleteBook(bcode) > 0){//BookDAO에 deleteBook함수를 실행해 리턴된 값이 영보다 msg에다가 경로를 넣어준다
			msg = "BookList.do?del=1";//성공했을때 del=1를 값을 같이 BookList 컨트롤러로 넘겨준다
		}else{//만약 실패했을때
			msg = "BookList.do?del=0";//실패했을때 del=0를 값을 같이 BookList 컨트롤러로 넘겨준다
		}
	} catch (Exception e) {//catch로 오류를 잡아줌
		e.printStackTrace();
	}
	
	RequestDispatcher rd = req.getRequestDispatcher(msg);//msg에 넣어준 경로를 넣어준다
	rd.forward(req, resp);//forward를 해줌
}
}
