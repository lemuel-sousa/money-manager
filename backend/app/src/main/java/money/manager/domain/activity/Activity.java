package money.manager.domain.activity;

import java.time.Instant;
import money.manager.domain.activity.type.ActivityType;
import money.manager.domain.exception.DomainException;
import money.manager.utils.InstantUtils;
import money.manager.utils.UUIDUtils;

public class Activity {

  private String id;
  private String description;
  private Instant date;
  private float value;
  private ActivityType type;
  private Instant createdAt;
  private Instant updatedAt;

  private Activity(final String anId, final String aDescription,
      final Instant aDate, final float aValue, final ActivityType aType,
      final Instant aCreatedAt, final Instant anUpdatedAt) {

    this.id = anId;
    this.description = aDescription;
    this.date = aDate;
    this.value = aValue;
    this.type = aType;
    this.createdAt = aCreatedAt;
    this.updatedAt = anUpdatedAt;

    this.validate();
  }

  public static Activity newActivity(final String aDescription, final Instant aDate, final float aValue,
      final ActivityType aType) {

    return new Activity(
        UUIDUtils.generate(),
        aDescription,
        aDate,
        aValue,
        aType,
        InstantUtils.now(),
        InstantUtils.now());
  }

  public static Activity with(final String anId, final String aDescription,
      final Instant aDate, final float aValue, final ActivityType aType,
      final Instant aCreatedAt, final Instant anUpdatedAt) {

    return new Activity(anId, aDescription, aDate, aValue, aType, aCreatedAt, anUpdatedAt);
  }

  private void validate() {
    if (this.id.isBlank()) {
      throw new DomainException("Activity's ID should not be blank.");
    }
    if (this.id.length() != 36) {
      throw new DomainException("Activity's should have a valid UUID.");
    }
    if (this.description.isBlank()) {
      throw new DomainException("Activity's description should not be blank.");
    }
    if (this.description.length() < 3) {
      throw new DomainException("Activity's description must have more than 3 characters.");
    }
    if (this.type != ActivityType.REVENUE && this.type != ActivityType.EXPENSE) {
      throw new DomainException("Activity's type should be reveneu or expense.");
    }
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Instant getDate() {
    return date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }

  public ActivityType getType() {
    return type;
  }

  public void setType(ActivityType type) {
    this.type = type;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }
}
