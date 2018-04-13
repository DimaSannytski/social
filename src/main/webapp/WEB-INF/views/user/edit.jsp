 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
        
             <div class="artcont" >
                 <div class="border">  
            <form:form method="POST" class="wallform" action="/user/edit/save" modelAttribute="editModel"
						>
			<form:input type="text" placeholder="Введіть email"  path="email" disabled="true" /> <br>
            <form:input type="text" placeholder="Имя" path="firstName"  disabled="true"/><br>
            <form:input type="text" placeholder="Фамилия" path="lastName"  disabled="true"/><br>
             <form:input type="text" placeholder="Age" path="age" />Вік<br>
          
           
             <form:input  placeholder="Phone" path="phoneNumber"  pattern="[\+()]*(?:\d[\s\-\.()xX]*){10,14}" />Номер телефону<br>
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
							</form:select>	<br>
            <button  >Зберегти зміни</button>
            </form:form></div><br><div class="border">  
            <form:form method="POST" class="wallform" action="/user/edit/photo" modelAttribute="editPhotoModel"
						enctype="multipart/form-data">
             <form:input path="file" type="file"  title="Profile Image" /><br>
            <button  >Змінити фото</button>
            </form:form>
            
                </div><br>
                <div class="border">  
            <form:form method="POST" class="wallform" action="/changepass/send" 
						>
         Лист з посилання для зміни паролю буде відправлено вам на електронну пошту<br><br>
            <button  >Змінити пароль</button>
            </form:form>
           
                </div>
      
                 </div>
       </div>
      
     
               
               </div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 