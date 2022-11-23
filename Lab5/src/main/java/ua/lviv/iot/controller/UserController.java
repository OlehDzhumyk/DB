package ua.lviv.iot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Event;
import ua.lviv.iot.domain.User;
import ua.lviv.iot.dto.EventDTO;
import ua.lviv.iot.dto.UserDTO;
import ua.lviv.iot.dto.assembler.EventDTOAssembler;
import ua.lviv.iot.dto.assembler.UserDTOAssembler;
import ua.lviv.iot.service.UserService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDTOAssembler userDTOAssembler;
    @Autowired
    private EventDTOAssembler eventDTOAssembler;

    @GetMapping(value = "/{userId}/events")
    public ResponseEntity<CollectionModel<EventDTO>> getAllEventsForUser(@PathVariable Integer userId) {
        List<Event> events = userService.findEventsByUserId(userId);
        Link selfLink = linkTo(methodOn(UserController.class).getAllEventsForUser(userId)).withSelfRel();
        CollectionModel<EventDTO> eventDTOs = eventDTOAssembler.toCollectionModel(events, selfLink);
        return new ResponseEntity<>(eventDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<UserDTO>> getAllUsers() {
        List<User> users = userService.findAll();
        CollectionModel<UserDTO> userDTOs = userDTOAssembler.toCollectionModel(users);
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        UserDTO userDTO = userDTOAssembler.toModel(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        User newUser = userService.create(user);
        UserDTO userDTO = userDTOAssembler.toModel(newUser);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User uUser, @PathVariable Integer userId) {
        userService.update(userId, uUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/insertTenUsers")
    public ResponseEntity<?> insertTenCompanies() {
        userService.insertTenUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getIdsSum")
    public ResponseEntity<Integer> getIdsSum() {
        return new ResponseEntity<>(userService.getUsersIdSum(), HttpStatus.OK);
    }

}
