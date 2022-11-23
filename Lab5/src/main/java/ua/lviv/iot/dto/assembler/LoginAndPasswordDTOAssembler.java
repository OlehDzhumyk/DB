package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.LoginAndPasswordController;
import ua.lviv.iot.domain.LoginAndPassword;
import ua.lviv.iot.dto.LoginAndPasswordDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LoginAndPasswordDTOAssembler implements RepresentationModelAssembler<LoginAndPassword, LoginAndPasswordDTO> {
    @Override
    public LoginAndPasswordDTO toModel(LoginAndPassword entity) {
        LoginAndPasswordDTO loginAndPasswordDTO = LoginAndPasswordDTO.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .build();
        Link selfLink = linkTo(methodOn(LoginAndPasswordController.class).getLoginAndPassword(Math.toIntExact(loginAndPasswordDTO.getId()))).withSelfRel();
        loginAndPasswordDTO.add(selfLink);
        return loginAndPasswordDTO;
    }

    @Override
    public CollectionModel<LoginAndPasswordDTO> toCollectionModel(Iterable<? extends LoginAndPassword> entities) {
        CollectionModel<LoginAndPasswordDTO> loginAndPasswordDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(LoginAndPasswordController.class).getAllLoginAndPasswords()).withSelfRel();
        loginAndPasswordDTOs.add(selfLink);
        return loginAndPasswordDTOs;
    }

    public CollectionModel<LoginAndPasswordDTO> toCollectionModel(Iterable<? extends LoginAndPassword> entities, Link link) {
        CollectionModel<LoginAndPasswordDTO> loginAndPasswordDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        loginAndPasswordDTOs.add(link);
        return loginAndPasswordDTOs;
    }
}
