 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
      
               <div class="border">
               <form:form method="POST" class="wallform" action="/group/create/" modelAttribute="createGroup"
						>
						 <form:input type="text" placeholder="Назва" path="groupName" /><br> 
						 <form:textarea type="text" placeholder="Про спільноту" path="about" /><br> 
						  <form:select path="category" >
								<c:forEach items="${categorys}" var="category">
									<form:option value="${category}">${category.category}</form:option>
								</c:forEach>
							</form:select><br><br>

						 
						<button  >Створити</button>
            </form:form>
               </div>
               
               </div></div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 