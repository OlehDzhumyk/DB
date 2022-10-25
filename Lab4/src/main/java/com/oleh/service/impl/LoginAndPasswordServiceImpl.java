package com.oleh.service.impl;

import com.oleh.dao.EventDao;
import com.oleh.dao.LoginAndPasswordDao;
import com.oleh.domain.LoginAndPassword;
import com.oleh.service.LoginAndPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class LoginAndPasswordServiceImpl implements LoginAndPasswordService {

    @Autowired
    private LoginAndPasswordDao loginAndPasswordDao;


    @Override
    public List<LoginAndPassword> findAll() {
        return loginAndPasswordDao.findAll();
    }

    @Override
    public Optional<LoginAndPassword> findById(Integer id) {
        return loginAndPasswordDao.findById(id);
    }

    @Override
    public int create(LoginAndPassword loginAndPassword) {
        return loginAndPasswordDao.create(loginAndPassword);
    }

    @Override
    public int update(Integer id, LoginAndPassword loginAndPassword) {
        return loginAndPasswordDao.update(id, loginAndPassword);
    }

    @Override
    public int delete(Integer id) {
        return loginAndPasswordDao.delete(id);
    }


    @Override
    public Optional<LoginAndPassword> findByLoginAndPassword(String password) {
        return loginAndPasswordDao.findByLoginAndPassword(password);
    }
}
