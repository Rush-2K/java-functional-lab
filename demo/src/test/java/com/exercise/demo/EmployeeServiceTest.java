package com.exercise.demo;

import com.exercise.demo.dto.EmployeeResponseDTO;
import com.exercise.demo.model.Employee;
import com.exercise.demo.repository.EmployeeRepository;
import com.exercise.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Service Test")
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

//    private EmployeeResponseDTO employeeResponseDTO;
    private Employee employee;

    @BeforeEach
    void setUp() {
//        this.employeeResponseDTO = EmployeeResponseDTO.builder()
//                .id(1)
//                .age(23)
//                .name("Jinwoo")
//                .salary(5000.00)
//                .department("IT")
//                .build();

        this.employee = Employee.builder()
                .id(1)
                .age(32)
                .name("Jinwoo")
                .salary(5000.00)
                .department("IT")
                .build();
    }

    @Nested
    @DisplayName("Get All Employees Tests")
    class getAllEmployeesTest {

        @Test
        @DisplayName("Should get all employees successfully")
        void shouldGetAllEmployeesSuccessfully() {
            // Given
            when(employeeRepository.findAll()).thenReturn(List.of(employee));

            // When
            List<EmployeeResponseDTO> result = employeeService.getAllEmployee();

            //Then
            assertEquals(1, result.size());
            assertEquals("Jinwoo", result.get(0).getName());

            verify(employeeRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("Get List of Names Test")
    class getNamesOnlyTest {

        @Test
        @DisplayName("Should get all names that are age greater than 30 successfully")
        void shouldGetNamesWhenAgeGreaterThan30Successfully() {
            //Given
            Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "HR", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "Finance", 40, 5000.0);

            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            //When
            List<String> result = employeeService.getNamesOnly();

            //Then
            assertEquals(2, result.size()); // only Bob & Charlie pass the filter
            assertTrue(result.contains("Bob"));
            assertTrue(result.contains("Charlie"));
            assertFalse(result.contains("Alice")); // Alice is under 30, excluded
            verify(employeeRepository, times(1)).findAll();

        }
    }

    @Test
    @DisplayName("should get highest salary")
    void shouldGetHighestSalarySuccessfully() {
        //Given
        Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
        Employee emp2 = new Employee(2, "Bob", "HR", 35, 4500.0);
        Employee emp3 = new Employee(3, "Charlie", "Finance", 40, 5000.0);

        when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

        //When
        Double result = employeeService.getHighestSalary();

        //Then
        assertEquals(5000.0, result);

//        verify(employeeRepository, times(1)).findAll();
        verifyNoInteractions(employeeRepository);
    }

    @Nested
    @DisplayName("Get Average Salary Test")
    class getAverageSalaryTest {
        @Test
        @DisplayName("should get average salary")
        void shouldGetAverageSalarySuccessfully() {
            //Given
            Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "HR", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "Finance", 40, 5000.0);

            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            //When
            Double result = employeeService.getAverageSalary();

            //Then
            assertEquals(4500.0, result);
        }

        @Test
        @DisplayName("should get average salary equals to zero")
        void shouldGetAverageSalaryZeroSuccessfully() {
            //Given
            when(employeeRepository.findAll()).thenReturn(List.of());

            //When
            Double result = employeeService.getAverageSalary();

            //Then
            assertEquals(0, result);
        }
    }

    @Nested
    @DisplayName("Get Sum Salary")
    class getSumSalary {

        @Test
        @DisplayName("Should get sum salary successfully")
        void shouldGetSumSalarySuccessfully() {
            //Given
            Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "HR", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "Finance", 40, 5000.0);
            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            //When
            Double result = employeeService.getSumSalary();

            //Then
            assertEquals(13500.0, result);
        }

    }

    @Nested
    @DisplayName("Get Sorted Salary Desc Test")
    class getSortedSalaryDescTest {
        @Test
        @DisplayName("should get sorted salary desc successfully")
        void shouldGetSortedSalaryDescSuccessfully() {
            //Given
            Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "HR", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "Finance", 40, 5000.0);
            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            //When
            List<Double> result = employeeService.getSortedSalaryDesc();

            //Then
            assertEquals(5000.0, result.getFirst());
            assertEquals(4000.0, result.get(2));
        }
    }

    @Nested
    @DisplayName("Get Distinct Department Test")
    class getDistinctDepartmentTest {
        @Test
        @DisplayName("get distinct department successfuly")
        void shouldgetDistinctDepartmentSuccessfuly() {
            //Given
            Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "HR", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "IT", 40, 5000.0);
            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            //When
            List<String> result = employeeService.getDistinctDepartmentNames();

            //Then
            assertEquals(2, result.size());
            assertTrue(result.contains("IT"));
            assertTrue(result.contains("HR"));

            verify(employeeRepository, times(1)).findAll();

        }

        @Test
        @DisplayName("Should return empty list when no employees exists")
        void shouldReturnEmptyListWhenNoEmployees() {
            //Given
            when(employeeRepository.findAll()).thenReturn(List.of());

            //When
            List<String> result = employeeService.getDistinctDepartmentNames();

            //Then
            assertTrue(result.isEmpty());

            verify(employeeRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("Get One Result only Test")
    class getOneTest {
        @Test
        @DisplayName("Should return one result successfully")
        void shouldReturnOneSuccessfully() {
            //Given
            Employee emp1 = new Employee(1, "Alice", "Finance", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "HR", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "Finance", 40, 5000.0);
            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            //When
            EmployeeResponseDTO result = employeeService.getOne();

            //Then
            assertEquals("Alice", result.getName());

            verify(employeeRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("group employees by department test")
    class groupEmployeesByDepartmentTest {
        @Test
        @DisplayName("should return group employees by department successfuly")
        void shouldReturnGroupEmployeesByDeptSuccessfully() {
            //Given
            Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "HR", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "IT", 40, 5000.0);
            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            //When
            Map<String, List<EmployeeResponseDTO>> result = employeeService.groupEmployeesByDepartment();

            //Then
            assertEquals(2, result.size()); //IT and HR

            // check IT group
            assertTrue(result.containsKey("IT"));
            assertEquals(2, result.get("IT").size());
            assertEquals("Alice", result.get("IT").get(0).getName());
            assertEquals("Charlie", result.get("IT").get(1).getName());

            // check HR group
            assertTrue(result.containsKey("HR"));
            assertEquals(1, result.get("HR").size());
            assertEquals("Bob", result.get("HR").get(0).getName());

            verify(employeeRepository, times(1)).findAll();
        }
    }

}
