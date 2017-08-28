package com.here.springdata.mongodb.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Supriya Nakkalwar on 19/08/17.
 */
@Document(collection = "Employees")
public class Employee {
    @Id
    private String id;
    private String name;
    private String designation;
    private Address address;

    protected Employee(){

    }

    public Employee(String id, String name, String designation, Address address) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public Address getAddress() {
        return address;
    }
}

