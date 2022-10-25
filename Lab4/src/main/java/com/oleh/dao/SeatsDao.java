package com.oleh.dao;

import com.oleh.domain.Seats;

import java.util.Optional;

public interface SeatsDao extends GeneralDao<Seats, Integer> {
    Optional<Seats> findBySeatPrice(Integer price);

}
