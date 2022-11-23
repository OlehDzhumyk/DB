package ua.lviv.iot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.LoginAndPassword;
import ua.lviv.iot.dto.LoginAndPasswordDTO;
import ua.lviv.iot.dto.assembler.LoginAndPasswordDTOAssembler;
import ua.lviv.iot.service.LoginAndPasswordService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/api/loginAndPasswords")
public class LoginAndPasswordController {
    @Autowired
    private LoginAndPasswordService loginAndPasswordService;
    @Autowired
    private LoginAndPasswordDTOAssembler loginAndPasswordDTOAssembler;


    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<LoginAndPasswordDTO>> getAllLoginAndPasswords() {
        List<LoginAndPassword> loginAndPasswords = loginAndPasswordService.findAll();
        CollectionModel<LoginAndPasswordDTO> loginAndPasswordDTOs = loginAndPasswordDTOAssembler.toCollectionModel(loginAndPasswords);
        return new ResponseEntity<>(loginAndPasswordDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{loginAndPasswordId}")
    public ResponseEntity<LoginAndPasswordDTO> getLoginAndPassword(@PathVariable Integer loginAndPasswordId) {
        LoginAndPassword loginAndPassword = loginAndPasswordService.findById(loginAndPasswordId);
        LoginAndPasswordDTO loginAndPasswordDTO = loginAndPasswordDTOAssembler.toModel(loginAndPassword);
        return new ResponseEntity<>(loginAndPasswordDTO, HttpStatus.OK);
    }

//    @GetMapping(value = "drivers/{driverId}")
//    public ResponseEntity<CollectionModel<LoginAndPasswordDTO>> getLoginAndPasswordsByDriverId(
//            @PathVariable Integer driverId) {
//        List<LoginAndPassword> loginAndPasswords = loginAndPasswordService.findLoginAndPasswordsByDriverId(driverId);
//        Link selfLink = linkTo(methodOn(LoginAndPasswordController.class).getLoginAndPasswordsByDriverId(driverId))
//                .withSelfRel();
//        CollectionModel<LoginAndPasswordDTO> loginAndPasswordDTOs = loginAndPasswordDTOAssembler.
//                toCollectionModel(loginAndPasswords, selfLink);
//        return new ResponseEntity<>(loginAndPasswordDTOs, HttpStatus.OK);
//    }

    @PostMapping(value = "")
    public ResponseEntity<LoginAndPasswordDTO> addLoginAndPassword(@RequestBody LoginAndPassword loginAndPassword) {
        LoginAndPassword newLoginAndPassword = loginAndPasswordService.create(loginAndPassword);
        LoginAndPasswordDTO loginAndPasswordDTO = loginAndPasswordDTOAssembler.toModel(newLoginAndPassword);
        return new ResponseEntity<>(loginAndPasswordDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{loginAndPasswordId}")
    public ResponseEntity<?> updateLoginAndPassword(@RequestBody LoginAndPassword uLoginAndPassword, @PathVariable Integer loginAndPasswordId) {
        loginAndPasswordService.update(loginAndPasswordId, uLoginAndPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{loginAndPasswordId}")
    public ResponseEntity<?> deleteLoginAndPassword(@PathVariable Integer loginAndPasswordId) {
        loginAndPasswordService.delete(loginAndPasswordId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
