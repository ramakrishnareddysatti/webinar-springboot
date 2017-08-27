package com.here.springdata.mongodb.repository;

import com.here.springdata.mongodb.document.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nakkalwa on 19/08/17.
 */
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    @Query(value = "{address.city:?0}")
    List<Employee> findByCity(String city);
}
