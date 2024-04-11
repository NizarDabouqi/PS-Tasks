<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddNewParkingSpot</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="main-container">
    <%@ include file="sideNavBar.jsp" %>

    <div class="sub-container">
        <h2 class="title">Add New Parking Spot</h2>

        <form id="newParkingLot" method="POST">
            <label class="label">Enter new parking ID:</label>
            <label class="label">
                <input type="text" id="parkingLotId" name="parkingLotId" class="user-input">
            </label><br>

            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div><br>
            </c:if>

            <input type="submit" value="Add" onclick="addNewParkingLot()" class="submit-button">
        </form>
    </div>
</div>
</body>
<script>
    function addNewParkingLot() {
        document.getElementById("newParkingLot").action = "${pageContext.request.contextPath}/ParkingLot";
        document.getElementById("newParkingLot").submit();
    }
</script>
</html>