package ru.netology.dmitriypetrov.moneytransferservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.netology.dmitriypetrov.moneytransferservice.dto.OperationRequestDto;
import ru.netology.dmitriypetrov.moneytransferservice.model.Amount;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@Setter
@AllArgsConstructor
public class VerificationCodeDao {
    private Map<String, String> verificationDao;
    private Map <String, OperationRequestDto> operationInformation;

    public VerificationCodeDao() {
        verificationDao = new HashMap<>();
        verificationDao.put("2","123");
        operationInformation = new HashMap<>();
        operationInformation.put("2",new OperationRequestDto("123333333333","888888888888","12/23","123",
                new Amount(5000,"EUR")));
    }
}
