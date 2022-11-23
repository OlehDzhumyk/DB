package ua.lviv.iot.service;


import ua.lviv.iot.domain.Seats;

import java.util.List;

public interface SeatsService extends GeneralService<Seats, Integer> {
    List<Seats> findSeatssByEventId(Integer eventId);
}
