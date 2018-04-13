 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>

<title>Project - registration</title>
  <link rel="stylesheet" type="text/css" href="resources/style/style.css" />
    <link rel="icon"
              type="image/x-icon"
              href="resources/style/favicon.ico">
</head>
<body>
  <div class="header">
        <img src="resources/style/img/logo.png" />
        <a class="exit" href="/login">Login</a>
        

    </div>
     <div class="menuart">



        <div class='article'>

           <div class="midle">
            <form:form action="/register" method="POST" modelAttribute="registerModel" class="login"><br>
                <h1 style="text-align:center;color: #464646 ">Реєстрація</h1><br>
                <form:input type="email" pattern="[a-zA-Z0-9_]+(?:\.[A-Za-z0-9!#$%&amp;'*+/=?^_`{|}~-]+)*@(?!([a-zA-Z0-9]*\.[a-zA-Z0-9]*\.[a-zA-Z0-9]*\.))(?:[A-Za-z0-9](?:[a-zA-Z0-9-]*[A-Za-z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?" placeholder="Введіть email"  path="email" /><form:errors path="email" cssClass="error"/> <br>
                 <form:input type="text" placeholder="Ім'я" path="firstName" pattern="[A-Za-z]{2,}" name="userLogin"/><form:errors path="firstName" cssClass="error"/> <br>
                <form:input type="text" placeholder="Прізвище"  path="lastName" pattern="[A-Za-z]{2,}" name="userLogin"/><form:errors path="lastName" cssClass="error"/> <br>
                <form:input type="text" placeholder="Вік" path="age" value='Введіть вік' /><form:errors path="age" cssClass="error"/><br>
                <form:select path="male" >
								<c:forEach items="${males}" var="male">
									<form:option value="${male}">${male.male}</form:option>
								</c:forEach>
							</form:select>	<br>
                <form:input type="password" placeholder="Введіть пароль" id="password1" name="userPass" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Не менее восьми символов, содержащих хотя бы одну цифру и символы из верхнего и нижнего регистра" path="password"/><form:errors path="password" cssClass="error"/><br>
                <form:input type="password" placeholder="Повторіть пароль" id="password" name="userPass1" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"  path="passwordConfirmation"/><br>
                <button id="register" >Реєстрація</button>

            
             </form:form>



        </div></div>
    </div>


    <%@include file="includes/footer.jsp" %>
</body>
</html> 