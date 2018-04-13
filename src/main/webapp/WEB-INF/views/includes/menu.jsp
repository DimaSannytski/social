<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ include file="/WEB-INF/views/taglib.jsp" %>
   <div class="menu">
            <a href="/user">Моя сторінка</a>
            <a href="/mail">Мої повідомлення</a>
            <a href="/friend">Мої Друзі</a>
            <a href="/photo">Мої фотографії</a>
            <a href="/group">Мої спільноти</a>
            <a href="/user/edit/">Налаштування</a>
             <a href="/user/all">Усі користувачі</a>
             <sec:authorize access="hasRole('ROLE_ADMIN')">
     <a href="/admin">Admin page</a></sec:authorize>
        </div>