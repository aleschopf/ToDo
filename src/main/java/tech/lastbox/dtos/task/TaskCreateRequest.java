package tech.lastbox.dtos.task;

public record TaskCreateRequest(String name, String description) {
    public TaskCreateRequest {
        description = (description == null) ? "" : description;
    }
}
