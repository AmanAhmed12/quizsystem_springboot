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
@Table(name = "admin_details")
public class Admin {

    @Id
    private String email;
    private String username;
    private String password;
    private String status;

    // Constructors, getters, and setters
}
