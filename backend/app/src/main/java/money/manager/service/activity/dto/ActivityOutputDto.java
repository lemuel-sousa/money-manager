package money.manager.service.activity.dto;

import java.time.Instant;

public record ActivityOutputDto(
                String id,
                String description,
                Instant date,
                float value,
                String type,
                Instant createdAt,
                Instant updatedAt) {
}
