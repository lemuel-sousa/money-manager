package money.manager.service.activity.dto;

import java.time.Instant;

public record ActivityInputDto(
        String description,
        Instant date,
        float value,
        String type) {
}
