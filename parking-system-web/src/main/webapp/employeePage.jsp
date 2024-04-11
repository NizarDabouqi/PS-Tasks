<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Employee</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="main-container">
    <%@ include file="sideNavBar.jsp" %>

    <div class="sub-container">
        <h2 class="title">Add New Employee</h2>

        <form id="newEmployee" method="POST">
            <label class="label">Enter new employee ID:</label>
            <label class="label">
                <input type="text" id="employeeId" name="employeeId" class="user-input">
            </label><br><br>


            <label class="label">Enter new employee name:</label>
            <label class="label">
                <input type="text" id="employeeName" name="employeeName" class="user-input">
            </label><br>

            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
                <br>
            </c:if>

            <input type="submit" value="Add" onclick="addNewEmployee()" class="submit-button">
        </form>
    </div>
</div>
</body>
<script>
    function addNewEmployee() {
        document.getElementById("newEmployee").action = "${pageContext.request.contextPath}/Employee";
        document.getElementById("newEmployee").submit();
    }
</script>
</html>
