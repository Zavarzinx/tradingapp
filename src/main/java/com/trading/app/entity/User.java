package com.trading.app.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Set;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String username;

    private String firstName;

    private String lastName;

    private boolean active;

    private String password;

    private String email;

    @Singular
    private Set<Role> roles;

    private Set<Order> orders;

    private Wallet wallet;

    public User(String username, String firstName, String lastName, String password, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
}
