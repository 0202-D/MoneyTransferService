package ru.netology.dmitriypetrov.moneytransferservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class AppException {
    private String description;
    private Integer id;


}
