<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ParkingLotInfo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        #container {
            width: 80%;
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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

        h2 {
            font-size: 24px;
            color: black;
            margin-bottom: 20px;
            text-align: center;
            font-weight: bold;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #b6bac3;
            color: black;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
            font-weight: bold;
        }

        input[type="submit"]:hover {
            background-color: #7a7d83;
        }

        .error-message {
            color: red;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        td {
            padding: 10px;
            border: 1px solid #ddd;
        }

        h4 {
            margin: 0;
        }

    </style>
</head>

<body>
<div id="navbar">
    <h1>ProgressSoft Parking System</h1>
</div>

<div id="container">
    <h2>Parking Spot Information:</h2>

    <form id="ParkingLot" method="POST">
        <label>Enter Parking ID:</label>
        <label>
            <input type="text" name="parkingId">
        </label>

        <c:if test="${not empty errorMessage}">
            <span class="error-message">${errorMessage}</span><br><br>
        </c:if>

        <input type="submit" value="Print" onclick="showParkingLotInfo()">
        <input type="submit" value="Home" onclick="goToHome()">
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

<script>
    function showParkingLotInfo() {
        document.getElementById("ParkingLot").action = "${pageContext.request.contextPath}/ParkingLotInfo";
        document.getElementById("ParkingLot").submit();
    }

    function goToHome() {
        document.getElementById("ParkingLot").action = "${pageContext.request.contextPath}/index.jsp";
        document.getElementById("ParkingLot").submit();
    }
</script>
</body>
</html>
