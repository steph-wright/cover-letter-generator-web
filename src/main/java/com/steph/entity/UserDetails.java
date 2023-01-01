package com.steph.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class UserDetails {

    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;

}
