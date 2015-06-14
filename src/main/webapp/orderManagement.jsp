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
<c:choose>
    <c:when test="${sessionScope.user != null}">
        <c:if test="${sessionScope.role == 'ADMINISTRATOR'}">
            <a href="/administrator/orderManagement/?action=create"><fmt:message key="order.action.create"
                                                                                 bundle="${lang}"/></a>
        </c:if>
        <c:if test="${sessionScope.role == 'CUSTOMER'}">
            <a href="/user/customerOrders/?action=create"><fmt:message key="order.action.create" bundle="${lang}"/></a>
        </c:if>
        <table border="1">
                <%--<c>--%>
            <c:if test="${sessionScope.role == 'ADMINISTRATOR'}">
                <th><fmt:message key="order.field.id" bundle="${lang}"/></th>
            </c:if>
                <%--${role==1 ? '<th>id</th>' :''}--%>
            <th><fmt:message key="order.field.orderNumber" bundle="${lang}"/></th>
            <th><fmt:message key="car.field.manufacuter" bundle="${lang}"/></th>
            <th><fmt:message key="car.field.model" bundle="${lang}"/></th>
            <th><fmt:message key="car.field.sign" bundle="${lang}"/></th>
            <th><fmt:message key="car.field.price" bundle="${lang}"/></th>
            <th><fmt:message key="order.field.startDate" bundle="${lang}"/></th>
            <th><fmt:message key="order.field.duration" bundle="${lang}"/></th>
            <th><fmt:message key="order.field.endDate" bundle="${lang}"/></th>

            <c:if test="${sessionScope.role == 'ADMINISTRATOR'}">
                <th><fmt:message key="user.field.firstName" bundle="${lang}"/></th>
                <th><fmt:message key="user.field.lastName" bundle="${lang}"/></th>
                <th><fmt:message key="user.field.passportID" bundle="${lang}"/></th>
                <th><fmt:message key="user.field.location" bundle="${lang}"/></th>
                <th><fmt:message key="user.field.email" bundle="${lang}"/></th>
                <th><fmt:message key="user.field.phone" bundle="${lang}"/></th>
            </c:if>

            <th><fmt:message key="order.field.rentPrice" bundle="${lang}"/></th>
            <th><fmt:message key="order.field.charges" bundle="${lang}"/></th>
            <th><fmt:message key="order.field.fullPrice" bundle="${lang}"/></th>
            <th><fmt:message key="order.field.isConfirmed" bundle="${lang}"/></th>
            <th><fmt:message key="order.field.comment" bundle="${lang}"/></th>
            <c:if test="${sessionScope.role == 'ADMINISTRATOR'}">
                <th><fmt:message key="managment.field.operations" bundle="${lang}"/></th>
            </c:if>
                <%--${role==1 ? '<th>Action</th>' :''}--%>
            </tr>
            <c:forEach items="${requestScope.orders}" var="order">
                <tr>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'ADMINISTRATOR'}">
                            <td><c:out value="${order.id}"/></td>
                            <td><c:out value="${order.carOrderNumber}"/></td>
                            <td><c:out value="${order.carManufacturer}"/></td>
                            <td><c:out value="${order.carModel}"/></td>
                            <td><c:out value="${order.carSign}"/></td>
                            <td><c:out value="${order.carPrice}"/></td>
                            <td><c:out value="${order.startDate}"/></td>
                            <td><c:out value="${order.duration}"/></td>
                            <td><c:out value="${order.endDate}"/></td>
                            <td><c:out value="${order.userFirstName}"/></td>
                            <td><c:out value="${order.userLastName}"/></td>
                            <td><c:out value="${order.userPassportID}"/></td>
                            <td><c:out value="${order.userLocation}"/></td>
                            <td><c:out value="${order.userEmail}"/></td>
                            <td><c:out value="${order.userPhone}"/></td>
                            <td><c:out value="${order.rentPrice}"/></td>
                            <td><c:out value="${order.charges}"/></td>
                            <td><c:out value="${order.fullPrice}"/></td>
                            <td><c:out value="${order.isConfirmed()}"/></td>
                            <td><c:out value="${order.comment}"/></td>
                            <td>
                                <a href="/administrator/orderManagement/?action=delete&id=${order.id}"><fmt:message
                                        key="order.action.remove" bundle="${lang}"/></a>
                                <br>
                                <a href="/administrator/orderManagement/?action=modify&id=${order.id}"><fmt:message
                                        key="order.action.change" bundle="${lang}"/></a>
                                <a href="/administrator/orderManagement/?action=confirm&id=${order.id}"><fmt:message
                                        key="order.action.confirm" bundle="${lang}"/>
                                    Order</a>
                            </td>
                        </c:when>

                        <c:when test="${sessionScope.role == 'CUSTOMER'}">
                            <td><c:out value="${order.carOrderNumber}"/></td>
                            <td><c:out value="${order.carManufacturer}"/></td>
                            <td><c:out value="${order.carModel}"/></td>
                            <td><c:out value="${order.carSign}"/></td>
                            <td><c:out value="${order.carPrice}"/></td>
                            <td><c:out value="${order.startDate}"/></td>
                            <td><c:out value="${order.duration}"/></td>
                            <td><c:out value="${order.endDate}"/></td>
                            <td><c:out value="${order.rentPrice}"/></td>
                            <td><c:out value="${order.charges}"/></td>
                            <td><c:out value="${order.fullPrice}"/></td>
                            <td><c:out value="${order.isConfirmed()}"/></td>
                            <td><c:out value="${order.comment}"/></td>

                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>

<jsp:include page="footer.jsp"/>

</body>
</html>
