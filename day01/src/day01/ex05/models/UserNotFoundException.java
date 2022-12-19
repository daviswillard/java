package day01.ex05.models;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("No such user!");
    }
}
