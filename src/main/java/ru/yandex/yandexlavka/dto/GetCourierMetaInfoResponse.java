package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Value;

@Value
public class GetCourierMetaInfoResponse {
    @JsonUnwrapped
    CourierDto courier;
    Integer rating;
    Integer earnings;
}
