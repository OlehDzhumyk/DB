package com.oleh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seats {
    private Integer id;
    private String section;
    private String number;
    private Integer price;
    private String reservation_status;
    private Integer eventId;
}
