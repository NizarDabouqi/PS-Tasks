<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EmployeesInfo</title>
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

        input[type="button"] {
            background-color: #b6bac3;
            color: black;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        input[type="button"]:hover {
            background-color: #7a7d83;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>
<div id="navbar">
    <h1>ProgressSoft Parking System</h1>
</div>

<div id="container">
    <h2>Employees Information:</h2><br>

    <table>
        <tr>
            <th>Employee ID</th>
            <th>Employee Name</th>
        </tr>

        <c:forEach items="${requestScope.employees}" var="employee">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <form id="employees" method="POST">
        <input type="button" value="Home" onclick="goToHome()">
    </form>
</div>
</body>

<script>
    function goToHome() {
        document.getElementById("employees").action = "${pageContext.request.contextPath}/index.jsp";
        document.getElementById("employees").submit();
    }
</script>
</html>
