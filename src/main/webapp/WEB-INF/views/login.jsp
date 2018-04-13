 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>

<title>Project login</title>
  <link rel="stylesheet" type="text/css" href="/resources/style/style.css" />
  <link rel="icon"
              type="image/x-icon"
              href="/resources/style/favicon.ico">
</head>

<body>
    <div class="header">
        <img src="resources/style/img/logo.png" />
     
       
       

    </div>
    <div class="menuart">

        <div class='article'>
<div class="midle">
    
            <form:form action="/login" method="POST" class="login" ><br>
                <h1 style="text-align:center;color: #464646; ">Авторизація</h1><br>
               
                <input type="email" placeholder="Введіть email" name="email" id="email"/><br>
                <input type="password" placeholder="Введіть пароль" name="password"  id="password" pattern="[A-Za-z0-9]{3,}"><br>
                	Запам'ятати мене:
	<input type="checkbox" name="rememberme"><br>
                <button  value="login" >Увійти</button>

 <c:if test="${param.fail}">
	<p style="color:red;">Login or password is incorrect</p>
	</c:if>

            </form:form><form class="login" >
             Ще немає аккаунту?<a class="exit" href="/register"> Зареєструйтесь</a></form>
</div>


        </div>
    </div>
<%@include file="includes/footer.jsp" %>
</body>
</html> 