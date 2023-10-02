package money.manager.service.activity.dto.mapper;

import java.util.List;
import java.util.function.Function;

import money.manager.domain.activity.Activity;
import money.manager.service.activity.dto.ActivityOutputDto;

public class ListActivitiesToListActivitiesOutputMapper implements Function<List<Activity>, List<ActivityOutputDto>> {

  public static ListActivitiesToListActivitiesOutputMapper build() {
    return new ListActivitiesToListActivitiesOutputMapper();
  }

  @Override
  public List<ActivityOutputDto> apply(List<Activity> input) {
    return input.stream()
        .map(a -> ActivityToActivityOutputDtoMapper.build().apply(a)).toList();
  }

}
