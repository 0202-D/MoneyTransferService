package ru.netology.dmitriypetrov.moneytransferservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestVerifyDto {
    @NotNull(message = "Поле 'operationID' не должно быть null")
    @NotBlank(message = "Поле 'operationID' не должно быть пустым")
    private String operationId;
    @NotNull(message = "Поле 'code' не должно быть null")
    @NotBlank(message = "Поле 'code' не должно быть пустым")
    private String code;

}
