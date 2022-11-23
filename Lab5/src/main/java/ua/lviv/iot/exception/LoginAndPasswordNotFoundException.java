package ua.lviv.iot.exception;

public class LoginAndPasswordNotFoundException extends RuntimeException {
    public LoginAndPasswordNotFoundException(Integer id) {
        super("Could not find 'login and password' with id = " + id);
    }
}
