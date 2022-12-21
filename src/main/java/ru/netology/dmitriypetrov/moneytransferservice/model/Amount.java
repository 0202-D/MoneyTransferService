package ru.netology.dmitriypetrov.moneytransferservice.model;

import lombok.*;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Amount {
   Integer value;
   String currency;
}
