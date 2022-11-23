package ua.lviv.iot.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.Event;
import ua.lviv.iot.domain.Seats;
import ua.lviv.iot.exception.SeatsNotFoundException;
import ua.lviv.iot.exception.UserNotFoundException;
import ua.lviv.iot.repository.EventRepository;
import ua.lviv.iot.repository.SeatsRepository;
import ua.lviv.iot.service.SeatsService;

import java.util.List;

@Service
public class SeatsServiceImpl implements SeatsService {

    @Autowired
    SeatsRepository seatsRepository;
    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Seats> findAll() {
        return seatsRepository.findAll();
    }

    @Override
    public Seats findById(Integer id) {
        return seatsRepository.findById(id)
                .orElseThrow(() -> new SeatsNotFoundException(id));
    }

    @Override
    public Seats create(Seats seats) {
        seatsRepository.save(seats);
        return seats;
    }

    @Override
    public void update(Integer id, Seats uSeats) {
        Seats seats = seatsRepository.findById(id)
                .orElseThrow(() -> new SeatsNotFoundException(id));
        seats.setSection(uSeats.getSection());
        seats.setNumber(uSeats.getNumber());
        seats.setPrice(uSeats.getPrice());
        seats.setReservationStatus(uSeats.getReservationStatus());

        seatsRepository.save(seats);
    }

    @Override
    public void delete(Integer id) {
        Seats seats = seatsRepository.findById(id)
                .orElseThrow(() -> new SeatsNotFoundException(id));
        seatsRepository.delete(seats);
    }

    @Override
    public List<Seats> findSeatssByEventId(Integer eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new UserNotFoundException(eventId));
        return event.getSeatss();
    }
}
