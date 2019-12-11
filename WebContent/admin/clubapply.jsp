<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" "%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>创建社团</title>
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
	<body>
	 
						
						
					   

				<body background=""
		       style=" background-repeat:no-repeat ;
		        background-size:100% 100%;
				background-attachment: fixed;">
		        <form action="ClubApply">
		            <div>
		            <h3>
		            创建社团申请表
		        </h3>
		
		            <p>
		                社团名称:<input type="text" name="clubname"/>
		            </p>
		            <p>
						社长学号:${loginName} 
		        	</p>
					<p>
		                社团简介:<input type="text" name="clubcontent"/>
		            </p>
					<!-- <p>
		                申请理由:<input type="text" name="club1"/>
		            </p> -->
		            <!-- 或者做成submit -->
		            <p>
		                填写完成; <input  type="submit" value="申请" style="font-size: 18px; background-color: #FFFFFF;">
		            </p>
		            </div>
		</form>  
		
	</body>
</html>