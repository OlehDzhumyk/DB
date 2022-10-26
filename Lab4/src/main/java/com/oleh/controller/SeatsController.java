package com.oleh.controller;

import com.oleh.domain.Seats;

import java.util.Optional;

public interface SeatsController extends GeneralController<Seats, Integer> {
    Optional<Seats> findBySeatPrice(Integer price);

}
