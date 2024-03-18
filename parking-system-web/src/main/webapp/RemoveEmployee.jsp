<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RemoveEmployee</title>
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
<div id="navbar">
    <h1>ProgressSoft Parking System</h1>
</div>

<div id="container">
    <h2>Remove Employee:</h2>

    <form id="removeEmployee" method="POST">
        <label>Enter employee ID:</label>
        <input type="text" name="employeeId"><br><br>

        <c:if test="${not empty errorMessage}">
            <span class="error-message">${errorMessage}</span><br><br>
        </c:if>

        <input type="submit" value="Remove" onclick="removeEmployee()">
        <input type="submit" value="Home" onclick="goToHome()">
    </form>
</div>

<script>
    function removeEmployee() {
        document.getElementById("removeEmployee").action = "${pageContext.request.contextPath}/RemoveEmployee";
        document.getElementById("removeEmployee").submit();
    }

    function goToHome() {
        document.getElementById("removeEmployee").action = "${pageContext.request.contextPath}/index.jsp";
        document.getElementById("removeEmployee").submit();
    }
</script>

</body>
</html>
