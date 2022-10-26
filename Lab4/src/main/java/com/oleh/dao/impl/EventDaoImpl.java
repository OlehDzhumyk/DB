package com.oleh.dao.impl;

import com.oleh.dao.EventDao;
import com.oleh.domain.Event;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class EventDaoImpl implements EventDao {

    private static final String FIND_ALL = "SELECT * FROM event";
    private static final String CREATE = "INSERT event(organizer, phone_number, address, date, duration, event_information, name) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE event SET organizer=?, phone_number=?, address=?, date=?, duration=?, event_information=?, name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM event WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM event WHERE id=?";
    private static final String FIND_BY_EVENT_ORGANIZER = "SELECT * FROM event WHERE organizer=?";

    private final JdbcTemplate jdbcTemplate;

    public EventDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Event.class));
    }

    @Override
    public Optional<Event> findById(Integer id) {
        Optional<Event> event;
        try {
            event = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Event.class), id));
        } catch (EmptyResultDataAccessException e) {
            event = Optional.empty();
        }
        return event;
    }

    @Override
    public int create(Event event) {
        return jdbcTemplate.update(CREATE, event.getOrganizer(), event.getPhoneNumber(), event.getAddress(), event.getDate(),
                                            event.getDuration(), event.getEventInformation(), event.getName());
    }

    @Override
    public int update(Integer id, Event event) {
        return jdbcTemplate.update(UPDATE, event.getOrganizer(), event.getPhoneNumber(), event.getAddress(), event.getDate(),
                                            event.getDuration(), event.getEventInformation(), event.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Event> findAllEventByOrganizer(String organizer) {
        Optional<Event> event;
        try {
            event = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_EVENT_ORGANIZER,
                    BeanPropertyRowMapper.newInstance(Event.class), organizer));
        } catch (EmptyResultDataAccessException e) {
            event = Optional.empty();
        }
        return event;
    }

}
