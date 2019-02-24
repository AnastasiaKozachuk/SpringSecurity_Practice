<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Home</title>
</head>
<body>


<p>
    User: <security:authentication property="principal.username"/>
</p>

<p>
    Role: <security:authentication property="principal.authorities"/>
</p>

<h1>Hello at home!</h1>

<form:form action="${pageContext.request.contextPath}/logout"
           method="POST">
    <input type="submit" value="Logout">
</form:form>


<hr>

<security:authorize access="hasRole('MANAGER')">

    <p>
        <a href="${pageContext.request.contextPath}/leaders"> LeaderShip meeting</a>
        (Only for Managers)
    </p>

</security:authorize>

<security:authorize access="hasRole('ADMIN')">

<p>
    <a href="${pageContext.request.contextPath}/systems"> LeaderShip meeting</a>
    (Only for Admin)
</p>
</security:authorize>

<hr>

</body>
</html>
