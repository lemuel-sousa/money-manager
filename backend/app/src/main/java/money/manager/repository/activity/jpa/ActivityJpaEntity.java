package money.manager.repository.activity.jpa;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import money.manager.domain.activity.Activity;
import money.manager.domain.activity.type.ActivityType;

@Entity(name = "Activity")
@Table(name = "activities")
public class ActivityJpaEntity {

  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "descrtiption", nullable = false)
  private String descrtiption;
  @Column(name = "date", nullable = false)
  private Instant date;
  @Column(name = "value", nullable = false)
  private Float value;
  @Column(name = "type", nullable = false)
  private Integer type;
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  public ActivityJpaEntity() {

  }

  private ActivityJpaEntity(final String anId, final String aDescrtiption, final Instant aDate,
      final Float aValue, final Integer aType, final Instant aCreatedAt, final Instant anUpdatedAt) {
    this.id = anId;
    this.descrtiption = aDescrtiption;
    this.date = aDate;
    this.value = aValue;
    this.type = aType;
    this.createdAt = aCreatedAt;
    this.updatedAt = anUpdatedAt;
  }

  public static ActivityJpaEntity from(final Activity anActivity) {
    final var aType = List.of(ActivityType.values())
        .stream()
        .filter(a -> a.equals(anActivity.getType()))
        .findFirst()
        .get()
        .ordinal();

    return new ActivityJpaEntity(
        anActivity.getId(),
        anActivity.getDescription(),
        anActivity.getDate(),
        anActivity.getValue(),
        aType,
        anActivity.getCreatedAt(),
        anActivity.getUpdatedAt());
  }

  public Activity toModel() {
    return Activity.with(
        this.getId(),
        this.getDescrtiption(),
        this.getDate(),
        this.getValue(),
        ActivityType.values()[this.getType()],
        this.getCreatedAt(),
        this.getUpdatedAt());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescrtiption() {
    return descrtiption;
  }

  public void setDescrtiption(String descrtiption) {
    this.descrtiption = descrtiption;
  }

  public Instant getDate() {
    return date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }

  public Float getValue() {
    return value;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
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
