<%@ page import="com.epam.labs.enums.Role" %>
<%@ page import="com.epam.labs.POJO.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="pageLanguage" var="lang"/>



<jsp:include page="header.jsp"/>

<br>

<a href="/administrator/carManagement/?action=create"><fmt:message key="car.action.create" bundle="${lang}"/></a>

<table border="1">
    <tr>
        <th><fmt:message key="car.field.model" bundle="${lang}"/></th>
        <th><fmt:message key="car.field.manufacuter" bundle="${lang}"/></th>
        <th><fmt:message key="car.field.sign" bundle="${lang}"/></th>
        <th><fmt:message key="car.field.price" bundle="${lang}"/></th>
        <th><fmt:message key="car.field.isAvalible" bundle="${lang}"/></th>
        <th><fmt:message key="managment.field.operations" bundle="${lang}"/></th>
    </tr>
    <c:forEach items="${requestScope.cars}" var="car">
        <tr>
            <td><c:out value="${car.model}"/></td>
            <td><c:out value="${car.manufacturer}"/></td>
            <td><c:out value="${car.sign}"/></td>
            <td><c:out value="${car.price}"/></td>
            <td><c:out value="${car.isAvaliable()}"/></td>
            <td>
                <a href="/administrator/carManagement/?action=delete&id=${car.id}"><fmt:message key="car.action.remove" bundle="${lang}"/></a>
                <br>
                <a href="/administrator/carManagement/?action=modify&id=${car.id}"><fmt:message key="car.action.change" bundle="${lang}"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp"/>

</body>
</html>
