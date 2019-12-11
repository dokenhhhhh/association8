<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Club"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="Test" method="get">
   <input  type="submit" value="查询" onClick="LoginSerlet()"style="font-size: 18px; background-color: #FFFFFF;">
</form>
<table  >
  		<tr>
  			<th>社团ID</th>
	  		<th>社团名称</th>
	  		<th>社长学号</th>
	  		<th>社员数量</th>
	  		<th>创建时间</th>
	  		
  		</tr>
     
	<c:forEach var="U" items="${userAll}" > 
	
      	 <tr>
	       <td>${U.clubId}</td>
	       <td>${U.clubName}</td>
	       <td>${U.presidentnum}</td>
	       <td>${U.number}</td>
	       <td><fmt:formatDate value="${U.clubCreationTime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
	       
	   </tr>
	
    </c:forEach>  
 	
   
    </table>

</body>
</html>