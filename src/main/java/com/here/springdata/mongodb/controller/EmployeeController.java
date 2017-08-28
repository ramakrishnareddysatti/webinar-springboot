package com.here.springdata.mongodb.controller;

import java.util.List;
import com.here.springdata.mongodb.document.Employee;
import com.here.springdata.mongodb.repository.EmployeeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * Created by Supriya Nakkalwar on 19/08/17.
 */
@RestController
@RequestMapping("/employees")
@Api(value = "EmployeeControllerAPI")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Value("${maxLimit:4}")
    private int maxLimit;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Get Request will give list of employees in the database
     * @return
     */
    @GetMapping("/all")
    @ApiOperation(value = "Get list of all employees")
    public List<Employee> getEmployees(){
        List<Employee> employees = this.employeeRepository.findAll();
        return employees;
    }

    /**
     * Get Request will give specific employee from the database based on Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get an employee by Id")
    public Employee getEmployeeById(@PathVariable String id){
        Employee employee = this.employeeRepository.findOne(id);
        return employee;
    }

    /**
     *Post Request will insert a new employee in the database
     * @param employee
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "Insert a new employee")
    public String insert(@RequestBody Employee employee){
        List<Employee> employees = this.employeeRepository.findAll();
        String result = "";
        if(employees.size() < maxLimit){
            Employee newEmployee = this.employeeRepository.insert(employee);
            result = "Id:" + newEmployee.getId() + " is inserted successfully";
        }else{
            throw new RuntimeException("MaxLimit Exceeded could not insert Employee with Id:" + employee.getId());
        }
        return result;
    }

    /**
     * Put Request will update an existing employee in the database
     * @param employee
     * @return
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value = "Updates details of existing employee")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = this.employeeRepository.save(employee);
        return updatedEmployee;
    }

    /**
     * Delete request will delete an existing employee from the database
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete details of an existing employee by id")
    public String deleteEmployee(@PathVariable String id){
        this.employeeRepository.delete(id);
        String deleteMessage = "Id:"+ id + " got deleted successfully";
        return deleteMessage;
    }

    /**
     * Get request will give list of those employees who resides in the same city
     * @param city
     * @return
     */
    @GetMapping("/address/{city}")
    @ApiOperation(value = "Gets list of employees residing in same city")
    public List<Employee> findByCity(@PathVariable String city){
        List<Employee> employees = this.employeeRepository.findByCity(city);
        return employees;
    }
}
