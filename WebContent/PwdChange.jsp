<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>密码修改</title>
        <style type="text/css">
            form
            {
                width:100%;
                height:700px;
                 margin-top: 0px;
                background:#008B8B;
            }
            div
            {
                display:inline-block;
                padding-top: 40px;
                padding-left: 0px;
                
            }
            h2
            {
                font-family: "微软雅黑";
            font-size: 40px;
                color:black;
            }
            #reg
            {
                color:blue;
            }
        </style>
    </head>
    <body background="images/u=3507332086,1782447423&fm=26&gp=0.jpg"
       style=" background-repeat:no-repeat ;
        background-size:100% 100%;
		background-attachment: fixed;
		margin: 100px auto 0 auto;
		width: 400px;
		text-align: center;">
         <form action="ChangePwd">
		           <fieldset>
						<legend>密码修改</legend>

					<table>
						<td>&nbsp;</td>
						<tr>
							<td>
								用户名：
							</td>
							<td>
								${student.studentId }
							</td>
						</tr>
						<td>&nbsp;</td>
						<tr >
							<td>
								旧密码：
							</td>
							<td>
								<input type="password" name="old">
							</td>
					
						</tr>
							<td>&nbsp;</td>
						<tr >
							<td>
								新密码：
							</td>
							<td>
								<input type="password" name="pwd">
							</td>
							
						</tr>
							<td>&nbsp;</td>
						<tr >
							<td>
								确认新密码：
							</td>
							<td>
								<input type="password" name="pwd2">
							</td>
							
						</tr>
						<td>&nbsp;</td>
						<tr>
							<td colspan="2" align="center">
								<input type="reset" value="返回">&emsp;&emsp;&emsp;
								<input type="submit" value="提交">
							</td>
						</tr>
					</table>



					</fieldset>
		    </form>    
		      
		
            
        
    </body>
</html>