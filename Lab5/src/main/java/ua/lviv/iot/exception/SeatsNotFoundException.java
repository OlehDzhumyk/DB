package ua.lviv.iot.exception;

public class SeatsNotFoundException extends RuntimeException {
    public SeatsNotFoundException(Integer id) {
        super("Could not find 'seats' with id = " + id);
    }
}
