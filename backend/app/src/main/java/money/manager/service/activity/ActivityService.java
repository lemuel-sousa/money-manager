package money.manager.service.activity;

import java.util.List;

import money.manager.service.activity.dto.ActivityInputDto;
import money.manager.service.activity.dto.ActivityOutputDto;

public interface ActivityService {

  public ActivityOutputDto insertActivity(final ActivityInputDto anInput);

  public void removeActivity(final String anId);

  public List<ActivityOutputDto> listActivities();

  public float calculateBalance();

}
