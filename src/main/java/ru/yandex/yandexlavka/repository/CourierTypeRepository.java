package ru.yandex.yandexlavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yandex.yandexlavka.entity.CourierType;
import ru.yandex.yandexlavka.model.ECourierType;

import java.util.Optional;

public interface CourierTypeRepository extends JpaRepository<CourierType, Integer> {
@Query("select c from CourierType c where c.type =:t ")
    Optional<CourierType> readBy(ECourierType t);
}