package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class OrderDto{
    @JsonProperty("order_id")
    Integer id;
    Float weight;
    @JsonProperty("regions")
    Integer region;
    Integer cost;
    @JsonProperty("delivery_hours")
    List<String> deliveryHours;
    @JsonProperty("completed_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime completedTime;
}