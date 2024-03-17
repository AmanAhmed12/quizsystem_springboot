package com.quizSystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "student_details")
public class User {

    @Id
    private String email;
    private String indexNo;
    private String username;
    private String password;
    private String semester;
    private String year;
    private String status;

    // Constructors, getters, and setters
}
