package ru.yandex.yandexlavka.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.yandexlavka.model.EOrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float weight;
    private Region region;
    private Integer cost;
    private LocalDateTime completedTime;
    private EOrderStatus status = EOrderStatus.CREATED;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="deliveryHours")
    private List<TimeInterval> deliveryHours = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Courier courier;
    @ManyToOne(fetch = FetchType.LAZY)
    private GroupOrder groupOrder;
}
