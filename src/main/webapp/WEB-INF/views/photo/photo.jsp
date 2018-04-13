 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <div class="menuart">
    
    <%@include file="/WEB-INF/views/includes/menu.jsp" %>

        <div class='article'>
        <div class="artc">
       <div class="floatleft">  <span class="comentpost">  <a href="photo/create/photo">Додати світлину</a>  
          </span><span class="messagedialog" ><a href="photo/create/album"> Створити альбом</a> </span></div> <br>
         <c:if test="${photoAlbumModel.size()==0}"> <div class="text-1em">Ви не створили жнодного альбому.</div></c:if> <c:if test="${photoAlbumModel.size()!=0}"><h1> Альбоми</h1><br>
           <c:forEach items="${photoAlbumModel}" var="photoAlbum" >
		 
			 <span class="album"><a href="photo/album/${photoAlbum.id}"> ${photoAlbum.name }</a> </span>  
			</c:forEach>  </c:if>
			  <c:if test="${ShowPhotoModel.size()==0}"> <div class="text-1em"> Ви не додали жодної світлини.</div> </c:if><c:if test="${ShowPhotoModel.size()!=0}">      <h1> <br>Фотографії</h1> <br></c:if>    
           <c:forEach items="${ShowPhotoModel}" var="photo">
	
			<a href="photo/${photo.id}"><img alt="photo" src="data:image/png;base64, ${photo.file}" width="300px"></a>
			
		
	</c:forEach> 
  
               
               </div></div></div>
                
       
       

       

       

<%@include file='/WEB-INF/views/includes/footer.jsp' %>
</body>
</html> 