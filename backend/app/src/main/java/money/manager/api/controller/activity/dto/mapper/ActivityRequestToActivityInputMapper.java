package money.manager.api.controller.activity.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.activity.dto.input.InsertActivityRequestDto;
import money.manager.service.activity.dto.ActivityInputDto;

public class ActivityRequestToActivityInputMapper implements Function<InsertActivityRequestDto, ActivityInputDto> {

  public static ActivityRequestToActivityInputMapper build() {
    return new ActivityRequestToActivityInputMapper();
  }

  @Override
  public ActivityInputDto apply(final InsertActivityRequestDto input) {
    return new ActivityInputDto(
        input.description(),
        input.date(),
        input.value(),
        input.type());
  }

}
