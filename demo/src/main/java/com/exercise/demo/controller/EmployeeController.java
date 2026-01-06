package com.exercise.demo.controller;

import com.exercise.demo.dto.EmployeeResponseDTO;
import com.exercise.demo.model.Employee;
import com.exercise.demo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all employees in the IT department.
    @GetMapping("/getall")
    public ResponseEntity<?> getAllEmployee() {
        log.info("get all employee request initiated");
        List<EmployeeResponseDTO> data = employeeService.getAllEmployee();
        return ResponseEntity.ok().body(data);
    }

    // Extract only the names of employees older than 30.
    @GetMapping("/name")
    public ResponseEntity<?> getNameOnly() {
        List<String> data = employeeService.getNamesOnly();

        return ResponseEntity.ok().body(data);
    }

    // Find the employee with the highest salary
    @GetMapping("/topSalary")
    public ResponseEntity<?> highestSalary() {
        Double data = employeeService.getHighestSalary();

        return ResponseEntity.ok().body(data);
    }

    // Calculate the average salary of employees in the HR department.
    @GetMapping("/avg")
    public ResponseEntity<?> avgSalary() {
        Double data = employeeService.getAverageSalary();

        return ResponseEntity.ok().body(data);
    }

    // Group employees into a map keyed by department.
    @GetMapping("/keyed")
    public ResponseEntity<?> groupEmployeesByDepartment() {
        Map<String, List<EmployeeResponseDTO>> data = employeeService.groupEmployeesByDepartment();

        return ResponseEntity.ok().body(data);
    }

    // Sort all employees by salary, highest first (result give names only)
    @GetMapping("/sortedSalary")
    public ResponseEntity<?> sortSalary() {
        List<Double> data = employeeService.getSortedSalaryDesc();

        return ResponseEntity.ok().body(data);
    }

    // Sum all salaries.
    @GetMapping("/sum")
    public ResponseEntity<?> sumSalary() {
        Double data = employeeService.getSumSalary();

        return ResponseEntity.ok().body(data);
    }

    // Get a list of distinct department names.
    @GetMapping("/distinctdept")
    public ResponseEntity<?> distinctDepartment() {
        List<String> data = employeeService.getDistinctDepartmentNames();

        return ResponseEntity.ok().body(data);
    }

    //  Partition employees into two groups: older than 30 and 30 or younger.
//    {
//        "true": ["Bob", "Charlie", "Diana", "Frank", "Grace"],
//        "false": ["Alice", "Eve", "Hank"]
//    }
    @GetMapping("/partition")
    public ResponseEntity<?> getPartitionByAge() {
        Map<Boolean, List<String>> data = employeeService.getPartitionByAge();

        return ResponseEntity.ok().body(data);
    }

    // Get one employee from the Finance department (any)
    @GetMapping("/getone")
    public ResponseEntity<?> getOne() {
        EmployeeResponseDTO data = employeeService.getOne();

        return ResponseEntity.ok().body(data);
    }

    // Count employees for each department
    @GetMapping("/count")
    public ResponseEntity<?> countEmployeeBasedOnDepartment() {
        Map<String, Long> data = employeeService.countEmployeeBasedOnDepartment();

        return ResponseEntity.ok().body(data);
    }

    // Group employees by department and calculate the average salary.
    @GetMapping("/gpavg")
    public ResponseEntity<?> groupEmployeesByDeptAndCalculateAvgSalary() {
        Map<String, Double> data = employeeService.groupEmployeesByDeptAndCalculateAvgSalary();

        return ResponseEntity.ok().body(data);
    }

//    Group employees by department and find the employee with the highest salary.
    @GetMapping("/gphighestsalary")
    public ResponseEntity<?> groupEmployeesByDeptAndFindHighestSalary() {
        Map<String, Optional<EmployeeResponseDTO>> data = employeeService.groupEmployeesByDeptAndFindHighestSalary();

        return ResponseEntity.ok().body(data);
    }

    // Group employees by department and join their names into a single string.
    // Expected Result:
    // {
    //  "IT": "Alice, Charlie, Grace, Jack, Mona",
    //  "HR": "Bob, Eve, Hank, Karen, Nate",
    //  "Finance": "Diana, Frank, Ivy, Leo, Olivia"
    //}
    @GetMapping("/gpjoinnames")
    public ResponseEntity<?> groupEmployeesByDeptAndJoinNames() {
        Map<String, String> data = employeeService.groupEmployeesByDeptAndJoinNames();

        return ResponseEntity.ok().body(data);
    }

    // Group employees by department and get salary statistics (count, sum, min, max, average)
    @GetMapping("/gpsummarize")
    public ResponseEntity<?> groupEmployeesByDeptAndSummarize() {
        Map<String, DoubleSummaryStatistics> data = employeeService.groupEmployeesByDeptAndSummarize();

        return ResponseEntity.ok().body(data);
    }
}
