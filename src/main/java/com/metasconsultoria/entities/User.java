package com.metasconsultoria.entities;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private int idUser;
    private String name;
    private String password;
    private String email;

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
