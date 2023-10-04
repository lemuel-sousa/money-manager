package money.manager.api.controller.activity.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ActivityResponseDto(
        @JsonProperty String id,
        @JsonProperty String description,
        @JsonProperty Instant date,
        @JsonProperty float value,
        @JsonProperty String type) {
}
