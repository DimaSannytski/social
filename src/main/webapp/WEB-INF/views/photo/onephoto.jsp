 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
      
               
  
			<img alt="photo" src="data:image/png;base64, ${photoSrc.file}" width="300px">
			
			 <c:if test="${buttonShow}"> <form:form method="POST" action="/photo/${photoId}/like">
			 <button  style="background-color: #a9a9a9;" >Like (${photoSrc.persons.size()})</button>
             </form:form></c:if>
              <c:if test="${!buttonShow}"> <form:form method="POST" action="/photo/${photoId}/dislike">
			 <button >Like (${photoSrc.persons.size()})</button>
             </form:form></c:if>
             <c:if test="${showDelete}">
              <form:form method="POST"   action="/photo/${photoId}/delete" >
			
             
            <button  >Видалити</button></form:form></c:if>
			<c:forEach items="${photoSrc.photoComent}" var="photoComent" >
		 
			 <br> <span class="comentpost"><a href="${photoComent.person.id}"> ${photoComent.person.firstName} ${photoComent.person.lastName}</a></span><span class="messagecoment" > ${photoComent.message }</span>	 <br>
			</c:forEach>   
               
		   <form:form method="POST" class="wallform"  action="/photo/${photoId}/send" modelAttribute="comentModel">
			
             <form:textarea placeholder="Коментарій" path="message" /><br>
            <button  >Відправити</button>
            </form:form></div></div></div>
             
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 