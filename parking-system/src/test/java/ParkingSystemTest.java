//import com.progressoft.exception.ParkingSystemException;
//import com.progressoft.model.Employee;
//import com.progressoft.model.ParkingLot;
//import com.progressoft.manager.ParkingSystemManagerImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ParkingSystemTest {
//    private ParkingSystemManagerImpl parkingSystem;
//
//    @BeforeEach
//    public void setUp() {
//        parkingSystem = new ParkingSystemManagerImpl();
//    }
//
//    @Test
//    public void givenInvalidParkingLot_whenAssignSpotToEmployee_thenThrowParkingSystemException() {
//        Employee employee = new Employee("0205752", "Nizar Dabouqi");
//        String invalidParkingId = "2B";
//        ParkingLot invalidParkingLot = new ParkingLot(invalidParkingId);
//        parkingSystem.addNewEmployee(employee);
//
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.assignSpotToEmployee(invalidParkingLot, employee));
//        assertEquals("Parking ID not found", exception.getMessage());
//    }
//
//    @Test
//    public void givenInvalidEmployee_whenAssignSpotToEmployee_thenThrowParkingSystemException() {
//        Employee invalidEmployee = new Employee("0193827", "Samer Awni");
//        ParkingLot parkingLot = new ParkingLot("1A");
//        parkingSystem.addNewSpot(parkingLot);
//
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.assignSpotToEmployee(parkingLot, invalidEmployee));
//        assertEquals("Employee ID not found", exception.getMessage());
//    }
//
//    @Test
//    public void givenOccupiedParkingLotForSameEmployee_whenAssignSpotToEmployee_thenThrowParkingSystemException() {
//        Employee employee = new Employee("0205752", "Nizar Dabouqi");
//        ParkingLot parkingLot = new ParkingLot("1A");
//        parkingSystem.addNewEmployee(employee);
//        parkingSystem.addNewSpot(parkingLot);
//        parkingSystem.assignSpotToEmployee(parkingLot, employee);
//
//        ParkingLot occuPiedparkingLot = new ParkingLot("1A");
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.assignSpotToEmployee(occuPiedparkingLot, employee));
//        assertEquals("The employee is already assigned to this parking", exception.getMessage());
//    }
//
//    @Test
//    public void givenOccupiedParkingLotForDifferentEmployee_whenAssignSpotToEmployee_thenThrowParkingSystemException() {
//        Employee employee = new Employee("0205752", "Nizar Dabouqi");
//        ParkingLot parkingLot = new ParkingLot("1A");
//        parkingSystem.addNewEmployee(employee);
//        parkingSystem.addNewSpot(parkingLot);
//        parkingSystem.assignSpotToEmployee(parkingLot, employee);
//
//        Employee newEmployee = new Employee("2220723", "Lujain Yazan");
//        parkingSystem.addNewEmployee(newEmployee);
//
//        ParkingLot occuPiedparkingLot = new ParkingLot("1A");
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.assignSpotToEmployee(occuPiedparkingLot, newEmployee));
//        assertEquals("The parking is assigned to another employee", exception.getMessage());
//    }
//
//    @Test
//    public void givenInvalidParkingLot_whenRemoveSpotFromEmployee_thenThrowParkingSystemException() {
//        String invalidParkingId = "2A";
//        ParkingLot invalidParking = new ParkingLot(invalidParkingId);
//
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.removeSpotFromEmployee(invalidParking));
//        assertEquals("Parking ID not found", exception.getMessage());
//    }
//
//    @Test
//    public void givenEmptyParkingLot_whenRemoveSpotFromEmployee_thenThrowParkingSystemException() {
//        ParkingLot parkingLot = new ParkingLot("1A");
//        parkingSystem.addNewSpot(parkingLot);
//
//        String emptyParkingId = "1A";
//        ParkingLot emptyParkingLot = new ParkingLot(emptyParkingId);
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.removeSpotFromEmployee(emptyParkingLot));
//        assertEquals("No employee assigned to this parking", exception.getMessage());
//    }
//
//    @Test
//    public void givenExistingParkingLot_whenAddNewSpot_thenThrowParkingSystemException() {
//        ParkingLot parkingLot = new ParkingLot("1A");
//        parkingSystem.addNewSpot(parkingLot);
//
//        ParkingLot existingParkingLot = new ParkingLot("1A");
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.addNewSpot(existingParkingLot));
//        assertEquals("Parking ID already exists", exception.getMessage());
//    }
//
//    @Test
//    public void givenEmptyParkingLot_whenAddNewSpot_thenThrowParkingSystemException() {
//        ParkingLot emptyParkingLot = new ParkingLot("");
//
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.addNewSpot(emptyParkingLot));
//        assertEquals("Parking ID cannot be empty", exception.getMessage());
//    }
//
//    @Test
//    public void givenNullParking_whenAddNewSpot_thenThrowParkingSystemException() {
//        ParkingLot nullParking = new ParkingLot(null);
//
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.addNewSpot(nullParking));
//        assertEquals("Parking ID cannot be empty", exception.getMessage());
//    }
//
//    @Test
//    public void givenExistingEmployee_whenAddNewEmployee_thenThrowParkingSystemException() {
//        Employee employee = new Employee("0205752", "Nizar Dabouqi");
//        parkingSystem.addNewEmployee(employee);
//
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.addNewEmployee(employee));
//        assertEquals("Employee ID already exists", exception.getMessage());
//    }
//
//    @Test
//    public void givenEmptyEmployeeId_whenAddNewEmployee_thenThrowParkingSystemException() {
//        Employee employee = new Employee("", "Nizar Dabouqi");
//
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.addNewEmployee(employee));
//        assertEquals("Employee ID cannot be empty", exception.getMessage());
//    }
//
//    @Test
//    public void givenNullEmployeeId_whenAddNewEmployee_thenThrowParkingSystemException() {
//        Employee employee = new Employee(null, "Nizar Dabouqi");
//
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.addNewEmployee(employee));
//        assertEquals("Employee ID cannot be empty", exception.getMessage());
//    }
//
//    @Test
//    public void givenEmptyEmployeeName_whenAddNewEmployee_thenThrowParkingSystemException() {
//        Employee employee = new Employee("0205752", "");
//
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.addNewEmployee(employee));
//        assertEquals("Employee name cannot be empty", exception.getMessage());
//    }
//
//    @Test
//    public void givenNullEmployeeName_whenAddNewEmployee_thenThrowParkingSystemException() {
//        Employee employee = new Employee("0205752", null);
//
//        ParkingSystemException exception = assertThrows(ParkingSystemException.class, () -> parkingSystem.addNewEmployee(employee));
//        assertEquals("Employee name cannot be empty", exception.getMessage());
//    }
//
//    @Test
//    public void givenTwoParkingSpotsAndSameEmployee_whenAssigningSpots_thenNoExceptionThrown() {
//        Employee employee = new Employee("0205752", "Nizar Dabouqi");
//        String spot1 = "1A";
//        String spot2 = "1B";
//
//        ParkingLot parkingLot1 = new ParkingLot(spot1);
//        ParkingLot parkingLot2 = new ParkingLot(spot2);
//
//        parkingSystem.addNewEmployee(employee);
//        parkingSystem.addNewSpot(parkingLot1);
//        parkingSystem.addNewSpot(parkingLot2);
//
//        assertDoesNotThrow(() -> {
//            parkingSystem.assignSpotToEmployee(parkingLot1, employee);
//            parkingSystem.assignSpotToEmployee(parkingLot2, employee);
//        });
//    }
//
//    @Test
//    public void givenValidData_whenAssignSpotToEmployee_thenNoExceptionThrown() {
//        Employee employee = new Employee("0205752", "Nizar Dabouqi");
//        String newSpot = "1A";
//        ParkingLot newParkingLot = new ParkingLot(newSpot);
//
//        parkingSystem.addNewEmployee(employee);
//        parkingSystem.addNewSpot(newParkingLot);
//
//        assertDoesNotThrow(() -> parkingSystem.assignSpotToEmployee(newParkingLot, employee));
//        ParkingLot occupiedSpot = parkingSystem.findParkingLotById(newSpot);
//        assertNotNull(occupiedSpot.getEmployeeId());
//        assertFalse(occupiedSpot.isAvailable());
//        assertEquals(occupiedSpot.getEmployeeId(), employee.getId());
//    }
//
//    @Test
//    public void givenValidData_whenRemoveSpotFromEmployee_thenNoExceptionThrown() {
//        Employee employee = new Employee("0205752", "Nizar Dabouqi");
//        String newSpot = "1A";
//        ParkingLot newParkingLot = new ParkingLot(newSpot);
//
//        parkingSystem.addNewEmployee(employee);
//        parkingSystem.addNewSpot(newParkingLot);
//        parkingSystem.assignSpotToEmployee(newParkingLot, employee);
//
//        assertDoesNotThrow(() -> parkingSystem.removeSpotFromEmployee(newParkingLot));
//        ParkingLot removedSpot = parkingSystem.findParkingLotById(newSpot);
//        assertNull(removedSpot.getEmployeeId());
//        assertTrue(removedSpot.isAvailable());
//    }
//
//    @Test
//    public void givenValidData_whenAddNewSpot_thenNoExceptionThrown() {
//        String newSpot = "2A";
//        ParkingLot newParkingLot = new ParkingLot(newSpot);
//
//        assertDoesNotThrow(() -> parkingSystem.addNewSpot(newParkingLot));
//        ParkingLot addedSpot = parkingSystem.findParkingLotById(newSpot);
//        assertNotNull(addedSpot);
//        assertEquals(newSpot, addedSpot.getId());
//    }
//
//    @Test
//    public void givenValidData_whenAddNewEmployee_thenNoExceptionThrown() {
//        Employee employee = new Employee("0184162", "George Kawar");
//        assertDoesNotThrow(() -> parkingSystem.addNewEmployee(employee));
//    }
//}
