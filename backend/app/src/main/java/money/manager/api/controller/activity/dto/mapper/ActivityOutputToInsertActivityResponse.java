package money.manager.api.controller.activity.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.activity.dto.InsertActivityResponseDto;
import money.manager.service.activity.dto.ActivityOutputDto;

public class ActivityOutputToInsertActivityResponse implements Function<ActivityOutputDto, InsertActivityResponseDto> {

  public static ActivityOutputToInsertActivityResponse build() {
    return new ActivityOutputToInsertActivityResponse();
  }

  @Override
  public InsertActivityResponseDto apply(final ActivityOutputDto input) {
    return new InsertActivityResponseDto(
        input.id(),
        input.description(),
        input.date(),
        input.value(),
        input.type(),
        input.createdAt(),
        input.updatedAt());
  }

}
