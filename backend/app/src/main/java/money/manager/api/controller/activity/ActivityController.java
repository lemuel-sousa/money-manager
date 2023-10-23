package money.manager.api.controller.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import money.manager.api.controller.activity.dto.input.InsertActivityRequestDto;
import money.manager.api.controller.activity.dto.mapper.ActivityOutputToInsertActivityResponseMapper;
import money.manager.api.controller.activity.dto.mapper.ActivityRequestToActivityInputMapper;
import money.manager.api.controller.activity.dto.mapper.ListActivitiesServiceToListActivitiesResponseMapper;
import money.manager.api.controller.activity.dto.output.CalculateBalanceResponseDto;
import money.manager.api.controller.activity.dto.output.InsertActivityResponseDto;
import money.manager.api.controller.activity.dto.output.ListActivitiesResponseDto;
import money.manager.repository.activity.ActivityJpaGateway;
import money.manager.repository.activity.jpa.ActivityJpaRepository;
import money.manager.service.activity.implementation.ActivityServiceImpl;

@RestController
@RequestMapping("/activities")
public class ActivityController {

  @Autowired
  private ActivityJpaRepository activityRepository;

  @GetMapping
  public ResponseEntity<ListActivitiesResponseDto> listActivities() {
    final var aGateway = ActivityJpaGateway.build(activityRepository);
    final var aService = ActivityServiceImpl.build(aGateway);

    final var aList = aService.listActivities();
    final var aResponse = ListActivitiesServiceToListActivitiesResponseMapper
        .build().apply(aList);

    return ResponseEntity.ok(aResponse);
  }

  @PostMapping
  public ResponseEntity<InsertActivityResponseDto> insetActivity(
      @RequestBody @Valid InsertActivityRequestDto input) {
    final var aGateway = ActivityJpaGateway.build(activityRepository);
    final var aService = ActivityServiceImpl.build(aGateway);

    final var aServiceInput = ActivityRequestToActivityInputMapper.build().apply(input);
    final var aServiceResponse = aService.insertActivity(aServiceInput);
    final var aResponse = ActivityOutputToInsertActivityResponseMapper.build().apply(aServiceResponse);

    return ResponseEntity.ok(aResponse);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteActivityById(@PathVariable("id") String id) {
    final var aGateway = ActivityJpaGateway.build(activityRepository);
    final var aService = ActivityServiceImpl.build(aGateway);

    aService.removeActivity(id);

    return ResponseEntity.noContent().build();
  }

  @GetMapping("balance")
  public ResponseEntity<CalculateBalanceResponseDto> calculateBalance() {
    final var aGateway = ActivityJpaGateway.build(activityRepository);
    final var aService = ActivityServiceImpl.build(aGateway);

    final var aServiceResponse = aService.calculateBalance();
    final var aResponse = new CalculateBalanceResponseDto(aServiceResponse);

    return ResponseEntity.ok(aResponse);
  }

}
