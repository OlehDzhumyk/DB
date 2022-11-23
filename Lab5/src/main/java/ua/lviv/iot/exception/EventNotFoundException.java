package ua.lviv.iot.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Integer id) {
        super("Could not find 'event' with id = " + id);
    }

}
