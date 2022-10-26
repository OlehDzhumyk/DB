package com.oleh.dao.impl;

import com.oleh.dao.SeatsDao;
import com.oleh.domain.Seats;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class SeatsDaoImpl implements SeatsDao {
    private static final String FIND_ALL = "SELECT * FROM seats";
    private static final String CREATE = "INSERT seats (section, number, price, reservation_status, event_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE seats SET section=?, number=?, price=?, reservation_status=?, event_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM seats WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM seats WHERE id=?";
    private static final String FIND_BY_SEAT_PRICE = "SELECT * FROM seats WHERE price=?";

    private final JdbcTemplate jdbcTemplate;

    public SeatsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Seats> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Seats.class));
    }

    @Override
    public Optional<Seats> findById(Integer id) {
        Optional<Seats> seats;
        try {
            seats = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Seats.class), id));
        } catch (EmptyResultDataAccessException e) {
            seats = Optional.empty();
        }
        return seats;
    }

    @Override
    public int create(Seats seats) {
        return jdbcTemplate.update(CREATE, seats.getSection(), seats.getNumber(), seats.getPrice(),
                                    seats.getReservation_status(), seats.getEventId());
    }

    @Override
    public int update(Integer id, Seats seats) {
        return jdbcTemplate.update(UPDATE, seats.getSection(), seats.getNumber(), seats.getPrice(),
                                    seats.getReservation_status(), seats.getEventId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Seats> findBySeatPrice(Integer price) {
        Optional<Seats> seats;
        try {
            seats = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_SEAT_PRICE,
                    BeanPropertyRowMapper.newInstance(Seats.class), price));
        } catch (EmptyResultDataAccessException e) {
            seats = Optional.empty();
        }
        return seats;
    }

}
