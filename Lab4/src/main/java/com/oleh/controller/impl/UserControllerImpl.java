package com.oleh.controller.impl;

import com.oleh.controller.UserController;
import com.oleh.domain.Event;
import com.oleh.domain.User;
import com.oleh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userService.findById(id);
    }

    @Override
    public int create(User user) {
        return userService.create(user);
    }

    @Override
    public int update(Integer id, User user) {
        return userService.update(id, user);
    }

    @Override
    public int delete(Integer id) {
        return userService.delete(id);
    }

    @Override
    public List<Event> findAllEventsBy(Integer id) {
        return userService.findAllEventsBy(id);
    }

}
