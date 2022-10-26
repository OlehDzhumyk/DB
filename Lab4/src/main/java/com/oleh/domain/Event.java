package com.oleh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    private Integer id;
    private String organizer;
    private String phoneNumber;
    private String address;
    private String name;
    private String date;
    private String duration;
    private String eventInformation;
}
