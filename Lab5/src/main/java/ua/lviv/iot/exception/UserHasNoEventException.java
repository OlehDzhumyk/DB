package ua.lviv.iot.exception;

public class UserHasNoEventException extends RuntimeException {
    public UserHasNoEventException(Integer eventId, Integer userId){
        super("'User' with id=" + userId +  " doesn't have 'event' with id=" + eventId);
    }
}
