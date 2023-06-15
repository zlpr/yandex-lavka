package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CouriersGroupOrders {
    @JsonProperty("courier_id")
    Integer courierId;

    List<GroupOrders> orders;


}
