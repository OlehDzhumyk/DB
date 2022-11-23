package ua.lviv.iot.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.lviv.iot.exception.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String eventNotFoundHandler(EventNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SeatsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String seatsNotFoundHandler(SeatsNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LoginAndPasswordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String loginAndPasswordNotFoundExceptionNotFoundHandler(LoginAndPasswordNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyHasEventException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userAlreadyHasEventHandler(UserAlreadyHasEventException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserHasNoEventException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userHasNoEventHandler(UserHasNoEventException ex) {
        return ex.getMessage();
    }

}
