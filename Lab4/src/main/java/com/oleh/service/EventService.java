package com.oleh.service;

import com.oleh.domain.Event;

import java.util.Optional;

public interface EventService extends GeneralService<Event, Integer> {

    Optional<Event> findAllEventByOrganizer(String organizer);

}
