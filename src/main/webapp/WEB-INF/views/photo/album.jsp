 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
      
              <c:if test="${!isMain}">
                <form:form method="POST" action="/photo/${albId}/deletealb"><input type="submit" value="Видалити альбом"/></form:form>
                <br> <a href="create/${albId}/photo"> Додати фото</a>  <br>
                </c:if>
                <c:forEach items="${albumModel}" var="photo">
	
	
			<a href="/photo/${photo.id}"><img alt="photo" src="data:image/png;base64, ${photo.file}" width="200px"></a>
			
		
	</c:forEach> 
               </div></div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 