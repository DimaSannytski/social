 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
        
             <div class="artcont" >
                 <div class="border">  
            <form:form method="POST" class="wallform" action="/admin/${userId}/edit/" modelAttribute="editModel"
						>
			<form:input type="text" placeholder="Введіть email"  path="email"  /> <br>
            <form:input type="text" placeholder="Имя" path="firstName"  /><br>
            <form:input type="text" placeholder="Фамилия" path="lastName" /><br>
             <form:input type="text" placeholder="Age" path="age" />Вік<br>
          
           
             <form:input  placeholder="Phone" path="phoneNumber"  />Номер телефону<br>
             <form:textarea placeholder="Про себе" path="about"  /><br>Країна
              <form:select path="country" >
								<c:forEach items="${countrys}" var="country">
									<form:option value="${country}">${country}</form:option>
								</c:forEach>
							</form:select><br>Стать:
			<form:select path="male" >
								<c:forEach items="${males}" var="male">
									<form:option value="${male}">${male.male}</form:option>
								</c:forEach>
							</form:select>	<br><br> 		
            <button  >Зберегти зміни</button>
            </form:form></div><br>
                   <div class="border">  
            <form:form method="POST" class="wallform" action="/admin/${userId}/edit/photo" modelAttribute="editPhotoModel"
						enctype="multipart/form-data">
             <form:input path="file" type="file"  title="Profile Image" /><br>
            <button  >Змінити фото</button>
            </form:form>
                </div>
                 </div>
       </div>
      
     
               
               </div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 