package tech.lastbox.dtos.task;

import tech.lastbox.util.enums.Status;

public record TaskUpdateRequest(long id, String observation, Status status) {
    public TaskUpdateRequest {
        observation = (observation == null) ? "" : observation;
    }
}
