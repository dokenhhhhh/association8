<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table  width="600" border="1" cellpadding="0" >
   <form action="Test" method="get">
   <input  type="submit" value="查询" onClick="LoginSerlet()"style="font-size: 18px; background-color: #FFFFFF;">
  		<tr>
  			<th>社团ID</th>
	  		<th>社团名称</th>
	  		<th>社长学号</th>
	  		<th>社员数量</th>
	  		<th>创建时间</th>
	  		
  		</tr>
  
	<c:forEach var="U" items="${clubAll}" > 
	
      	 <tr>
	       <td>${U.clubId}<td>
	       <td>${U.clubName}<td>
	       <td>${U.presidentnum}<td>
	       <td>${U.number}<td>
	       <td>${U.clubCreationTime}<td>
	       
	   </tr>
	
    </c:forEach>  
 	</form>
     
    </table>
</body>
</html>