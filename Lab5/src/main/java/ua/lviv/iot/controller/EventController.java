package ua.lviv.iot.controller;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Event;
import ua.lviv.iot.dto.EventDTO;
import ua.lviv.iot.dto.UserDTO;
import ua.lviv.iot.dto.assembler.EventDTOAssembler;
import ua.lviv.iot.service.EventService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventDTOAssembler eventDTOAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<EventDTO>> getAllEvents() {
        List<Event> events = eventService.findAll();
        CollectionModel<EventDTO> eventDTOs = eventDTOAssembler.toCollectionModel(events);
        return new ResponseEntity<>(eventDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable Integer eventId) {
        Event event = eventService.findById(eventId);
        EventDTO eventDTO = eventDTOAssembler.toModel(event);
        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<CollectionModel<EventDTO>> getEventByBrand(@PathVariable String name) {
        List<Event> events = eventService.findEventByName(name);
        CollectionModel<EventDTO> eventDTOs = eventDTOAssembler.toCollectionModel(events);
        return new ResponseEntity<>(eventDTOs, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<EventDTO> addEvent(@RequestBody Event event) {
        Event newEvent = eventService.create(event);
        EventDTO eventDTO = eventDTOAssembler.toModel(newEvent);
        return new ResponseEntity<>(eventDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{eventId}")
    public ResponseEntity<?> updateEvent(@RequestBody Event uEvent, @PathVariable Integer eventId) {
        eventService.update(eventId, uEvent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer eventId) {
        eventService.delete(eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/insertWithProcedure")
    public ResponseEntity<EventDTO> insertEventWIthProcedure(@RequestBody EventDTO dtoParam){
        Integer id = eventService.insertWithProcedure(dtoParam.getName(), dtoParam.getPhoneNumber(), dtoParam.getAddress());
        Event entityCreated = eventService.findById(id);
        EventDTO dto = eventDTOAssembler.toModel(entityCreated);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/insertIntoTicketMtoM")
    public ResponseEntity<?> insertIntoTicketMtoM(@RequestBody JSONObject jsonObject){
        try {
            eventService.insertIntoTicketMToM(jsonObject.getAsString("event_name"), jsonObject.getAsString("user_name"));
        } catch (Exception exception) {
            return new ResponseEntity<>("Error with reason: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping(value = "/relationship")
//    public ResponseEntity<?> addDriverHasCarRelationship(@RequestBody JSONObject jsonObject) {
//        driverService.addDriverHasCarRelationship(jsonObject.getAsString("driver_name"), jsonObject.getAsString("car_brand"));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
