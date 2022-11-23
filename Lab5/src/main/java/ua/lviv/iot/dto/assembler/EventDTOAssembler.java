package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.EventController;
import ua.lviv.iot.domain.Event;
import ua.lviv.iot.dto.EventDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventDTOAssembler implements RepresentationModelAssembler<Event, EventDTO> {
    @Override
    public EventDTO toModel(Event entity) {
        EventDTO eventDTO = EventDTO.builder()
                .id(entity.getId())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .date(entity.getDate())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(EventController.class).getEvent(Math.toIntExact(eventDTO.getId()))).withSelfRel();
        eventDTO.add(selfLink);
        return eventDTO;
    }

    @Override
    public CollectionModel<EventDTO> toCollectionModel(Iterable<? extends Event> entities) {
        CollectionModel<EventDTO> eventDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(EventController.class).getAllEvents()).withSelfRel();
        eventDTOs.add(selfLink);
        return eventDTOs;
    }

    public CollectionModel<EventDTO> toCollectionModel(Iterable<? extends Event> entities, Link link) {
        CollectionModel<EventDTO> eventDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        eventDTOs.add(link);
        return eventDTOs;
    }
}
