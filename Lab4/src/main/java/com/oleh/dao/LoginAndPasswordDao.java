package com.oleh.dao;


import com.oleh.domain.LoginAndPassword;

import java.util.Optional;

public interface LoginAndPasswordDao extends GeneralDao<LoginAndPassword, Integer> {
    Optional<LoginAndPassword> findByLoginAndPassword(String password);

}
