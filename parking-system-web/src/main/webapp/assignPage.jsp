<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AssignPage</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="main-container">
    <%@ include file="sideNavBar.jsp" %>

    <div class="sub-container">
        <h2 class="title">Assign parking spot to employee:</h2>

        <form id="assignSpotToEmployee" method="POST">
            <label class="label">Enter parking ID:</label>
            <label class="label">
                <input type="text" name="parkingLotId" class="user-input">
            </label><br><br>

            <label class="label">Enter employee ID:</label>
            <label class="label">
                <input type="text" name="employeeId" class="user-input">
            </label><br>

            <label class="label">Enter expiry date (MM/DD/YYYY):</label>
            <label class="label">
                <input type="date" name="expiryDate">
            </label><br>

            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
                <br>
            </c:if>

            <input type="submit" value="Assign" onclick="assignSpotToEmployee()" class="submit-button">
        </form>
    </div>
</div>
</body>
<script>
    function assignSpotToEmployee() {
        document.getElementById("assignSpotToEmployee").action = "${pageContext.request.contextPath}/AssignSpotToEmployee";
        document.getElementById("assignSpotToEmployee").submit();
    }
</script>
</html>
