<%@ page import="com.epam.labs.dao.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="pageLanguage" var="lang"/>


<%--<%@taglib prefix="custom" uri="/WEB-INF/ConcatDescriptor.tld" %>--%>


<?xml version="1.0" encoding="UTF-8"?>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">--%>

<!DOCTYPE html>
<html lang="${language}">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fi">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <title>Project4</title>
</head>
<body>

<h1>Car order system</h1>

<ul id="menu">
    <form method="post">
        <select id="language" name="language" onchange="submit()">
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
        </select>
    </form>
    <c:choose>
        <c:when test="${sessionScope.user != null}">
            <li><a href="/logOut" id="logout"><fmt:message key="header.link.logOut" bundle="${lang}"/></a></li>
            <%
                UserDAO userDAO = new UserDAO();
                int idRole = userDAO.getUserRoleByID((int) (session.getAttribute("user")));
            %>
            <c:set var="role" value="<%= idRole %>"/>

            <c:choose>
                <c:when test="${role == 1}">
                    <li><a href='/administrator/orderManagement/'><fmt:message key="header.link.orderManagment"
                                                                               bundle="${lang}"/></a></li>
                    <li><a href='/administrator/userManagement/'><fmt:message key="header.link.userManagment"
                                                                              bundle="${lang}"/></a></li>
                    <li><a href='/administrator/carManagement/'><fmt:message key="header.link.carManagment"
                                                                             bundle="${lang}"/></a></li>
                </c:when>

                <c:when test="${role == 2}">
                    <li><a href='/user/customerOrders/'><fmt:message key="header.link.customerOrders"
                                                                     bundle="${lang}"/></a></li>
                </c:when>

                <c:otherwise>
                    Something is going wrong
                </c:otherwise>
            </c:choose>

        </c:when>

        <c:otherwise>
            <li><a href="/logIn" id="login"><fmt:message key="header.link.logIn" bundle="${lang}"/></a></li>
            <li><a href="/register"><fmt:message key="header.link.register" bundle="${lang}"/></a></li>
        </c:otherwise>
    </c:choose>

    <li><a href="/"><fmt:message key="header.link.main" bundle="${lang}"/></a></li>

    <%--<custom:substring originalLine="You  " concatLine="logged in!"/>--%>

</ul>
