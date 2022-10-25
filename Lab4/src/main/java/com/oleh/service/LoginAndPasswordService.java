package com.oleh.service;


import com.oleh.domain.LoginAndPassword;

import java.util.Optional;

public interface LoginAndPasswordService extends GeneralService<LoginAndPassword, Integer> {
    Optional<LoginAndPassword> findByLoginAndPassword(String password);

}
