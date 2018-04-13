 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
       
         <c:if test="${avaId>0}">  <a href="/photo/${avaId}">  <img  alt="Profile" src="data:image/png; base64, ${imageSrc}" style="height:300px; margin-left:40px; "> </a></c:if>
      <c:if test="${avaId==0}">   <img  alt="Profile" src="data:image/png; base64, ${imageSrc}" style="height:300px;margin-left:40px; ">  </c:if>
        
             <div class="info">
                 
                 <div class="nameuser"> ${userProfile.firstName} ${userProfile.lastName}</div><br>
                Поштова скринька: ${userProfile.email }<br>
                Про себе: ${userProfile.about}<br>
                 Вік: ${userProfile.age}<br>
                  Країна: ${userProfile.country}<br>
                 Стать: ${userProfile.male.male}<br>
                <%--  <sec:authorize access="hasRole('ROLE_ADMIN')">
     <a href="/user/edit/${userProfile.id}" class="loginBtn">Edit user profile</a></sec:authorize> --%>
                  </div><div class="buttons">
                  <form:form method="POST"   action="/user/${userProfile.id}/dialog" modelAttribute="dialogModel">
			
            
            <button  class="send">Надіслати повідомлення</button>
            </form:form>
           <sec:authorize access="hasRole('ROLE_ADMIN')">
            <form:form method="POST"   action="/admin/${userProfile.id}/delete">
            <button  class="send">Видалити</button>
            </form:form> 
            <c:if test="${buttonBanStatus=='HIDE'}"><div class="friend">is Banned</div> </c:if> 
             <c:if test="${buttonBanStatus=='SHOW'}">
             <form:form method="POST"   action="/admin/${userProfile.id}/bann">
            <button  class="send">Забанити</button>
            </form:form> </c:if>   
            <c:if test="${buttonBanStatus=='HIDE'}">
             <form:form method="POST"   action="/admin/${userProfile.id}/outbann">
            <button  class="send">Розабанити</button>
            </form:form> </c:if>                   </sec:authorize>
            <c:if test="${buttonStatus=='FRIEND'}"><div class="friend">Ваш друг</div> </c:if>
            <c:if test="${buttonStatus=='HIDE'}"></c:if>
            <c:if test="${buttonStatus=='SHOW'}"><form:form method="POST"   action="/user/${userProfile.id}/friend" modelAttribute="friendModel">
            <button  class="send">Долучити до друзів</button>
            </form:form></c:if>
            <c:if test="${buttonStatus=='CONFIRM'}"><form:form method="POST"   action="/friend/invite/${friendId}/from" modelAttribute="friendModel">
            <button  class="send">Прийняти запит</button>
            </form:form></c:if>
            <c:if test="${buttonStatus=='SEND'}"><form:form method="POST"   action="/friend/outfriend/${friendId}/delete/from" modelAttribute="friendModel">
            <button  class="send">Відмінити запит</button>
            </form:form></c:if>
                 </div>
                  <br> <br><h1>Друзі(${friendA.size()+friendB.size()})</h1><br>
           <c:forEach items="${friendB}" var="friend" >
           <a href="/user/${friend.afriend.id}">${friend.afriend.firstName} ${friend.afriend.lastName}</a><br>
            </c:forEach>
             <c:forEach items="${friendA}" var="friend" >
           <a href="/user/${friend.bfriend.id}">${friend.bfriend.firstName} ${friend.bfriend.lastName}</a><br>
            </c:forEach> 
                 <br><br><div class="wall">
                 <h1>Стіна користувача</h1>
                  <form:form method="POST" class="wallform"  action="/user/${userProfile.id}/sendWall" modelAttribute="wallModel">
			
             <form:textarea placeholder="Запис" path="message" /><br>
            <button  >Відправити</button>
            </form:form>
     
       <c:forEach items="${wallPosts}" var="wall" >
      <div class="wallpost">
		<div class="postuser">
			<a href="${wall.fromPerson.id}"> <img  alt="Profile" src="data:image/png; base64, ${wall.fromPerson.photo}" style="height:60px; "><span class="left-top">
			${wall.fromPerson.firstName} ${wall.fromPerson.lastName}</span></a>
			</div> <div class="message">${wall.message }  <c:if test="${buttonShow}"> <form:form method="POST"   action="/user/${wall.id}/deletewall" >
			
             <input type="submit" class="delete" value="Видалити">
            </form:form></c:if></div> 
			<div class="coment">
		 <form:form method="POST" class="wallform"  action="/user/${userId}/wall/${wall.id}/comment" modelAttribute="commentModel">
			
             <form:textarea placeholder="Коментарій" path="post" pattern="{1,}"/><br>
            <button  >Відправити</button>
            </form:form></div>
           
        <c:if test="${wall.wallComents.size()!=0}">   <div class="text-12em"> Коментарі:</div></c:if>
             <div class="comentd">  <c:forEach items="${wall.wallComents}" var="wallComents" >
		<br> <span class="comentpost">
			 <a href="${wallComents.person.id}"> ${wallComents.person.firstName} ${wallComents.person.lastName}</a> </span><span class="messagecoment" > ${wallComents.postComent }
			</span> <br> </c:forEach>  </div> </div>
	</c:forEach>
       </div>
      
     
               
               </div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 