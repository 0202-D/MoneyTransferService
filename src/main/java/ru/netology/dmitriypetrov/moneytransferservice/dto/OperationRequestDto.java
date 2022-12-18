package ru.netology.dmitriypetrov.moneytransferservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;
import ru.netology.dmitriypetrov.moneytransferservice.model.Amount;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OperationRequestDto {
    @NotNull(message = "Необходимо ввести номер карты")
    @Size(min = 16, message = "Номер карты должен состоять из 16-и символов")
    private String cardFromNumber;

    @NotNull(message = "Необходимо ввести номер карты")
    @Size(min = 16, message = "Номер карты должен состоять из 16-и символов")
    private String cardToNumber;

    @NotNull(message = "Необходимо ввести срок действия карты")
    @Size(min = 4, message = "Срок действия карты должен состоять из 4-х символов")
    private String cardFromValidTill;

    @NotNull(message = "Необходимо ввести CVV")
    @Size(min = 3, message = "CVV должен состоять из 3-х символов")
    private String cardFromCVV;

    private Amount amount;
}
