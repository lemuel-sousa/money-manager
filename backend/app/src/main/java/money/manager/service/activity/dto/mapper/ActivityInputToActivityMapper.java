package money.manager.service.activity.dto.mapper;

import java.util.function.Function;

import money.manager.domain.activity.Activity;
import money.manager.domain.activity.type.ActivityType;
import money.manager.service.activity.dto.ActivityInputDto;
import money.manager.service.exception.ServiceException;

public class ActivityInputToActivityMapper implements Function<ActivityInputDto, Activity> {

  public static ActivityInputToActivityMapper build() {
    return new ActivityInputToActivityMapper();
  }

  @Override
  public Activity apply(final ActivityInputDto input) {

    if (input.type().equals(ActivityType.REVENUE.toString().toLowerCase())) {

      return Activity.newActivity(
          input.description(),
          input.date(),
          input.value(),
          ActivityType.REVENUE);

    } else if (input.type().equals(ActivityType.EXPENSE.toString().toLowerCase())) {

      return Activity.newActivity(
          input.description(),
          input.date(),
          input.value(),
          ActivityType.EXPENSE);
    } else {
      throw new ServiceException("Invalid activity type");
    }

  }

}
