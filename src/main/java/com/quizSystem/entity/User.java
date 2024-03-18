package com.quizSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank; // Add this import

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

    @NotBlank // Ensure password is not blank
    @Column(name = "index_no")
    private String indexNo;
    
    @NotBlank // Ensure username is not blank
    private String username;

   
    private String password;
   
    private String semester;
    private String year;
    private String status;

    // Constructors, getters, and setters
}
