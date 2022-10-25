package com.oleh.service;


import com.oleh.domain.Event;
import com.oleh.domain.User;

import java.util.List;


public interface UserService extends GeneralService<User, Integer> {

    List<Event> findAllEventsBy(Integer id);

}
