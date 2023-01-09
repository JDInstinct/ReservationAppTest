package com.ibm.cube.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Document (collection = "employee")
public class Employee {
    @Id
    String id;
    private final String name;
    private final String email;

    public Employee(String name, String email) {
        if (name != null && email != null) {
            this.name = name;
            this.email = email;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee name and email must be provided");
        }

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
