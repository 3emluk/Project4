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
        <c:choose>
            <c:when test="${sessionScope.role == 'ADMINISTRATOR'}">
                <c:set var="action" value="/administrator/orderManagement/"/>
            </c:when>

            <c:when test="${sessionScope.role == 'CUSTOMER'}">
                <c:set var="action" value="/user/customerOrders/"/>
            </c:when>
        </c:choose>

        <form method="POST" action="${action}">
            <table border="0">
                <tr>
                    <td>
                        <select id="idCar" name="idCar" required>

                            <c:choose>
                                <c:when test="${!empty requestScope.car}">
                                    <option value="${car.id}">${car.manufacturer} ${car.model} ${car.price}</option>
                                </c:when>

                                <c:when test="${empty requestScope.car}">
                                    <c:forEach items='${requestScope.cars}' var='car'>
                                        <option value="${car.id}">${car.manufacturer} ${car.model} ${car.price}</option>
                                    </c:forEach>
                                </c:when>

                            </c:choose>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="order.field.startDate" bundle="${lang}"/></td>
                    <td><input type="date" name="startDate" id="startDate" required
                               value="${!empty requestScope.order ?  order.startDate : ''}"/></td>
                </tr>

                <tr>
                    <td><fmt:message key="order.field.duration" bundle="${lang}"/>:</td>
                    <td><input type="number" min="1" max="365" step="1" name="duration" id="duration" required
                               value="${!empty requestScope.order ?  order.duration : ''}"/></td>
                </tr>

                <tr>
                    <td><fmt:message key="order.field.endDate" bundle="${lang}"/>:</td>
                    <td><input type="date" name="endDate" id="endDate"
                               value="${!empty requestScope.order ?  order.endDate : ''}"/></td>
                </tr>

                <c:if test="${sessionScope.role == 'ADMINISTRATOR'}">
                    <tr>
                        <td>
                            <select id="idUser" name="idUser" required>
                                <c:choose>
                                    <c:when test="${!empty requestScope.user}">
                                        <option value="${user.id}">${user.firstName} ${user.lastName} ${user.passportID}</option>
                                    </c:when>

                                    <c:when test="${empty requestScope.user}">
                                        <c:forEach items='${requestScope.users}' var='user'>
                                            <option value="${user.id}">${user.firstName} ${user.lastName} ${user.passportID}</option>
                                        </c:forEach>
                                    </c:when>

                                </c:choose>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td><fmt:message key="order.field.charges" bundle="${lang}"/>:</td>
                        <td><input type="number" min="0" step="1" name="charges" id="charges" required
                                   value="${!empty requestScope.order ?  order.charges : ''}"/></td>
                    </tr>

                </c:if>
                <tr>
                    <td><fmt:message key="order.field.comment" bundle="${lang}"/>:</td>
                    <td><input type="text" name="comment" id="comment"
                               value="${!empty requestScope.comment ?  user.comment : ''}"/></td>
                </tr>
                <tr>
                    <td>
                        <select hidden="true" name='action'>
                            <c:if test="${sessionScope.role == 'ADMINISTRATOR'}">
                                <option ${requestScope.action == 'modify' ? 'selected' : ''}
                                        value="modifyConcrete"></option>
                            </c:if>
                            <option ${requestScope.action == 'create' ? 'selected' : ''}
                                    value="createConcrete"></option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="hidden" name="id" id="id" value="${!empty requestScope.order ?  order.id:-1}"/>
                </tr>

                <tr>
                    <td>
                        <button type="submit" value="p" name="p" id="p" class="btn btn-primary"><fmt:message
                                key="managment.button.confirm" bundle="${lang}"/></button>
                    </td>
                </tr>
            </table>
        </form>
    </c:when>

</c:choose>

<jsp:include page="footer.jsp"/>

</body>
</html>
