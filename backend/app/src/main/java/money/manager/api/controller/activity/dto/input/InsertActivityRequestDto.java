package money.manager.api.controller.activity.dto.input;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;

public record InsertActivityRequestDto(
    @NotBlank(message = "descpription field is mandatory.") String description,
    Instant date,
    float value,
    @NotBlank(message = "type field is mandatory.") String type) {
}
