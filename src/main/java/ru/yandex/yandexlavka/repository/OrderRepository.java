package ru.yandex.yandexlavka.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yandex.yandexlavka.entity.Order;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Slice<Order> readAllBy(Pageable pageable);

    @Query("select o from Order o where o.id =:orderId and o.courier.id =:courierId")
    Optional<Order> readByIdAndCourierId(Integer orderId, Integer courierId);

    @Query("select count(o) as count from Order o where o.courier.id =:courierId and o.completedTime >=:start and o.completedTime <:end")
    Integer readNumberOfOrdersBy(Integer courierId, LocalDateTime start, LocalDateTime end);
    @Query("select sum(o.cost) as sum from Order o where o.courier.id =:courierId and o.completedTime >=:start and o.completedTime <:end")
    Integer readTotalCostOfOrdersBy(Integer courierId, LocalDateTime start, LocalDateTime end);

    default Optional<Order> readBy(Integer id){
        return this.findById(id);
    }
}