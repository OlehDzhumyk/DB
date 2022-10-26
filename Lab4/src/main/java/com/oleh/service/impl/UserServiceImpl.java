package com.oleh.service.impl;

import com.oleh.dao.UserDao;
import com.oleh.domain.Event;
import com.oleh.domain.User;
import com.oleh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public int create(User user) {
        return userDao.create(user);
    }

    @Override
    public int update(Integer id, User user) {
        return userDao.update(id, user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }

    @Override
    public List<Event> findAllEventsBy(Integer id) {
        return userDao.findAllEventsBy(id);
    }

}
