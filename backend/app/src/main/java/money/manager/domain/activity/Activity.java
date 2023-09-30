package money.manager.domain.activity;

import java.time.Instant;

import money.manager.domain.exception.DomainException;
import money.manager.domain.type.ActivityType;

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
    if (this.value < 0.01) {
      throw new DomainException("Activity's value must not be less than 0.01.");
    }
    if (this.type != ActivityType.REVENUE && this.type != ActivityType.EXPENSE) {
      throw new DomainException("Activity's type should be reveneu or expense.");
    }
  }

}
