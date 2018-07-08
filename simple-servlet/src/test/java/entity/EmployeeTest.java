package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private static final String TEST_NAME = "Тохман Вячеслав Анатольевич";

    @Test
    void employeeTest() {
        Employee employee = new Employee();
        employee.setFio(TEST_NAME);
        assertEquals(TEST_NAME, employee.getFio());
    }
}