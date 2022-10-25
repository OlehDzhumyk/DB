package com.oleh.controller.impl;

import com.oleh.controller.LoginAndPasswordController;
import com.oleh.domain.LoginAndPassword;
import com.oleh.service.LoginAndPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class LoginAndPasswordControllerImpl implements LoginAndPasswordController {

    @Autowired
    private LoginAndPasswordService loginAndPasswordService;


    @Override
    public List<LoginAndPassword> findAll() {
        return loginAndPasswordService.findAll();
    }

    @Override
    public Optional<LoginAndPassword> findById(Integer id) {
        return loginAndPasswordService.findById(id);
    }

    @Override
    public int create(LoginAndPassword loginAndPassword) {
        return loginAndPasswordService.create(loginAndPassword);
    }

    @Override
    public int update(Integer id, LoginAndPassword loginAndPassword) {
        return loginAndPasswordService.update(id, loginAndPassword);
    }

    @Override
    public int delete(Integer id) {
        return loginAndPasswordService.delete(id);
    }
}
