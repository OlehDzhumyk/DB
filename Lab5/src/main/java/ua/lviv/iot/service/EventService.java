package ua.lviv.iot.service;


import ua.lviv.iot.domain.Event;

import java.util.List;

public interface EventService extends GeneralService<Event, Integer> {
    List<Event> findEventByName(String name);

    Integer insertWithProcedure(String name, String phoneNumber, String address);

    void insertIntoTicketMToM(String eventName, String userName);
}
