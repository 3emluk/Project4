<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="pageLanguage" var="lang"/>


<jsp:include page="header.jsp"/>
<form action=/logInConcrete method=POST>
    <table border=0>
        <tr>
            <td colspan=2>
                <p align=center>
                    <fmt:message key="login.label.welcome" bundle="${lang}"/>
            </td>
        </tr>

        <tr>
            <td>
                <p align=right><b><fmt:message key="user.field.email" bundle="${lang}"/>:</b>
            </td>
            <td>
                <p><input type=text name="email" value="" size=15>
            </td>
        </tr>

        <tr>
            <td>
                <p align=right><b><fmt:message key="user.field.password" bundle="${lang}"/>:</b>
            </td>
            <td>
                <p><input type=password name="passwd" value="" size=15>
            </td>
        </tr>

        <tr>
            <td colspan=2>
                <div style="text-align: center;">
                    <input type=submit value="<fmt:message key="login.button.login" bundle="${lang}"/>">
                </div>
            </td>
        </tr>
    </table>
</form>

<c:if test="${!empty requestScope.loginError}">
    <div style="color:red">
        <c:out value="${loginError}"/>
    </div>
</c:if>


<jsp:include page="footer.jsp"/>

</body>
</html>