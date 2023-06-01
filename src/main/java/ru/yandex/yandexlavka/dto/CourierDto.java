package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.yandex.yandexlavka.model.ECourierType;

import java.util.List;

@Value
public class CourierDto {
    @JsonProperty("courier_id")
    Integer courierId;
    @JsonProperty("courier_type")
    ECourierType courierType;
    @JsonProperty("regions")
    List<Integer> regions;
    @JsonProperty("working_hours")
    List<String> workingHours;
}
