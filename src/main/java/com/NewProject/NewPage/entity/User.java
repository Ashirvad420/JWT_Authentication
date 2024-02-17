package com.NewProject.NewPage.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // this is using for SingUp Dto
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
}

// in this table username and email is unique