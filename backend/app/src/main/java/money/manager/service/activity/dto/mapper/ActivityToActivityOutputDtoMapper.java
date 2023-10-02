package money.manager.service.activity.dto.mapper;

import java.util.function.Function;

import money.manager.domain.activity.Activity;
import money.manager.service.activity.dto.ActivityOutputDto;

public class ActivityToActivityOutputDtoMapper implements Function<Activity, ActivityOutputDto> {

  public static ActivityToActivityOutputDtoMapper build() {
    return new ActivityToActivityOutputDtoMapper();
  }

  @Override
  public ActivityOutputDto apply(Activity input) {

    return new ActivityOutputDto(
        input.getId(),
        input.getDescription(),
        input.getDate(),
        input.getValue(),
        input.getType().getType(),
        input.getCreatedAt(),
        input.getUpdatedAt());
  }

}
