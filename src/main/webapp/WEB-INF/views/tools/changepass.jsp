 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <form:form action="/changepass/change" method="POST" modelAttribute="changePass" class="login"><br>
  <form:input type="password" placeholder="Введіть пароль"  name="userPass" pattern="[A-Za-z0-9]{3,}" path="password"/><br>
                <form:input type="password" placeholder="Повторіть пароль"  name="userPass1" pattern="[A-Za-z0-9]{3,}" path="passwordConfirmation"/><br>
                <button id="register" >Змінити пароль</button>
     
     </form:form>
     
     
       </div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 