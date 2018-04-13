 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
      
               
               <form:form method="POST" class="wallform" action="/photo/create/photo" modelAttribute="photoModel"
						enctype="multipart/form-data">
						<%-- <form:input type="text" placeholder="Name" path="name" /><br> --%>
						 <form:input path="file" type="file"  title="Photo" /><br>
						<button  >Загрузити фото</button>
            </form:form>
               
               
               </div></div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 