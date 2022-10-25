package com.oleh.controller;


import com.oleh.domain.Event;
import com.oleh.domain.User;

import java.util.List;


public interface UserController extends GeneralController<User, Integer> {

    List<Event> findAllEventsBy(Integer id);

}
