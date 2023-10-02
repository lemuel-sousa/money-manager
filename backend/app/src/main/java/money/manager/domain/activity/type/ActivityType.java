package money.manager.domain.activity.type;

public enum ActivityType {

  REVENUE("revenue"),
  EXPENSE("expense"),
  INVALID_ACTIVITY_TYPE("invalid_activity_type");

  private String type;

  ActivityType(final String aType) {
    this.type = aType;
  }

  public String getType() {
    return type;
  }

}
