<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="pageLanguage" var="lang"/>

<br>
<br>

<form method="POST" action="/administrator/carManagement/">
    <table border="0">
        <tr>
            <td><fmt:message key="car.field.model" bundle="${lang}"/>:</td>
            <td><input type="text" name="model" id="model" required  value="${!empty requestScope.car ?  car.model : ''}"/></td>
        </tr>

        <tr>
            <td><fmt:message key="car.field.manufacuter" bundle="${lang}"/>:</td>
            <td><input type="text" name="manufacturer" id="manufacturer" required
                       value="${!empty requestScope.car ?  car.manufacturer : ''}"/></td>
        </tr>

        <tr>
            <td><fmt:message key="car.field.sign" bundle="${lang}"/>:</td>
            <td><input type="text" name="sign" id="sign"
                       value="${!empty requestScope.car ?  car.sign : ''}"/></td>
        </tr>

        <tr>
            <td><fmt:message key="car.field.price" bundle="${lang}"/>:</td>
            <td><input type="number" min="0" step="1" name="price" id="price" required
                       value="${!empty requestScope.car ?  car.price : ''}"/></td>
        </tr>

        <tr>
            <td><fmt:message key="car.field.isAvalible" bundle="${lang}"/>:</td>
            <td>
                <select name='isAvaliable'  required >
                    <option value="true" ${car.isAvaliable() == 'true' ? 'selected' : ''} >True</option>
                    <option value="false" ${car.isAvaliable() == 'false' ? 'selected' : ''}>False</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <select hidden="true" name='action'>
                    <option ${requestScope.action == 'modify' ? 'selected' : ''} value="modifyConcrete"></option>
                    <option ${requestScope.action == 'create' ? 'selected' : ''} value="createConcrete"></option>
                </select>
            </td>

        </tr>
        <tr>
            <td><input type="hidden" name="id" id="id" value="${!empty requestScope.car ?  car.id:-1}"/>
        </tr>
        <tr>
            <td>
                <button type="submit" value="p" name="p" id="p" class="btn btn-primary"><fmt:message key="car.action.create" bundle="${lang}"/></button>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="footer.jsp"/>

</body>
</html>
