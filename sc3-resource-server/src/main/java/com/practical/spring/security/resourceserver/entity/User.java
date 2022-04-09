package com.practical.spring.security.resourceserver.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Suleyman Yildirim
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String algorithm;
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities;



}
