package com.exercise.demo.service;

import com.exercise.demo.dto.EmployeeResponseDTO;
import com.exercise.demo.mapper.EmployeeMapper;
import com.exercise.demo.model.Employee;
import com.exercise.demo.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class EmployeeAnalyticsService {

    EmployeeRepository employeeRepository;
    EmployeeMapper mapper;

    public EmployeeAnalyticsService(EmployeeRepository employeeRepository, EmployeeMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public Map<String, Double> getAverageSalaryByDept() {
        List<Employee> employeeList = employeeRepository.findAll();

        Map<String, Double> collect = employeeList.stream()
                .map(employee -> EmployeeMapper.toDto(employee))
                .collect(Collectors.groupingBy(employee -> employee.getDepartment(),
                        Collectors.averagingDouble(emp -> emp.getSalary())));

        return collect;
    }

    public List<EmployeeResponseDTO> topNHighestPaidSalary(int n) {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeResponseDTO> collect = employees.stream()
                .map(EmployeeMapper::toDto)
                .sorted(Comparator.comparing(EmployeeResponseDTO::getSalary).reversed())
                .limit(n)
                .collect(Collectors.toList());

        return collect;
    }

    public List<String> getEmployeeNamesOlderThan(int age) {
        List<Employee> employees = employeeRepository.findAll();

        List<String> collect = employees.stream()
                .filter(employee -> employee.getAge() > age)
                .map(Employee::getName)
                .collect(Collectors.toList());

        return collect;
    }

//    Groups employees by department.
//    Counts how many employees are in each department.
    public Map<String, Long> groupEmployeesByDeptAndCount() {
        List<Employee> employees = employeeRepository.findAll();

        Map<String, Long> collect = employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment(),
                        Collectors.counting()));

        return collect;
    }
    
    // Finds the employee whose name has the most characters.
    public Optional<EmployeeResponseDTO> employeeWithLongestName() {
        List<Employee> employees = employeeRepository.findAll();

        Optional<EmployeeResponseDTO> collect = employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .map(EmployeeMapper::toDto)
                .findFirst();

        return collect;
    }

}
