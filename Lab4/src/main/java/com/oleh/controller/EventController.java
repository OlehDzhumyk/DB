package com.oleh.controller;

import com.oleh.domain.Event;

import java.util.Optional;

public interface EventController extends GeneralController<Event, Integer> {

    Optional<Event> findAllEventByOrganizer(String organizer);

}
