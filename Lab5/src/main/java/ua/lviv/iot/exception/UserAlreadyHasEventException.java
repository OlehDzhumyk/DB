package ua.lviv.iot.exception;

public class UserAlreadyHasEventException extends RuntimeException {
    public UserAlreadyHasEventException(Integer eventId, Integer userId){
        super("'User' with id=" + userId +  " already has 'seats' with id=" + eventId);
    }
}
