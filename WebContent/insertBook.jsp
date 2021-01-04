<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="book.BookDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 추가</title>
</head>
<body>
<%@ include file = "header.jsp" %>
	<%
		if(request.getParameter("result")!=null){
	%>	
			<script type="text/javascript"> alert("도서 등록 실패"); </script>
	<%
		}
	%>
	<h2>회원 가입</h2>
	<form method="post" action="BookInsert.do">
		<table border="1" style="width:400">
			<tr>
				<th>도서코드</th>
				<td>
					<%	
						BookDAO instance = BookDAO.getInstance();
					%>
					<input type="text" name="bcode" value="<%= instance.getBookCode() %>" readonly>
				</td>
			</tr>
			<tr>
				<th>도서제목</th><td><input type="text" name="btitle"></td>
			</tr>
			<tr>
				<th>도서저자</th>
				<td>
					<input type="text" name="bwriter">
				</td>
			</tr>
			<tr>
				<th>출판사코드</th>
				<td>
					<select name="bpub">
						<option value="1001">양영디지털</option>
						<option value="1002">북스미디어</option>
						<option value="1003">한빛아카데미</option>
						<option value="1004">이지스</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>가격</th><td><input type="text" name="bprice"></td>
			</tr>
			<tr>
				<th>출간날짜</th><td><input type="date" name="bdate"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					 <input type="submit" value="등록"><input type="reset" value="재작성">
				</td>
			</tr>
		</table>
	</form>
	<%@ include file = "footer.jsp" %>
</body>
</html>