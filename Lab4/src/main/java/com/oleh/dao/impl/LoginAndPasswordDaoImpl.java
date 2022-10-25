package com.oleh.dao.impl;

import com.oleh.dao.LoginAndPasswordDao;
import com.oleh.domain.LoginAndPassword;
import com.oleh.domain.Seats;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class LoginAndPasswordDaoImpl implements LoginAndPasswordDao {
    private static final String FIND_ALL = "SELECT * FROM login_and_password";
    private static final String CREATE = "INSERT login_and_password(login, password) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE login_and_password SET login=?, password=? WHERE id=?";
    private static final String DELETE = "DELETE FROM login_and_password WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM login_and_password WHERE id=?";

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM login_and_password WHERE login=?";

    private final JdbcTemplate jdbcTemplate;

    public LoginAndPasswordDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<LoginAndPassword> findAll() {
        return jdbcTemplate.query(FIND_ALL,
                BeanPropertyRowMapper.newInstance(LoginAndPassword.class));
    }

    @Override
    public Optional<LoginAndPassword> findById(Integer id) {
        Optional<LoginAndPassword> loginAndPassword;
        try {
            loginAndPassword = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(LoginAndPassword.class), id));
        } catch (EmptyResultDataAccessException e) {
            loginAndPassword = Optional.empty();
        }
        return loginAndPassword;
    }

    @Override
    public int create(LoginAndPassword loginAndPassword) {
        return jdbcTemplate.update(CREATE, loginAndPassword.getLogin(), loginAndPassword.getPassword());
    }

    @Override
    public int update(Integer id, LoginAndPassword loginAndPassword) {
        return jdbcTemplate.update(UPDATE, loginAndPassword.getLogin(), loginAndPassword.getPassword(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<LoginAndPassword> findByLoginAndPassword(String password) {
        Optional<LoginAndPassword> loginAndPassword;
        try {
            loginAndPassword = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_LOGIN_AND_PASSWORD,
                    BeanPropertyRowMapper.newInstance(LoginAndPassword.class), password));
        } catch (EmptyResultDataAccessException e) {
            loginAndPassword = Optional.empty();
        }
        return loginAndPassword;
    }
}
