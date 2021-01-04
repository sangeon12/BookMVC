<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 삭제</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		int bcode = Integer.parseInt(request.getParameter("bcode"));
	%>
	<script type="text/javascript">
	if (confirm("정말 삭제하시겠습니까?") == true){
		location.href='BookDelete.do?bcode=<%= bcode %>';	
	}else{   //취소
		location.href='BookList.do?cancel=0';
	}
	</script>
</body>
</html>