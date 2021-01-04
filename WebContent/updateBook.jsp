<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="book.BookDAO" %><%-- BookDAO임포트 --%>
<%@ page import="book.BookVO" %><%-- BookVO임포트 --%>
<%@ page import="java.sql.*" %><%-- java.sql.*임포트 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
<%@ include file = "header.jsp" %><%-- 홈페이지 상단 --%>
	<%
		request.setCharacterEncoding("utf-8"); //request를 UTF-8로 받음
		int bcode = Integer.parseInt(request.getParameter("bcode"));//form태그로 보낸 bcode를 받아옴
		BookDAO instance = BookDAO.getInstance();//BookDAO에서 인스턴스를 가져옴
		BookVO vo = instance.getBook(bcode);//BookDAO에 있는 함수를 이용해 회원 정보를 BookVO형으로 가져옴  
	%>
			<form action="BookUpdate.do" method="post"><%-- form태그를 만들고 controller로 값을 보내줌 --%>
				<table border="1" style="width: 400"><%-- 리스트에 있는 정보를 표로 출력하기위해 table로 표를 만든다 --%>
					<tr><td colspan="2" align="center"><b>도서 정보 수정</b></td></tr>
					<tr><th>도서코드</th><td><input type="text" name="bcode" value="<%= vo.getBcode() %>" readonly></td></tr>
					<tr><th>도서제목</th><td><input type="text" name="btitle" value="<%= vo.getBtitle() %>"></td></tr>
					<tr><th>도서저자</th> <td><input type="text" name="bwriter" value="<%= vo.getBwriter() %>"></td></tr> <%-- disabled --%>
					<tr><th>출판사코드</th> <td><select name="bpub">
						<option value="1001">양영디지털</option>
						<option value="1002">북스미디어</option>
						<option value="1003">한빛아카데미</option>
						<option value="1004">이지스</option>
					</select></td></tr>
					<tr><th>가격</th> <td><input type="text" name="bprice" value="<%= vo.getBprice() %>"></td></tr>
					<tr><th>출간날짜</th> <td><input type="date" name="bdate" value="<%= vo.getBdate() %>"></td></tr>
						
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="확인"> <input type="reset" value="취소">
						</tr>
				</table>
			</form>
			<%@ include file = "footer.jsp" %>
</body>
</html>