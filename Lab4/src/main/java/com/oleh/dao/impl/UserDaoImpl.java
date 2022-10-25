package com.oleh.dao.impl;

import com.oleh.dao.UserDao;
import com.oleh.domain.Event;
import com.oleh.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class UserDaoImpl implements UserDao {
    private static final String FIND_ALL = "SELECT * FROM user";
    private static final String CREATE = "INSERT user(name, second_name, phone, email, city, region, login_and_password_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET name=?, second_name=?, phone=?, email=?, city=?, region=?, login_and_password_id=? where id=?";
    private static final String DELETE = "DELETE FROM user WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String FIND_ALL_EVENTS = "SELECT * FROM event WHERE EXISTS (SELECT * FROM ticket WHERE event_id = id and user_id=?)";

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(User.class), id));
        } catch (EmptyResultDataAccessException e) {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public int create(User user) {
        return jdbcTemplate.update(CREATE, user.getName(), user.getSecondName(),
                                        user.getPhoneNumber(), user.getEmail(), user.getCity(), user.getRegion(), user.getLoginAndPasswordId());
    }

    @Override
    public int update(Integer id, User user) {
        return jdbcTemplate.update(UPDATE,  user.getName(), user.getSecondName(),
                user.getPhoneNumber(), user.getEmail(), user.getCity(), user.getRegion(), user.getLoginAndPasswordId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Event> findAllEventsBy(Integer id) {
        return jdbcTemplate.query(FIND_ALL_EVENTS, BeanPropertyRowMapper.newInstance(Event.class), id);
    }

}
