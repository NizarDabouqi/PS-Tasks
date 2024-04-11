<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/functions.js"></script>

</head>
<body>
<div class="main-container">
    <%@ include file="sideNavBar.jsp" %>

    <div class="sub-container">
        <c:if test="${empty requestScope.parkingLots}">
            <div class="warning-container">
                <p class="warning-message">No Parking Spots Found</p>
            </div>
        </c:if>

        <c:if test="${not empty requestScope.parkingLots}">
            <div class="header-container">
                <button class="shortcut-button" onclick="redirectToParking()">
                    Add New Parking
                </button>

                <div class="group">
                    <svg viewBox="0 0 24 24" aria-hidden="true" class="icon">
                        <g>
                            <path
                                    d="M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.215 0 4.24-.804 5.808-2.13l3.66 3.66c.147.146.34.22.53.22s.385-.073.53-.22c.295-.293.295-.767.002-1.06zM3.5 11c0-4.135 3.365-7.5 7.5-7.5s7.5 3.365 7.5 7.5-3.365 7.5-7.5 7.5-7.5-3.365-7.5-7.5z"
                            ></path>
                        </g>
                    </svg>
                    <label>
                        <input id="searchInput" class="input" type="search" placeholder="Search"/>
                    </label>
                </div>

                <button class="shortcut-button" onclick="redirectToEmployee()">
                    Add New Employee
                </button>
            </div>
            <table>
                <tr>
                    <th class="table-header">Parking ID</th>
                    <th class="table-header">Available</th>
                    <th class="table-header">Assign To</th>
                    <th class="table-header">Employee Name</th>
                    <th class="table-header">Expiry Date</th>
                    <th class="table-header">Delete Assignation</th>
                    <th class="table-header">Delete Parking</th>
                </tr>

                <c:forEach items="${requestScope.parkingLots}" var="parkingLot">
                    <tr>
                        <td class="table-content">${parkingLot.id}</td>

                        <td class="table-content">
                            <c:choose>
                                <c:when test="${parkingLot.available}">
                                    <div class="availability-container">
                                        <div class="available"></div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="availability-container">
                                        <div class="unavailable"></div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <c:choose>
                            <c:when test="${empty parkingLot.employeeId}">
                                <td class="table-content">-</td>
                            </c:when>
                            <c:otherwise>
                                <td class="table-content">${parkingLot.employeeId}</td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${empty parkingLot.employeeName}">
                                <td class="table-content">-</td>
                            </c:when>
                            <c:otherwise>
                                <td class="table-content">${parkingLot.employeeName}</td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${empty parkingLot.expiryDate}">
                                <td class="table-content">-</td>
                            </c:when>
                            <c:otherwise>
                                <td class="table-content">${parkingLot.expiryDate}</td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${empty parkingLot.employeeId}">
                                <td class="table-content">-</td>
                            </c:when>
                            <c:otherwise>
                                <td class="table-content">
                                    <button class="deleteButton"
                                            onclick="removeSpotFromEmployee('${parkingLot.id}')">
                                        <svg
                                                xmlns="http://www.w3.org/2000/svg"
                                                fill="none"
                                                viewBox="0 0 50 59"
                                                class="bin"
                                        >
                                            <path
                                                    fill="#B5BAC1"
                                                    d="M0 7.5C0 5.01472 2.01472 3 4.5 3H45.5C47.9853 3 50 5.01472 50 7.5V7.5C50 8.32843 49.3284 9 48.5 9H1.5C0.671571 9 0 8.32843 0 7.5V7.5Z"
                                            ></path>
                                            <path
                                                    fill="#B5BAC1"
                                                    d="M17 3C17 1.34315 18.3431 0 20 0H29.3125C30.9694 0 32.3125 1.34315 32.3125 3V3H17V3Z"
                                            ></path>
                                            <path
                                                    fill="#B5BAC1"
                                                    d="M2.18565 18.0974C2.08466 15.821 3.903 13.9202 6.18172 13.9202H43.8189C46.0976 13.9202 47.916 15.821 47.815 18.0975L46.1699 55.1775C46.0751 57.3155 44.314 59.0002 42.1739 59.0002H7.8268C5.68661 59.0002 3.92559 57.3155 3.83073 55.1775L2.18565 18.0974ZM18.0003 49.5402C16.6196 49.5402 15.5003 48.4209 15.5003 47.0402V24.9602C15.5003 23.5795 16.6196 22.4602 18.0003 22.4602C19.381 22.4602 20.5003 23.5795 20.5003 24.9602V47.0402C20.5003 48.4209 19.381 49.5402 18.0003 49.5402ZM29.5003 47.0402C29.5003 48.4209 30.6196 49.5402 32.0003 49.5402C33.381 49.5402 34.5003 48.4209 34.5003 47.0402V24.9602C34.5003 23.5795 33.381 22.4602 32.0003 22.4602C30.6196 22.4602 29.5003 23.5795 29.5003 24.9602V47.0402Z"
                                                    clip-rule="evenodd"
                                                    fill-rule="evenodd"
                                            ></path>
                                            <path fill="#B5BAC1" d="M2 13H48L47.6742 21.28H2.32031L2 13Z"></path>
                                        </svg>
                                    </button>
                                </td>
                            </c:otherwise>
                        </c:choose>

                        <td>
                            <button class="deleteButton"
                                    onclick="removeParkingSpot('${parkingLot.id}')">
                                <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        fill="none"
                                        viewBox="0 0 50 59"
                                        class="bin"
                                >
                                    <path
                                            fill="#B5BAC1"
                                            d="M0 7.5C0 5.01472 2.01472 3 4.5 3H45.5C47.9853 3 50 5.01472 50 7.5V7.5C50 8.32843 49.3284 9 48.5 9H1.5C0.671571 9 0 8.32843 0 7.5V7.5Z"
                                    ></path>
                                    <path
                                            fill="#B5BAC1"
                                            d="M17 3C17 1.34315 18.3431 0 20 0H29.3125C30.9694 0 32.3125 1.34315 32.3125 3V3H17V3Z"
                                    ></path>
                                    <path
                                            fill="#B5BAC1"
                                            d="M2.18565 18.0974C2.08466 15.821 3.903 13.9202 6.18172 13.9202H43.8189C46.0976 13.9202 47.916 15.821 47.815 18.0975L46.1699 55.1775C46.0751 57.3155 44.314 59.0002 42.1739 59.0002H7.8268C5.68661 59.0002 3.92559 57.3155 3.83073 55.1775L2.18565 18.0974ZM18.0003 49.5402C16.6196 49.5402 15.5003 48.4209 15.5003 47.0402V24.9602C15.5003 23.5795 16.6196 22.4602 18.0003 22.4602C19.381 22.4602 20.5003 23.5795 20.5003 24.9602V47.0402C20.5003 48.4209 19.381 49.5402 18.0003 49.5402ZM29.5003 47.0402C29.5003 48.4209 30.6196 49.5402 32.0003 49.5402C33.381 49.5402 34.5003 48.4209 34.5003 47.0402V24.9602C34.5003 23.5795 33.381 22.4602 32.0003 22.4602C30.6196 22.4602 29.5003 23.5795 29.5003 24.9602V47.0402Z"
                                            clip-rule="evenodd"
                                            fill-rule="evenodd"
                                    ></path>
                                    <path fill="#B5BAC1" d="M2 13H48L47.6742 21.28H2.32031L2 13Z"></path>
                                </svg>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <br>
    </div>
</div>
</body>
</html>



