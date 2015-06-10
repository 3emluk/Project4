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

<a href="/administrator/userManagement/?action=create"><fmt:message key="user.actions.create" bundle="${lang}"/></a>

<table border="1">
    <tr>
        <th><fmt:message key="user.field.role" bundle="${lang}"/></th>
        <th><fmt:message key="user.field.email" bundle="${lang}"/></th>
        <th><fmt:message key="user.field.firstName" bundle="${lang}"/></th>
        <th><fmt:message key="user.field.lastName" bundle="${lang}"/></th>
        <th><fmt:message key="user.field.passportID" bundle="${lang}"/></th>
        <th><fmt:message key="user.field.location" bundle="${lang}"/></th>
        <th><fmt:message key="user.field.phone" bundle="${lang}"/></th>
        <th><fmt:message key="managment.field.operations" bundle="${lang}"/></th>
    </tr>
    <c:forEach items="${requestScope.users}" var="usr">
        <tr>

            <%
                String msg = "";
                User user = (User) pageContext.getAttribute("usr");
                if (user.getIdRole() == Role.ADMINISTRATOR) {
                    msg = "Administrator";
                }
                if (user.getIdRole() == Role.CUSTOMER) {
                    msg = "Customer";
                }
            %>
            <td><%= msg %>
            </td>
            <td><c:out value="${usr.email}"/></td>
            <td><c:out value="${usr.firstName}"/></td>
            <td><c:out value="${usr.lastName}"/></td>
            <td><c:out value="${usr.passportID}"/></td>
            <td><c:out value="${usr.location}"/></td>
            <td><c:out value="${usr.phone}"/></td>
            <td>
                <a href="/administrator/userManagement/?action=delete&id=${usr.id}"><fmt:message key="user.actions.remove" bundle="${lang}"/></a>
                <br>
                <a href="/administrator/userManagement/?action=modify&id=${usr.id}"><fmt:message key="user.actions.change" bundle="${lang}"/></a>
            </td>

        </tr>
    </c:forEach>
</table>

<jsp:include page="footer.jsp"/>

</body>
</html>
