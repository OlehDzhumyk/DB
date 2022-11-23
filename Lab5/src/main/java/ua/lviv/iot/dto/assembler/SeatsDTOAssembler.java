package ua.lviv.iot.dto.assembler;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.SeatsController;
import ua.lviv.iot.domain.Seats;
import ua.lviv.iot.dto.SeatsDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SeatsDTOAssembler implements RepresentationModelAssembler<Seats, SeatsDTO> {
    @Override
    public SeatsDTO toModel(Seats entity) {
        SeatsDTO seatsDTO = SeatsDTO.builder()
                .id(entity.getId())
                .section(entity.getSection())
                .number(entity.getNumber())
                .price(entity.getPrice())
                .reservationStatus(entity.getReservationStatus())
                .build();
        Link selfLink = linkTo(methodOn(SeatsController.class).getSeats(Math.toIntExact(seatsDTO.getId()))).withSelfRel();
        seatsDTO.add(selfLink);
//        Link carLink = linkTo(methodOn(SeatsController.class).getAllCarsForSeats(Math.toIntExact(entity.getId()))).withRel("cars");
//        seatsDTO.add(carLink);
        return seatsDTO;
    }

    @Override
    public CollectionModel<SeatsDTO> toCollectionModel(Iterable<? extends Seats> entities) {
        CollectionModel<SeatsDTO> seatsDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(SeatsController.class).getAllSeatss()).withSelfRel();
        seatsDTOs.add(selfLink);
        return seatsDTOs;
    }

    public CollectionModel<SeatsDTO> toCollectionModel(Iterable<? extends Seats> entities, Link link) {
        CollectionModel<SeatsDTO> seatsDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        seatsDTOs.add(link);
        return seatsDTOs;
    }
}