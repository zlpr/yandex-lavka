package ru.yandex.yandexlavka.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Limit {
    private float weight;
    private int orders;
    private int regions;
    private int timeFirst;
    private int timeOthers;

}
