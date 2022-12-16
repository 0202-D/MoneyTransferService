package ru.netology.dmitriypetrov.moneytransferservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Card {
    private String cardFromNumber;
    private String cardFromValidTill;
    private String cardFromCVV;
}
