package money.manager.api.controller.activity.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.activity.dto.output.InsertActivityResponseDto;
import money.manager.service.activity.dto.ActivityOutputDto;

public class ActivityOutputToInsertActivityResponseMapper implements Function<ActivityOutputDto, InsertActivityResponseDto> {

  public static ActivityOutputToInsertActivityResponseMapper build() {
    return new ActivityOutputToInsertActivityResponseMapper();
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
