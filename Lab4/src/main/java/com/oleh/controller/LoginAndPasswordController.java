package com.oleh.controller;


import com.oleh.domain.LoginAndPassword;

import java.util.Optional;

public interface LoginAndPasswordController extends GeneralController<LoginAndPassword, Integer> {

    Optional<LoginAndPassword> findByLoginAndPassword(String password);

}
