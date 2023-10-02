package money.manager.service.implementation;

import java.util.List;

import money.manager.domain.activity.type.ActivityType;
import money.manager.domain.gateway.ActivityGateway;
import money.manager.service.activity.ActivityService;
import money.manager.service.activity.dto.ActivityInputDto;
import money.manager.service.activity.dto.ActivityOutputDto;
import money.manager.service.activity.dto.mapper.ActivityInputToActivityMapper;
import money.manager.service.activity.dto.mapper.ActivityToActivityOutputDtoMapper;
import money.manager.service.activity.dto.mapper.ListActivitiesToListActivitiesOutputMapper;

public class ActivityServiceImpl implements ActivityService {

  private ActivityGateway activityGateway;

  private ActivityServiceImpl(final ActivityGateway aGateway) {
    this.activityGateway = aGateway;
  }

  @Override
  public ActivityOutputDto insertActivity(final ActivityInputDto anInput) {
    final var anActivity = ActivityInputToActivityMapper.build()
        .apply(anInput);

    this.activityGateway.create(anActivity);

    return ActivityToActivityOutputDtoMapper.build().apply(anActivity);
  }

  @Override
  public void removeActivity(final String anId) {
    this.activityGateway.delete(anId);
  }

  @Override
  public List<ActivityOutputDto> listActivities() {
    final var aList = this.activityGateway.findAll();

    return ListActivitiesToListActivitiesOutputMapper.build().apply(aList);
  }

  @Override
  public float calculateBalance() {
    final var aList = this.activityGateway.findAll();

    if (aList == null || aList.size() == 0) {
      return 0;
    }

    return (float) aList.stream()
        .mapToDouble(a -> a.getType() == ActivityType.REVENUE
            ? a.getValue()
            : -a.getValue())
        .sum();
  }

}
