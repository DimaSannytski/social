 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/WEB-INF/views/taglib.jsp" %>

       
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/style/style.css" />
  <link rel="icon"
              type="image/x-icon"
              href="/resources/style/favicon.ico">
<title>Page</title>
</head>
<body>
    <div class="header">
      <img src="/resources/style/img/logo.png" />
       <form:form class="search" action="/search" method="POST"  >
  <input class="search-a"name="search" placeholder="Пошук" type="text"/> 
   <input class="search-b" type="submit" value="Знайти">
   </form:form> 
     <form:form action="/logout" method="POST"> 
<input type="submit" class="logout" value="Logout">

</form:form>
 
        
    </div>
    