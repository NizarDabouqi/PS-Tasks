<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ParkingLotInfo</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="main-container">
    <%@ include file="sideNavBar.jsp" %>

    <div class="sub-container">
        <h2 class="title">Parking Spot Information:</h2>

        <form id="ParkingLot" method="POST">
            <label class="label">Enter Parking ID:</label>
            <label class="label">
                <input type="text" name="parkingId" class="user-input">
            </label>

            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
                <br>
            </c:if>

            <input type="submit" value="Print" onclick="showParkingLotInfo()" class="submit-button">
        </form>
        <table>
            <tr>
                <td><h4>Parking ID:</h4></td>
                <td><h4>${parkingLotId}</h4></td>
            </tr>
            <tr>
                <td><h4>Available:</h4></td>
                <td><h4>${isAvailable}</h4></td>
            </tr>
            <tr>
                <td><h4>Employee ID that assigned to this parking lot:</h4></td>
                <td><h4>${employeeId}</h4></td>
            </tr>
            <tr>
                <td><h4>Employee name that assigned to this parking lot:</h4></td>
                <td><h4>${employeeName}</h4></td>
            </tr>
        </table>
    </div>
</div>
</body>
<script>
    function showParkingLotInfo() {
        document.getElementById("ParkingLot").action = "${pageContext.request.contextPath}/ParkingLotInfo";
        document.getElementById("ParkingLot").submit();
    }
</script>
</html>
