 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
        
             <div class="artcont" >
              <div class="border">  
            <form:form method="POST" class="wallform" action="/group/${groupId}/settings/confirm" modelAttribute="editGroup"
					enctype="multipart/form-data"	>
            <form:input type="text" placeholder="Name" path="groupName"  /><br>
            <form:textarea type="text" placeholder="About" path="about"  /><br>
              
								
            <button  >Зберегти зміни</button>
            </form:form> </div><br>  <div class="border">  
              <form:form method="POST" class="wallform" action="/group/${groupId}/settings/photo" modelAttribute="photoModel"
				enctype="multipart/form-data"		>
    
              <form:input path="file" type="file"  title="Profile Image" /><br>
								
            <button  >Завантажити фото</button></form:form>
            
        </div> 
                 </div>
       </div>
      
     
               
               </div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 