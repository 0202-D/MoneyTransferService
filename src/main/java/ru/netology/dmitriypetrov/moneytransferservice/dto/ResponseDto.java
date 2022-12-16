package ru.netology.dmitriypetrov.moneytransferservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ResponseDto {
    private String operationId;
}
