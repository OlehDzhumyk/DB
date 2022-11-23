package ua.lviv.iot.service;

import ua.lviv.iot.domain.Event;
import ua.lviv.iot.domain.User;

import java.util.List;

public interface UserService extends GeneralService<User, Integer> {
    List<Event> findEventsByUserId(Integer userId);

    void insertTenUsers();
    Integer getUsersIdSum();

}
