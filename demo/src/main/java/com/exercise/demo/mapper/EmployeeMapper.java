package com.exercise.demo.mapper;

import com.exercise.demo.dto.EmployeeResponseDTO;
import com.exercise.demo.model.Employee;

public class EmployeeMapper {
     public static EmployeeResponseDTO toDto(Employee employee) {
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setId(employee.getId());
        employeeResponseDTO.setName(employee.getName());
        employeeResponseDTO.setAge(employee.getAge());
        employeeResponseDTO.setDepartment(employee.getDepartment());
        employeeResponseDTO.setSalary(employee.getSalary());

        return employeeResponseDTO;
    }
}
