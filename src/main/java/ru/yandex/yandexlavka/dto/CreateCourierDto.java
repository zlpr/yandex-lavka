package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Value;
import ru.yandex.yandexlavka.model.ECourierType;

import java.util.List;

@Value
public class CreateCourierDto {
    @NotNull
    @JsonProperty("courier_type")
    ECourierType courierType;
    @NotEmpty
    List<Integer> regions;
    @NotEmpty
    @JsonProperty("working_hours")
    List<@Pattern(regexp = "([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]") String> workingHours;
}
