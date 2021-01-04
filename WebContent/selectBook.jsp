	<%@ page import="book.BookDAO" %>
	<%@ page import="book.BookVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="java.util.*" %>
    <%@ page import="java.util.Date" %>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록</title>
</head>
<body>
<%@ include file = "header.jsp" %>
	<%
		
		ArrayList<BookVO> booklist = (ArrayList<BookVO>)request.getAttribute("list");
		 
		
		if(request.getParameter("result")!=null){
			int result = Integer.parseInt(request.getParameter("result"));
			
			if(result >0){
		%>
			<script type="text/javascript">
				alert("도서 등록 완료");
			</script>
		<%		
			}else{
		%>
				<script type="text/javascript">
				alert("도서 등록 실패");
			</script>
		<%		
			}	
		}
		
		if(request.getParameter("del")!=null){
			int result = Integer.parseInt(request.getParameter("del"));
			if(result > 0){
		%>
				<script type="text/javascript">
					alert("도서 삭제 완료");
				</script>
		<%	
			}else{
		%>
				<script type="text/javascript">
					alert("도서 삭제 실패");
				</script>
		<%
			}
		}
		
		if(request.getParameter("up") != null){
			int result = Integer.parseInt(request.getParameter("up"));
			if(result > 0){
		%>
				<script type="text/javascript">
					alert("도서  정보 수정 성공");
				</script>
		<%		
			}else{
		%>
				<script type="text/javascript">
					alert("도서  정보 수정 실패");
				</script>
		<%		
			}
		}
	%>
	
	<h2>회원 목록</h2>
	<table border="1">
		<tr align="center">
			<th width="100">도서코드</th>
			<th width="100">도서제목</th>
			<th width="100">도서저자</th>
			<th width="100">출판사코드</th>
			<th width="100">가격</th>
			<th width="100">출간날짜</th>
			<th width="100">삭제</th>
		</tr>
	<%
		for(BookVO list : booklist){
			
		String[] pub = {"양영디지털", "북스미디어", "한빛아카데미","이지스"};
		int num = 1001;
	    int num2 = 0;
		for(int i = 0; i <= pub.length; i++){
			if(list.getBpub() == num){
				num2 = i;
			}
			num++;
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		String strNowDate = simpleDateFormat.format(list.getBdate());
		
		 DecimalFormat df = new DecimalFormat("#,###");
		 String num3 = df.format(list.getBprice());
		
	%>
	<tr>
		<td width="100"><a href="updateBook.jsp?bcode=<%= list.getBcode() %>"><%= list.getBcode() %></a></td>
		<td width="100"><%= list.getBtitle() %></td>
		<td width="100"><%= list.getBwriter() %></td>
		<td width="100"><%= pub[num2] %></td>
		<td width="100"><%= num3 %></td>
		<td width="125"><%= strNowDate %></td>
		<td width="100"><a href="deleteBook.jsp?bcode=<%= list.getBcode() %>">삭제</a></td> 
	</tr>
	
	<%
	}
	%>
	</table>
	<%@ include file = "footer.jsp" %>
</body>
</html>