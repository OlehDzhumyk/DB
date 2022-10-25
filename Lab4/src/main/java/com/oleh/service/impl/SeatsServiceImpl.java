package com.oleh.service.impl;

import com.oleh.dao.SeatsDao;
import com.oleh.domain.Seats;
import com.oleh.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class SeatsServiceImpl implements SeatsService {

    @Autowired
    private SeatsDao seatsDao;

    @Override
    public List<Seats> findAll() {
        return seatsDao.findAll();
    }

    @Override
    public Optional<Seats> findById(Integer id) {
        return seatsDao.findById(id);
    }

    @Override
    public int create(Seats seats) {
        return seatsDao.create(seats);
    }

    @Override
    public int update(Integer id, Seats seats) {
        return seatsDao.update(id, seats);
    }

    @Override
    public int delete(Integer id) {
        return seatsDao.delete(id);
    }

    @Override
    public Optional<Seats> findBySeatPrice(Integer price) {
        return seatsDao.findBySeatPrice(price);
    }

}
