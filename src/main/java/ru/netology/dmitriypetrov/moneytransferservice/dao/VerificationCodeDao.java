package ru.netology.dmitriypetrov.moneytransferservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.netology.dmitriypetrov.moneytransferservice.dto.OperationRequestDto;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
@Setter
@AllArgsConstructor
public class VerificationCodeDao {
    private ConcurrentHashMap <String, OperationRequestDto> operationInformation;

    public VerificationCodeDao() {
        operationInformation = new ConcurrentHashMap<>();
    }

    public void save (String id,OperationRequestDto value){
        operationInformation.put(id,value);
    }
}
