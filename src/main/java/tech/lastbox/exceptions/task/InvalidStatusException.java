package tech.lastbox.exceptions.task;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException() {
        super("O status informado não está presente na lista de status válidos.");
    }
}
