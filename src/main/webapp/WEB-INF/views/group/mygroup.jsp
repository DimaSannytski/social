 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
        <div class="floatleft">
           <span class="comentpost">   <a href="/group/">Усі спільноти</a> 
             </span><span class="messagedialog" > <a href="/group/create/">Створити спільноту</a>  </span><br></div>
         
         <c:if test="${groupModel.size()==0}"> <div class="text-1em">Ви ще не створили жодної спільноти</div> </c:if>
			      <c:if test="${groupModel.size()!=0}"> 
			         <h1> Мої спільноти</h1> <br></c:if>
           <c:forEach items="${groupModel}" var="spilnoty">
	
			<div class="friendprof">
		<div class="postuser"><a href="/group/${spilnoty.id}">${spilnoty.groupName} </a> </div> <div class="message">${spilnoty.about} </div></div>
			
		
	</c:forEach> 
  
               
               </div></div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 