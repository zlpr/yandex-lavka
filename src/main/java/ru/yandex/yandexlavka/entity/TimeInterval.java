package ru.yandex.yandexlavka.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeInterval {
    @DateTimeFormat
    private LocalTime startTime;

    @DateTimeFormat
    private LocalTime endTime;

    @Override
    public String toString() {
        return startTime + "-" + endTime;
    }
}
