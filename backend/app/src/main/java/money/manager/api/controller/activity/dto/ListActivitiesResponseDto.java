package money.manager.api.controller.activity.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ListActivitiesResponseDto(
                                @JsonProperty List<ActivityResponseDto> activities) {
}
