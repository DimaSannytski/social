 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
       <div class="floatleft">
           <span class="comentpost">  <a href="/friend/">Мої друзі</a>
             </span><span class="messagedialog" ><a href="/friend/outinvite">Вихідні заявки</a></span><br>
     </div>
             
       <c:if test="${friendsList.size()==0}"> <div class="text-1em">  У вас немає вхідних заявок</div></c:if>
       <c:forEach items="${friendsList}" var="friend" >
		
			<br><a href="/user/${friend.bfriend.id}"> ${friend.bfriend.firstName} ${friend.bfriend.lastName} </a> 
			
			<form:form method="POST"   action="/friend/invite/${friend.id}" >

            <button  >Прийняти</button>
            
            
            </form:form> <form:form method="POST"   action="/friend/delete/${friend.id}" >
			
            
            <button  >Ігнорувати</button>
            </form:form><br>
			
	  
	</c:forEach>
       
       </div>
      
     
               
               </div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 