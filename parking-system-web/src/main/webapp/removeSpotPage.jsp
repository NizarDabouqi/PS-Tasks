<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RemoveSpot</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="main-container">
    <%@ include file="sideNavBar.jsp" %>

    <div class="sub-container">
        <h2 class="title">Remove Parking Spot:</h2>

        <form id="removeParkingSpot" method="POST">
            <label class="label">Enter parking ID:</label>
            <label class="label">
                <input type="text" name="parkingLotId" class="user-input">
            </label>

            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
                <br>
            </c:if>

            <input type="submit" value="Remove" onclick="removeParkingSpot()" class="submit-button">
        </form>
    </div>
</div>
</body>
<script>
    function removeParkingSpot() {
        document.getElementById("removeParkingSpot").action = "${pageContext.request.contextPath}/RemoveParkingSpot";
        document.getElementById("removeParkingSpot").submit();
    }
</script>
</html>
