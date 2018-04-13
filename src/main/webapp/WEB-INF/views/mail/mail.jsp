 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
       
             
           <c:if test="${dialogModel.size()==0}"> <div class="text-1em">  У вас немає повідомлень</div></c:if>
       <c:forEach items="${dialogModel}" var="dialog" >
       
	
			<a href="/mail/dialog/${dialog.id}"  class="dialog"> ${dialog.APerson.firstName} ${dialog.APerson.lastName} - - -  ${dialog.BPerson.firstName} ${dialog.BPerson.lastName}   </a> 
			
	 
	</c:forEach>
       </div>
      
     
               
               </div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 