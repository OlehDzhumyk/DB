package com.oleh.dao;


import com.oleh.domain.Event;
import com.oleh.domain.User;

import java.util.List;


public interface UserDao extends GeneralDao<User, Integer> {

    List<Event> findAllEventsBy(Integer id);

}
