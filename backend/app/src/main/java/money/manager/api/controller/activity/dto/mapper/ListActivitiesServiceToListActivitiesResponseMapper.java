package money.manager.api.controller.activity.dto.mapper;

import java.util.List;
import java.util.function.Function;

import money.manager.api.controller.activity.dto.output.ActivityResponseDto;
import money.manager.api.controller.activity.dto.output.ListActivitiesResponseDto;
import money.manager.service.activity.dto.ActivityOutputDto;

public class ListActivitiesServiceToListActivitiesResponseMapper
        implements Function<List<ActivityOutputDto>, ListActivitiesResponseDto> {

    public static ListActivitiesServiceToListActivitiesResponseMapper build() {
        return new ListActivitiesServiceToListActivitiesResponseMapper();
    }

    @Override
    public ListActivitiesResponseDto apply(List<ActivityOutputDto> input) {
        final var aList = input.stream()
                .map(a -> new ActivityResponseDto(
                        a.id(),
                        a.description(),
                        a.date(),
                        a.value(),
                        a.type()))
                .toList();

        return new ListActivitiesResponseDto(aList);
    }

}
