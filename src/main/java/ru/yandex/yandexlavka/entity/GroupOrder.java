package ru.yandex.yandexlavka.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupOrder {
    @Id
    private Integer id;
    private LocalDate deliveryDate;
    private Limit limit;
    @ManyToOne(fetch = FetchType.LAZY)
    private Courier courier;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupOrder")
    private List<Order> orders;

}
