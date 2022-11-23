package ua.lviv.iot.dto.assembler;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.UserController;
import ua.lviv.iot.domain.User;
import ua.lviv.iot.dto.UserDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserDTOAssembler implements RepresentationModelAssembler<User, UserDTO> {

    @Override
    public UserDTO toModel(User entity) {
        UserDTO userDTO = UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .secondName(entity.getSecondName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .build();
        Link selfLink = linkTo(methodOn(UserController.class).getUser(Math.toIntExact(userDTO.getId()))).withSelfRel();
        userDTO.add(selfLink);
        Link eventLink = linkTo(methodOn(UserController.class).getAllEventsForUser(Math.toIntExact(entity.getId()))).withRel("events");
        userDTO.add(eventLink);
        return userDTO;
    }

    @Override
    public CollectionModel<UserDTO> toCollectionModel(Iterable<? extends User> entities) {
        CollectionModel<UserDTO> userDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();
        userDTOs.add(selfLink);
        return userDTOs;
    }
}
