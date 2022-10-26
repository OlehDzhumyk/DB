package com.oleh.controller.impl;

import com.oleh.controller.EventController;
import com.oleh.domain.Event;
import com.oleh.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class EventControllerImpl implements EventController {

    @Autowired
    private EventService eventService;

    @Override
    public Optional<Event> findAllEventByOrganizer(String organizer) {
        return eventService.findAllEventByOrganizer(organizer);
    }

    @Override
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return eventService.findById(id);
    }

    @Override
    public int create(Event event) {
        return eventService.create(event);
    }

    @Override
    public int update(Integer id, Event event) {
        return eventService.update(id ,event);
    }

    @Override
    public int delete(Integer id) {
        return eventService.delete(id);
    }

}
