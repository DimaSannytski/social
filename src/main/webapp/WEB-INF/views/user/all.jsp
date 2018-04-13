 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
       <form:form action="/search" method="POST"  >
  <input name="search" placeholder="Введіть ім'я" type="text"/> 
   <input type="submit" value="Знайти">
   </form:form><br>
   
        
			          <h1> Усі користувачі</h1> <br>
           <c:forEach items="${usersModel}" var="user">
	
			<div style="margin-left: 60px;"><a class="usser" href="/user/${user.id}"><img  alt="Profile" src="data:image/png; base64, ${user.photo}" style="height:120px; "><span class="left-top">  ${user.firstName} ${user.lastName}</span></a>
			
	</div>
	</c:forEach> 
  
               
               </div></div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 