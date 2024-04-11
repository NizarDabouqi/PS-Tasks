<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RemoveSpotFromEmployee</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="main-container">
    <%@ include file="sideNavBar.jsp" %>

    <div class="sub-container">
        <h2 class="title">Remove parking spot from employee:</h2>

        <form id="removeSpotFromEmployee" method="POST">

            <label class="label">Enter parking ID to remove:</label>
            <label class="label">
                <input type="text" name="parkingLotId" class="user-input">
            </label>

            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
                <br>
            </c:if>

            <input type="submit" value="Remove" onclick="removeSpotFromEmployee()" class="submit-button">
        </form>
    </div>
</div>
</body>
<script>
    function removeSpotFromEmployee() {
        document.getElementById("removeSpotFromEmployee").action = "${pageContext.request.contextPath}/RemoveSpotFromEmployee";
        document.getElementById("removeSpotFromEmployee").submit();
    }
</script>
</html>