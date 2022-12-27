package ru.netology.dmitriypetrov.moneytransferservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ResponseDto {
    private String operationId;
}
