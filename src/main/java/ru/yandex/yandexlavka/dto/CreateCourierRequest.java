package ru.yandex.yandexlavka.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourierRequest {
   @NotEmpty
   private List<@Valid CreateCourierDto> couriers;
}
