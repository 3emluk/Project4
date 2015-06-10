<%@ page import="com.epam.labs.POJO.User" %>
<%@ page import="com.epam.labs.dao.UserDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="pageLanguage" var="lang"/>

<br>

<c:choose>
    <c:when test="${sessionScope.user != null}">
        <%
            UserDAO userDAO = new UserDAO();
            int idRole = userDAO.getUserRoleByID((int) (session.getAttribute("user")));
        %>
        <c:set var="role" value="<%= idRole %>"/>
        <c:choose>
            <c:when test="${role == 1}">
                <c:set var="action" value="/administrator/userManagement/"/>
            </c:when>

            <c:otherwise>
                <c:set var="action" value="/"/>
            </c:otherwise>
        </c:choose>

    </c:when>
    <c:otherwise>
        <c:set var="action" value="/registerConcrete"/>
    </c:otherwise>
</c:choose>

<form method="POST" action="${action}">
    <table border="0">
        <tr>
            <td><fmt:message key="user.field.email" bundle="${lang}"/>:</td>
            <td><input type="email" name="email" id="email" required
                       value="${!empty requestScope.user ?  user.email : ''}"/></td>
        </tr>

        <tr>
            <td><fmt:message key="user.field.firstName" bundle="${lang}"/>:</td>
            <td><input type="text" name="firstName" id="firstName" required
                       value="${!empty requestScope.user ?  user.firstName : ''}"/></td>
        </tr>

        <tr>
            <td><fmt:message key="user.field.lastName" bundle="${lang}"/>:</td>
            <td><input type="text" name="lastName" id="lastName" required
                       value="${!empty requestScope.user ?  user.lastName : ''}"/></td>
        </tr>
        <tr>
            <td><fmt:message key="user.field.passportID" bundle="${lang}"/>:</td>
            <td><input type="text" name="passportID" id="passportID" required
                       value="${!empty requestScope.user ?  user.passportID : ''}"/></td>
        </tr>
        <tr>
            <td><fmt:message key="user.field.location" bundle="${lang}"/>:</td>
            <td><input type="text" name="location" id="location" required
                       value="${!empty requestScope.user ?  user.location : ''}"/></td>
        </tr>

        <tr>
            <td><fmt:message key="user.field.phone" bundle="${lang}"/>:</td>
            <td><input type="tel" name="phone" id="phone" required
                       value="${!empty requestScope.user ?  user.phone : ''}"/></td>
        </tr>
        <c:if test="${role == 1}">
            <tr>
                <td><fmt:message key="user.field.role" bundle="${lang}"/></td>
                <%
                    String msg = "";
                    User usr = (User) request.getAttribute("user");
                    if (usr != null) {
                        if (usr.getIdRole().getRoleId() == 1) {
                            msg = "Administrator";
                        }
                        if (usr.getIdRole().getRoleId() == 2) {
                            msg = "Customer";
                        }
                    }

                %>
                <c:set var="role" value="<%= msg %>"/>
                <td>
                    <select name='role' required>
                        <option  ${role == 'Administrator' ? 'selected' : ''} value="1">Administrator</option>
                        <option  ${role == 'Customer' ? 'selected' : ''} value="2">Customer</option>
                    </select>
                </td>
            </tr>
        </c:if>
        <tr>
            <td><fmt:message key="user.field.password" bundle="${lang}"/>:</td>
            <td><input type="password" name="password" id="password" required/></td>
        </tr>

        <c:out value="${role}"/>
        <%--<c:if test="${role ==1}">--%>
            <tr>
                <td>
                    <select hidden="true" name='action'>
                        <option ${requestScope.action == '' ? 'selected' : ''} value=""></option>
                        <option ${requestScope.action == 'modify' ? 'selected' : ''} value="modifyConcrete"></option>
                        <option ${requestScope.action == 'create' ? 'selected' : ''} value="createConcrete"></option>
                    </select>
                </td>

            </tr>
            <tr>
                <td><input type="hidden" name="id" id="id" value="${!empty requestScope.user ?  user.id:-1}"/>
            </tr>
        <%--</c:if>--%>
        <tr>
            <td>
                <button type="submit" value="p" name="p" id="p" class="btn btn-primary"><fmt:message
                        key="managment.button.confirm" bundle="${lang}"/></button>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="footer.jsp"/>

</body>
</html>
