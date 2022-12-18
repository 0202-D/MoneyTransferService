package ru.netology.dmitriypetrov.moneytransferservice.service;

import ru.netology.dmitriypetrov.moneytransferservice.dto.OperationRequestDto;
import ru.netology.dmitriypetrov.moneytransferservice.dto.RequestVerifyDto;
import ru.netology.dmitriypetrov.moneytransferservice.dto.ResponseDto;

public interface AppService {
    ResponseDto transfer(OperationRequestDto operationRequestDto);
    public ResponseDto confirmOperation(RequestVerifyDto dto);
}
