package com.oleh.service.impl;

import com.oleh.dao.EventDao;
import com.oleh.domain.Event;
import com.oleh.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public Optional<Event> findAllEventByOrganizer(String organizer) {
        return eventDao.findAllEventByOrganizer(organizer);
    }

    @Override
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return eventDao.findById(id);
    }

    @Override
    public int create(Event event) {
        return eventDao.create(event);
    }

    @Override
    public int update(Integer id, Event event) {
        return eventDao.update(id ,event);
    }

    @Override
    public int delete(Integer id) {
        return eventDao.delete(id);
    }

}
