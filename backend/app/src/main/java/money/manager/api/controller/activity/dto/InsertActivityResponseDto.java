package money.manager.api.controller.activity.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InsertActivityResponseDto(
    String id,
    String description,
    Instant date,
    float value,
    String type,
    @JsonProperty("created_at") Instant createdAt,
    @JsonProperty("updated_at") Instant updatedAt) {
}
