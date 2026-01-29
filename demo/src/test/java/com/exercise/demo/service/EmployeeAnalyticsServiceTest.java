package com.exercise.demo.service;

import com.exercise.demo.dto.EmployeeResponseDTO;
import com.exercise.demo.mapper.EmployeeMapper;
import com.exercise.demo.model.Employee;
import com.exercise.demo.repository.EmployeeRepository;
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
@DisplayName("Employee Analytics Service Test")
public class EmployeeAnalyticsServiceTest {

    @InjectMocks
    EmployeeAnalyticsService employeeAnalyticsService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmployeeMapper employeeMapper;

    @Nested
    @DisplayName("getAverageSalaryByDepartment Test")
    class getAverageSalaryByDeptTest {

        @Test
        @DisplayName("should get average salary by department successfully")
        void shouldGetAverageSalaryByDeptSuccessfully() {
            // given
            Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "IT", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "Finance", 40, 5000.0);

            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            // when
            Map<String, Double> result = employeeAnalyticsService.getAverageSalaryByDept();

            // then
            assertEquals(4250.0, result.get("IT"));
            assertEquals(5000.0, result.get("Finance"));

            verify(employeeRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("top N Highest Paid Salary Test")
    class topNHighestPaidSalaryTest {

        @Test
        @DisplayName("should get top n highest paid salary successfully")
        void shouldGetTopNHighestPaidSalarySuccessfully() {
            int n = 2;
            Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "IT", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "Finance", 40, 5000.0);

            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            List<EmployeeResponseDTO> result = employeeAnalyticsService.topNHighestPaidSalary(n);

            assertEquals(2, result.size());
            assertEquals("Charlie", result.get(0).getName());
            assertEquals("Bob", result.get(1).getName());

            // Ensure Alice is excluded
            assertFalse(result.stream()
                    .anyMatch(employeeResponseDTO -> employeeResponseDTO.getName().equals("Alice")));

            verify(employeeRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("get employee names older than test")
    class getEmployeeNamesOlderThanTest {
        @Test
        @DisplayName("should get employee names older than successfully")
        void shouldGetEmployeeNamesOlderThanSuccessfully() {
            //given
            int age = 30;
            Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "IT", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "Finance", 40, 5000.0);

            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            //when
            List<String> result = employeeAnalyticsService.getEmployeeNamesOlderThan(age);

            //then
            assertEquals(2, result.size());

            assertTrue(result.contains("Bob"));
            assertTrue(result.contains("Charlie"));
            assertFalse(result.contains("Alice"));

            verify(employeeRepository, times(1)).findAll();
        }

        @Test
        @DisplayName("should return empty list successfully")
        void shouldReturnEmptyListSuccessfully() {
            //given
            int age = 30;
            when(employeeRepository.findAll()).thenReturn(List.of());

            //when
            List<String> result = employeeAnalyticsService.getEmployeeNamesOlderThan(age);

            //then
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("All employees under threshold")
        void shouldReturnSuccessWhenAllEmployeesUnderThreshold() {
            //given
            int age = 30;
            Employee emp1 = new Employee(1, "Alice", "IT", 25, 4000.0);
            Employee emp2 = new Employee(2, "Bob", "IT", 35, 4500.0);
            Employee emp3 = new Employee(3, "Charlie", "Finance", 40, 5000.0);

            when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2, emp3));

            //when
            List<String> result = employeeAnalyticsService.getEmployeeNamesOlderThan(age);

            //then

        }
    }
}
