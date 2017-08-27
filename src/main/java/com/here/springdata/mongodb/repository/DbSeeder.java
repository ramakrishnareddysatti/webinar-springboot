package com.here.springdata.mongodb.repository;

import com.here.springdata.mongodb.document.Address;
import com.here.springdata.mongodb.document.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nakkalwa on 19/08/17.
 * DbSeeder is called as soon as Spring Boot app is initialize as it is implementing CommandLineRunner
 * This is called to insert some employees in the employee database
 * This is optional
 */
@Component
public class DbSeeder implements CommandLineRunner {

    private EmployeeRepository employeeRepository;

    public DbSeeder(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
            Employee john = new Employee(
                    "1", "John","Software Developer",
                    new Address("India", "Mumbai")
            );
            Employee kelly = new Employee(
                    "2", "Kelly","Quality Engineer ",
                    new Address("United States", "Chicago")
            );
            Employee david = new Employee(
                    "3", "David","Business Analyst",
                    new Address("Germany", "Berlin")
            );

        //drop all employees
        this.employeeRepository.deleteAll();

        List<Employee> employeeList = Arrays.asList(john, kelly, david);

        //Add employees to the database
        this.employeeRepository.save(employeeList);
    }
}
