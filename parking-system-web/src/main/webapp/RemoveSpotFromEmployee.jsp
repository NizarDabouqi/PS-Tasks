<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RemoveSpotFromEmployee</title>
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
            color: #dc3545;
            margin-top: 10px;
        }

    </style>
</head>

<body>
<div class="navbar">
    <h1>ProgressSoft Parking System</h1>
</div>

<div class="container">
    <h2>Remove parking spot from employee:</h2>

    <form id="removeSpotFromEmployee" method="POST">

        <label>Enter parking ID to remove:</label>
        <label>
            <input type="text" name="parkingLotId">
        </label>

        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>

        <br>
        <input type="submit" value="Remove" onclick="removeSpotFromEmployee()">
        <input type="submit" value="Home" onclick="goToHome()">
    </form>
</div>
</body>

<script>
    function removeSpotFromEmployee() {
        document.getElementById("removeSpotFromEmployee").action = "${pageContext.request.contextPath}/RemoveSpotFromEmployee";
        document.getElementById("removeSpotFromEmployee").submit();
    }

    function goToHome() {
        document.getElementById("removeSpotFromEmployee").action = "${pageContext.request.contextPath}/index.jsp";
        document.getElementById("removeSpotFromEmployee").submit();
    }
</script>
</html>