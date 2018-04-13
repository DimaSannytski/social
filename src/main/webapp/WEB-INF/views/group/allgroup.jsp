 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
       
           <div class="floatleft">
           <span class="comentpost">   <a href="/group/my">Мої спільноти</a> 
             </span><span class="messagedialog" > <a href="/group/create/">Створити спільноту</a>  </span><br></div>
              <form:form action="/search/group" method="POST"  >
  <input name="search" placeholder="Введіть назву" type="text"/> 
   <input type="submit" value="Знайти">
   </form:form><br>
             <c:if test="${spilnotyAll.size()==0}"> <div class="text-1em">Ви не належете до жодної спільноти</div> </c:if>
			      <c:if test="${spilnotyAll.size()!=0}">    <h1>Усі спільноти</h1> <br></c:if>
           <c:forEach items="${spilnotyAll}" var="spilnoty">
	
			 <div class="friendprof">
		<div class="postuser"><a href="group/${spilnoty.id}">${spilnoty.groupName}</a></div> <div class="message">${spilnoty.about}<br><span style="color: red;"> ${spilnoty.category.category}</span></div></div>
			
		
	</c:forEach> 
  
               
               </div></div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 