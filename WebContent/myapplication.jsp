<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
    
      <tr>
        <th align="center" valign="middle" class="borderright">编号</th>
        <th align="center" valign="middle" class="borderright">申请社团</th>
        <th align="center" valign="middle" class="borderright">申请者</th>
        <th align="center" valign="middle" class="borderright">申请时间</th>
        <th align="center" valign="middle" class="borderright">申请内容</th>
        <th align="center" valign="middle" class="borderright">申请结果</th>
        <th align="center" valign="middle" class="borderright">申请类型</th>
      </tr>
      <c:forEach items="${ApplicationAllS}" var="U" varStatus="status">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="center" valign="middle" class="borderright borderbottom">${status.index+1 }</td>
        <td align="center" valign="middle" class="borderright borderbottom">${U.applicationResult}</td>
        <td align="center" valign="middle" class="borderright borderbottom">${U.applicationNum}</td>
        <td align="center" valign="middle" class="borderright borderbottom">${U.applicationTime}</td>
        <td align="center" valign="middle" class="borderright borderbottom">${U.applicationContent}</td>
		<td align="center" valign="middle" class="borderright borderbottom">${U.applicationState}</td>
        <td align="center" valign="middle" class="borderright borderbottom">${U.applicationType }</td>
      </tr>
      <tr>


      
      </c:forEach>
      
    </table>
</body>
</html>