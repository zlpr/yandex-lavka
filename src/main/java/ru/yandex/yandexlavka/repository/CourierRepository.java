package ru.yandex.yandexlavka.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.entity.Courier;

import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Integer> {
    Slice<Courier> readAllBy(Pageable pageable);

   default Optional<Courier> readBy(Integer courierId){
       return this.findById(courierId);
   }
}
