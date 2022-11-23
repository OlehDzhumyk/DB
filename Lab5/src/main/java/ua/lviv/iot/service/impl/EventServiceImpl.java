package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.Event;
import ua.lviv.iot.exception.EventNotFoundException;
import ua.lviv.iot.repository.EventRepository;
import ua.lviv.iot.service.EventService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> findEventByName(String name) {
        return eventRepository.findEventByName(name);
    }

    @Override
    public Integer insertWithProcedure(String name, String phoneNumber, String address) {
        return eventRepository.insertWithProcedure(name, phoneNumber, address);
    }

    @Override
    public void insertIntoTicketMToM(String eventName, String userName) {
        eventRepository.insertIntoTicketMtoM(eventName, userName);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @Transactional
    public Event create(Event event) {
        eventRepository.save(event);
        return event;
    }

    @Transactional
    public void update(Integer id, Event uEvent) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        //update
        event.setPhoneNumber(uEvent.getPhoneNumber());
        event.setAddress(uEvent.getAddress());
        event.setDate(uEvent.getDate());
        event.setName(uEvent.getName());
        eventRepository.save(event);
    }

    @Transactional
    public void delete(Integer id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        eventRepository.delete(event);
    }
}
