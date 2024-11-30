package tech.lastbox.dtos.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskDTO(
                      long id,
                      String name,
                      String description,
                      String lastUpdateObservation,
                      String status,
                      @JsonProperty("created_at")
                      Instant createdAt,
                      @JsonProperty("updated_at")
                      Instant updatedAt
                     ) {}