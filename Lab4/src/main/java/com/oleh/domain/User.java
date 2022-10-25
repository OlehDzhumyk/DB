package com.oleh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String name;
    private String secondName;
    private String phoneNumber;
    private String email;
    private String city;
    private String region;
    private Integer loginAndPasswordId;
}
