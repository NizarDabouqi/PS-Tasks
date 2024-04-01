<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RemoveSpot</title>
    <link rel="stylesheet" type="text/css" href="../Styles/styles.css">
    <style>
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
            font-weight: bold;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #7a7d83;
        }

        .error-message {
            color: red;
        }
    </style>
</head>

<body>
<div class="navbar">
    <h1>ProgressSoft Parking System</h1>
</div>

<div class="container">
    <h2>Remove Parking Spot:</h2>

    <form id="removeParkingSpot" method="POST">
        <label>Enter parking ID:</label>
        <input type="text" name="parkingLotId"><br><br>

        <c:if test="${not empty errorMessage}">
            <span class="error-message">${errorMessage}</span><br><br>
        </c:if>

        <input type="submit" value="Remove" onclick="removeParkingSpot()">
        <input type="submit" value="Home" onclick="goToHome()">
    </form>
</div>

<script>
    function removeParkingSpot() {
        document.getElementById("removeParkingSpot").action = "${pageContext.request.contextPath}/RemoveParkingSpot";
        document.getElementById("removeParkingSpot").submit();
    }

    function goToHome() {
        document.getElementById("removeParkingSpot").action = "${pageContext.request.contextPath}/index.jsp";
        document.getElementById("removeParkingSpot").submit();
    }
</script>

</body>
</html>
