package money.manager.api.controller.activity.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;

public record InsertActivityRequestDto(
  @NotBlank(message = "descpription field is mandatory.") String description,
  @NotBlank(message = "date field is mandatory.") Instant date,
  @NotBlank(message = "value field is mandatory.") float value,
  @NotBlank(message = "type field is mandatory.") String type) {
}
