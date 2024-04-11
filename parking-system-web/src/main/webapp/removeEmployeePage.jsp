<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RemoveEmployee</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="main-container">
    <%@ include file="sideNavBar.jsp" %>

    <div class="sub-container">
        <h2 class="title">Remove Employee:</h2>

        <form id="removeEmployee" method="POST">
            <label class="label">Enter employee ID:</label>
            <label class="label">
                <input type="text" name="employeeId" class="user-input">
            </label>

            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
                <br>
            </c:if>

            <input type="submit" value="Remove" onclick="removeEmployee()" class="submit-button">
        </form>
    </div>
</div>
</body>
<script>
    function removeEmployee() {
        document.getElementById("removeEmployee").action = "${pageContext.request.contextPath}/RemoveEmployee";
        document.getElementById("removeEmployee").submit();
    }
</script>
</html>
