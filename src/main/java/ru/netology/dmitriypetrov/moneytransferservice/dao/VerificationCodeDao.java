package ru.netology.dmitriypetrov.moneytransferservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.netology.dmitriypetrov.moneytransferservice.dto.OperationRequestDto;
import ru.netology.dmitriypetrov.moneytransferservice.model.Amount;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@Setter
@AllArgsConstructor
public class VerificationCodeDao {
    private Map <String, OperationRequestDto> operationInformation;

    public VerificationCodeDao() {
        operationInformation = new HashMap<>();
    }

    public void save (String id,OperationRequestDto value){
        operationInformation.put(id,value);
    }
}
