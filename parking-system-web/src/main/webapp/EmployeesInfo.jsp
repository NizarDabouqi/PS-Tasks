<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EmployeesInfo</title>
    <link rel="stylesheet" type="text/css" href="../Styles/styles.css">
    <style>
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
<div class="navbar">
    <h1>ProgressSoft Parking System</h1>
</div>

<div class="container">
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
