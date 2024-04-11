function removeSpotFromEmployee(parkingLotId) {
    $.ajax({
        type: "POST", url: "RemoveSpotFromEmployee", data: {
            parkingLotId: parkingLotId
        }, success: function (data) {
            console.log("Parking spot removed from employee successfully");
            location.reload();
        },
    });
}

function removeParkingSpot(parkingLotId) {
    $.ajax({
        type: "POST", url: "RemoveParkingSpot", data: {
            parkingLotId: parkingLotId
        }, success: function (data) {
            console.log("Parking spot removed successfully");
            location.reload();
        },
    });
}

function removeEmployee(employeeId) {
    $.ajax({
        type: "POST", url: "RemoveEmployee", data: {
            employeeId: employeeId
        }, success: function (data) {
            console.log("Employee removed successfully");
            location.reload();
        },
    });
}

function redirectToParking() {
    window.location.href = "parkingLotPage.jsp";
}

function redirectToEmployee() {
    window.location.href = "employeePage.jsp";
}

$(document).ready(function () {
    $('#searchInput').on('keyup', function () {
        const searchText = $(this).val().toLowerCase();
        $('table tr').each(function (index) {
            if (index !== 0) {
                const rowData = $(this).text().toLowerCase();
                if (rowData.indexOf(searchText) === -1) {
                    $(this).hide();
                } else {
                    $(this).show();
                }
            }
        });
    });
});



