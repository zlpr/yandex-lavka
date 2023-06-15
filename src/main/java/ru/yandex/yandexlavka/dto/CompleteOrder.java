package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Value
public class CompleteOrder {
    @NotNull
    @JsonProperty("courier_id")
    Integer courierId;
    @NotNull
    @JsonProperty("order_id")
    Integer orderId;
    @NotNull
    @JsonProperty("complete_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime completedTime;
}
