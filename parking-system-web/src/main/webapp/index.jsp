<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>HomePage</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        #navbar {
            background-color: #003268;
            color: #fff;
            padding: 10px;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        #navbar h1 {
            margin: 0;
            font-size: 24px;
            font-weight: bold;
            letter-spacing: 2px;
        }

        #parkingForm {
            width: 60%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

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
<div id="navbar">
    <h1>ProgressSoft Parking System</h1>
</div>

<form id="parkingForm" method="POST">
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