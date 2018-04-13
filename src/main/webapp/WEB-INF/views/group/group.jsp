 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
      <c:if test="${photoId>0}">  <a href="/photo/${photoId}">  <img  alt="Profile" src="data:image/png; base64, ${imageSrc}" style="height:300px;margin-left:40px; "> </a></c:if>
      <c:if test="${photoId==null}">   <img  alt="Profile" src="data:image/png; base64, ${imageSrc}" style="height:300px;margin-left:40px; margin-top: 20px;">  </c:if>
             <div class="artcont" style="float:right; margin:10px;padding:10px;">
                  <div class="info">
                 
                 <div class="nameuser">
                 ${spilnotyModel.groupName}</div><br>
               
                 Про спіьноту: ${spilnotyModel.about}<br>
                 
                 Категорія: ${spilnotyModel.category.category}<br>
                 
                <%--  <sec:authorize access="hasRole(ROLE_ADMIN)">
     <a href="/user/edit/${userProfile.id}" class="loginBtn">Edit user profile</a></sec:authorize> --%>
                  </div>
             <c:if test="${buttonStatus=='SHOW'}">     <form:form method="POST" action="/group/${groupId}/join" >
            <button   class="send">Приєднатися</button>
            </form:form></c:if>
             <c:if test="${buttonStatus=='HIDE'}">     <form:form method="POST" action="/group/${groupId}/out" >
            <button   class="send">Вийти з групи</button>
            </form:form></c:if>
              <c:if test="${showButton}">
           <div class="friend"> <a href="${spilnotyModel.id}/settings">Редагувати </a></div> </c:if>   
            <br></div>
            <br><br>
            <h1>Учасники (${spilnotyModel.persons.size()})</h1>
                  <c:forEach items="${spilnotyModel.persons}" var="person" >
		 
			 <br><a href="/user/${person.id}"> ${person.firstName} ${person.lastName}</a><br>
			</c:forEach> <br> <br><br><div class="wall">
                  <h1>Стіна новин</h1>
                  <form:form method="POST" class="wallform"  action="/group/${groupId}/send" modelAttribute="wallModel">
			
             <form:textarea placeholder="Повідомлення" path="message" /><br>
            <button  >Відправити</button>
            </form:form>
                
     
       <c:forEach items="${wallPosts}" var="wall" >
		<div class="wallpost">
		<div class="postuser">
			<a href="${wall.fromPerson.id}">  <img  alt="Profile" src="data:image/png; base64, ${wall.fromPerson.photo}" style="height:60px; "><span class="left-top">
			${wall.fromPerson.firstName} ${wall.fromPerson.lastName}</span></a>  </div> <div class="message"> ${wall.message } </div>
			<div class="coment">
		 <form:form method="POST" class="wallform"  action="/group/${groupId}/${wall.id}/send" modelAttribute="wallComentModel">
			
             <form:textarea placeholder="Коментарій" path="post" /><br>
            <button  >Відправити</button>
            </form:form></div>
            <c:if test="${wall.wallComents.size()!=0}"> <div class="text-12em"> Коментарі:</div></c:if>
         
             <div class="comentd">  <c:forEach items="${wall.wallComents}" var="wallComents" >
		 
			 <br> <span class="comentpost"><a href="${wallComents.person.id}"> ${wallComents.person.firstName} ${wallComents.person.lastName}</a></span><span class="messagecoment" >  ${wallComents.postComent } <br>
		</span> <br>	</c:forEach>  </div>   </div> 
	</c:forEach>
      </div>
       </div>
      
     
               
               </div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 