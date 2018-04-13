 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
       
                 
                  <form:form method="POST" class="wallform"  action="/mail/dialog/${dialogId}/send" modelAttribute="messageModel">
			
             <form:textarea placeholder="Повідомлення" path="message" /><br>
            <button  >Відправити</button>
            </form:form><br> <br>
  
       <c:forEach items="${messagesModel}" var="myMessage" ><br>
		  <c:if test="${myMessage.APerson.id==personId}">
		<div class="maill"><span class="mailname"><a href="/user/${myMessage.APerson.id}"> ${myMessage.APerson.firstName}  </a>  </span> <span class="mailmessage">  ${myMessage.message }
		<br><span class="smsize">${myMessage.createdAt} </span>
		 </span></div>  </c:if>
		  <c:if test="${myMessage.APerson.id!=personId}"> 
		  <div  style=" width: 900px; text-align: right; margin: 1px;"><span class="mailmessagel"> ${myMessage.message }<br><span class="smsize">${myMessage.createdAt}</span> </span><span class="mailnamel"><a href="/user/${myMessage.APerson.id}"> ${myMessage.APerson.firstName} </a></span></div>
		
		 </c:if>
		 
	</c:forEach>
       
       </div>
      
     
               
               </div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 