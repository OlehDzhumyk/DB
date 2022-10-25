package com.oleh.service;

import com.oleh.domain.Seats;

import java.util.Optional;

public interface SeatsService extends GeneralService<Seats, Integer> {
    Optional<Seats> findBySeatPrice(Integer price);

}
