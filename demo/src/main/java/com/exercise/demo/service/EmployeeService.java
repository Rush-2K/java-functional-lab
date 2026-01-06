package com.exercise.demo.service;

import com.exercise.demo.dto.EmployeeResponseDTO;
import com.exercise.demo.mapper.EmployeeMapper;
import com.exercise.demo.model.Employee;
import com.exercise.demo.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeResponseDTO> getAllEmployee() {
        List<Employee> employee = employeeRepository.findAll();
        log.info("Employee data: {}", employee);

        List<EmployeeResponseDTO> result = employee.stream()
                .map(emp -> EmployeeMapper.toDto(emp))
                .collect(Collectors.toList());

        log.info("Result: {}", result);

        return result;
    }

    public List<String> getNamesOnly() {
        List<Employee> employee = employeeRepository.findAll();

        List<String> filteredEmployee = employee.stream()
                .filter(emp -> emp.getAge() > 30)
                .map(emp -> emp.getName())
                .collect(Collectors.toList());

        return filteredEmployee;
    }

    public Double getHighestSalary() {
        List<Employee> employee = employeeRepository.findAll();

        Double result = employee.stream()
                .sorted(Comparator.comparing(employee1 -> employee1.getSalary()))
                .map(emp -> emp.getSalary())
                .findFirst()
                .orElse(null);

        return result;
    }

    public Double getAverageSalary() {
        List<Employee> employee = employeeRepository.findAll();

        Double average = employee.stream()
                .mapToDouble(emp -> emp.getSalary())
                .average()
                .orElse(0.0);

        return average;
    }

    public Double getSumSalary() {
        List<Employee> employee = employeeRepository.findAll();

        Double sum = employee.stream()
                .mapToDouble(emp -> emp.getSalary())
                .sum();

        return sum;
    }

    public List<Double> getSortedSalaryDesc() {
        List<Employee> employees = employeeRepository.findAll();

        List<Double> result = employees.stream()
                .map(emp -> emp.getSalary())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        return result;
    }

    public List<String> getDistinctDepartmentNames() {
        List<Employee> employees = employeeRepository.findAll();

        List<String> result = employees.stream()
                .map(emp -> emp.getDepartment())
                .distinct()
                .collect(Collectors.toList());

        return result;
    }

    public EmployeeResponseDTO getOne() {
        List<Employee> employees = employeeRepository.findAll();

        EmployeeResponseDTO result = employees.stream()
                .filter(emp -> emp.getDepartment().equals("Finance"))
                .map(employee -> EmployeeMapper.toDto(employee))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Failed"));

        return result;
    }

    public Map<String, List<EmployeeResponseDTO>> groupEmployeesByDepartment() {
        List<Employee> employees = employeeRepository.findAll();

        Map<String, List<EmployeeResponseDTO>> result = employees.stream()
                .map(employee -> EmployeeMapper.toDto(employee))
                .collect(Collectors.groupingBy(employee -> employee.getDepartment()));

        return result;
    }

    public Map<String, Long> countEmployeeBasedOnDepartment() {
        List<Employee> employees = employeeRepository.findAll();

        Map<String, Long> result = employees.stream()
                .map(employee -> EmployeeMapper.toDto(employee))
                .collect(Collectors.groupingBy(employee -> employee.getDepartment(),
                        Collectors.counting()));

        return result;
    }

    public Map<Boolean, List<String>> getPartitionByAge() {
        List<Employee> employees = employeeRepository.findAll();

        Map<Boolean, List<String>> collect = employees.stream()
                .collect(Collectors.partitioningBy(employee -> employee.getAge() > 30,
                        Collectors.mapping(employee -> employee.getName(),
                                Collectors.toList())));

        return collect;
    }

    public Map<String, Double> groupEmployeesByDeptAndCalculateAvgSalary() {
        List<Employee> employees = employeeRepository.findAll();

        Map<String, Double> result = employees.stream()
                .map(employee -> EmployeeMapper.toDto(employee))
                .collect(Collectors.groupingBy(employeeResponseDTO -> employeeResponseDTO.getDepartment(),
                        Collectors.averagingDouble(emp -> emp.getSalary())));

        return result;
    }

    public Map<String, Optional<EmployeeResponseDTO>> groupEmployeesByDeptAndFindHighestSalary() {
        List<Employee> employees = employeeRepository.findAll();

        Map<String, Optional<EmployeeResponseDTO>> result = employees.stream()
                .map(employee -> EmployeeMapper.toDto(employee))
                .collect(Collectors.groupingBy(employeeResponseDTO -> employeeResponseDTO.getDepartment(),
                        Collectors.maxBy(Comparator.comparing(employeeResponseDTO -> employeeResponseDTO.getSalary()))));

        if(result == null) {
            throw new RuntimeException("Failed");
        }

        return result;
    }

    public Map<String, String> groupEmployeesByDeptAndJoinNames() {
        List<Employee> employees = employeeRepository.findAll();

        Map<String, String> result = employees.stream()
                .map(employee -> EmployeeMapper.toDto(employee))
                .collect(Collectors.groupingBy(employeeResponseDTO -> employeeResponseDTO.getDepartment(),
                        Collectors.mapping(emp -> emp.getName(),
                                Collectors.joining(", "))));

        return result;
    }

    public Map<String, DoubleSummaryStatistics> groupEmployeesByDeptAndSummarize() {
        List<Employee> employees = employeeRepository.findAll();

        Map<String, DoubleSummaryStatistics> result = employees.stream()
                .map(employee -> EmployeeMapper.toDto(employee))
                .collect(Collectors.groupingBy(employeeResponseDTO -> employeeResponseDTO.getDepartment(),
                        Collectors.summarizingDouble(emp -> emp.getSalary())));

        return result;
    }
}
