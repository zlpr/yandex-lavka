package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;
@Value
public class GroupOrders {
    @JsonProperty("group_order_id")
    Integer groupOrderId;

    List<OrderDto> orders;
}
