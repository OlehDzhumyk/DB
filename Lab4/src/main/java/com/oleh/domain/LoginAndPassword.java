package com.oleh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginAndPassword {
    private Integer id;
    private String login;
    private String password;
}