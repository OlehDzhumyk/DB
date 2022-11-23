package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Seats;
import ua.lviv.iot.dto.SeatsDTO;
import ua.lviv.iot.dto.assembler.SeatsDTOAssembler;
import ua.lviv.iot.service.SeatsService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/seatss")
public class SeatsController {
    @Autowired
    private SeatsService seatsService;
    @Autowired
    private SeatsDTOAssembler seatsDTOAssembler;

    @GetMapping(value = "events/{eventId}")
    public ResponseEntity<CollectionModel<SeatsDTO>> getSeatssByEventId(
            @PathVariable Integer eventId) {
        List<Seats> seatss = seatsService.findSeatssByEventId(eventId);
        Link selfLink = linkTo(methodOn(SeatsController.class).getSeatssByEventId(eventId))
                .withSelfRel();
        CollectionModel<SeatsDTO> seatsDTOs = seatsDTOAssembler.
                toCollectionModel(seatss, selfLink);
        return new ResponseEntity<>(seatsDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{seatsId}")
    public ResponseEntity<SeatsDTO> getSeats(@PathVariable Integer seatsId) {
        Seats seats = seatsService.findById(seatsId);
        SeatsDTO seatsDTO = seatsDTOAssembler.toModel(seats);
        return new ResponseEntity<>(seatsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<SeatsDTO>> getAllSeatss() {
        List<Seats> seatss = seatsService.findAll();
        CollectionModel<SeatsDTO> seatsDTOs = seatsDTOAssembler.toCollectionModel(seatss);
        return new ResponseEntity<>(seatsDTOs, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<SeatsDTO> addSeats(@RequestBody Seats seats) {
        Seats newSeats = seatsService.create(seats);
        SeatsDTO seatsDTO = seatsDTOAssembler.toModel(newSeats);
        return new ResponseEntity<>(seatsDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{seatsId}")
    public ResponseEntity<?> updateSeats(@RequestBody Seats uSeats, @PathVariable Integer seatsId) {
        seatsService.update(seatsId, uSeats);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{seatsId}")
    public ResponseEntity<?> deleteSeats(@PathVariable Integer seatsId) {
        seatsService.delete(seatsId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
