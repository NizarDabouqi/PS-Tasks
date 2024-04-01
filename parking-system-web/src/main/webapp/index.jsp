<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" type="text/css" href="../Styles/styles.css">
    <style>
        .button-container {
            display: flex;
            flex-direction: column;
            width: 70%;
            gap: 10px;
            margin: 0 auto;
        }

        .option-button {
            background-color: #b6bac3;
            color: black;
            border: none;
            font-weight: bold;
            padding: 15px 30px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            letter-spacing: 2px;
            text-align: center;
            font-size: 18px;
        }

        .option-button:hover {
            background-color: #7a7d83;
        }

        .options-heading {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 20px;
            text-align: center;
            color: #333;
            text-transform: uppercase;
            letter-spacing: 2px;
            border-bottom: 2px solid black;
            padding-bottom: 10px;
        }
    </style>
</head>

<body>
<div class="navbar">
    <h1>ProgressSoft Parking System</h1>
</div>

<div class="container">
<form method="POST">
    <h2 class="options-heading">Choose one of the options:</h2>
    <br>

    <div class="button-container">
        <button type="button" class="option-button" value="${pageContext.request.contextPath}/ParkingLot.jsp">ADD NEW PARKING SPOT</button>
        <button type="button" class="option-button" value="${pageContext.request.contextPath}/Employee.jsp">ADD NEW EMPLOYEE</button>
        <button type="button" class="option-button" value="${pageContext.request.contextPath}/AssignPage.jsp">ASSIGN PARKING SPOT TO EMPLOYEE</button>
        <button type="button" class="option-button" value="${pageContext.request.contextPath}/RemoveSpotFromEmployee.jsp">REMOVE PARKING SPOT FROM EMPLOYEE</button>
        <button type="button" class="option-button" value="${pageContext.request.contextPath}/ParkingLotInfo.jsp">SHOW PARKING SPOT INFO</button>
        <button type="button" class="option-button" value="${pageContext.request.contextPath}/EmployeesInfo">SHOW ALL EMPLOYEES INFO</button>
        <button type="button" class="option-button" value="${pageContext.request.contextPath}/ParkingLotsInfo">SHOW ALL PARKING SPOT INFO</button>
        <button type="button" class="option-button" value="${pageContext.request.contextPath}/RemoveEmployee.jsp">REMOVE EMPLOYEE</button>
        <button type="button" class="option-button" value="${pageContext.request.contextPath}/RemoveSpot.jsp">REMOVE PARKING SPOT</button>
    </div>
</form>
</div>
</body>

<script>
    const buttons = document.querySelectorAll(".option-button");

    buttons.forEach(button => {
        button.addEventListener("click", function () {
            window.location.href = this.value;
        });
    });
</script>
</html>