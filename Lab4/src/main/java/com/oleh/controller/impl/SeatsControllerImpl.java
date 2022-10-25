package com.oleh.controller.impl;

import com.oleh.controller.SeatsController;
import com.oleh.domain.Seats;
import com.oleh.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class SeatsControllerImpl implements SeatsController {

    @Autowired
    private SeatsService seatsService;

    @Override
    public List<Seats> findAll() {
        return seatsService.findAll();
    }

    @Override
    public Optional<Seats> findById(Integer id) {
        return seatsService.findById(id);
    }

    @Override
    public int create(Seats seats) {
        return seatsService.create(seats);
    }

    @Override
    public int update(Integer id, Seats seats) {
        return seatsService.update(id, seats);
    }

    @Override
    public int delete(Integer id) {
        return seatsService.delete(id);
    }

    @Override
    public Optional<Seats> findBySeatPrice(Integer price) {
        return seatsService.findBySeatPrice(price);
    }

}
