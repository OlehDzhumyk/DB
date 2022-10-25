package com.oleh.dao;

import com.oleh.domain.Event;

import java.util.Optional;

public interface EventDao extends GeneralDao<Event, Integer> {
    Optional<Event> findAllEventByOrganizer(String organizer);

}
