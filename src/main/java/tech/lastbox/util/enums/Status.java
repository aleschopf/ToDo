package tech.lastbox.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import tech.lastbox.exceptions.task.InvalidStatusException;

public enum Status {
    NOT_STARTED("Not started"),
    IN_PROGRESS("In progress"),
    COMPLETE("Complete"),
    ON_HOLD("On hold"),
    CANCELED("Canceled");


    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    @JsonCreator
    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.name().equalsIgnoreCase(value) || status.description.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new InvalidStatusException();
    }
}
