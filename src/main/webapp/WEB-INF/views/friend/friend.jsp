 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
       <div class="floatleft">
           <span class="comentpost">  <a href="/friend/invite">Вхідні заявки: ${sizeF}</a>
             </span><span class="messagedialog" ><a href="/friend/outinvite">Вихідні заявки</a></span><br>
     </div>
     <c:if test="${friendsListA.size()==0&&friendsListB.size()==0}"> <div class="text-1em">  Список ваших друзів пустий</div> </c:if>
       <c:forEach items="${friendsListA}" var="friend" >
		
			 <div class="friendprof" >
		<div class="postuser" style="text-align: top;"><a href="/user/${friend.bfriend.id}">
		<img  alt="Profile" src="data:image/png; base64, ${friend.bfriend.photo}" style="height:90px; "><span class="left-top"> ${friend.bfriend.firstName} ${friend.bfriend.lastName} </span></a> 
		 <form:form method="POST"   action="/friend/outfriend/${friend.id}/delete" >
			
            
            <button  class="freindform" style="float: right; margin-top: -40px;">Видалити</button>
            </form:form> 
		</div> 
			</div><br>
			
	  
	</c:forEach>
	       <c:forEach items="${friendsListB}" var="friend" >
		
			 <div class="friendprof">
		<div class="postuser" style=""><a href="/user/${friend.afriend.id}"><img  alt="Profile" src="data:image/png; base64, ${friend.afriend.photo}" style="height:90px;"><span class="left-top"> ${friend.afriend.firstName} ${friend.afriend.lastName}</span> </a> 

			 <form:form method="POST"   action="/friend/outfriend/${friend.id}/delete" >
			
            
            <button  class="freindform" style="float: right; margin-top: -40px;">Видалити</button>
            </form:form> </div></div><br>
			
	  
	</c:forEach>
       
       </div>
      
     
               
               </div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 