package ru.netology.dmitriypetrov.moneytransferservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.netology.dmitriypetrov.moneytransferservice.dao.VerificationCodeDao;
import ru.netology.dmitriypetrov.moneytransferservice.dto.OperationRequestDto;
import ru.netology.dmitriypetrov.moneytransferservice.dto.RequestVerifyDto;
import ru.netology.dmitriypetrov.moneytransferservice.dto.ResponseDto;
import ru.netology.dmitriypetrov.moneytransferservice.exception.ErrorInputException;
import ru.netology.dmitriypetrov.moneytransferservice.exception.ErrorTransferException;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AppServiceImpl implements AppService {
    private final VerificationCodeDao verificationCodeDao;
    private ResponseDto responseDto;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppServiceImpl.class);
    private AtomicInteger operationId = new AtomicInteger(0);
    private AtomicInteger confirmOperationId = new AtomicInteger(0);

    public AppServiceImpl(VerificationCodeDao verificationCodeDao, ResponseDto responseDto) {
        this.verificationCodeDao = verificationCodeDao;
        this.responseDto = responseDto;
    }
    public ResponseDto transfer(OperationRequestDto operationRequestDto) {
        operationId.addAndGet(1);
        responseDto.setOperationId(operationId.toString());
        String verifyCode = generateCode();
        verificationCodeDao.getVerificationDao().put(operationId.toString(), verifyCode);
        verificationCodeDao.getOperationInformation().put(operationId.toString(), operationRequestDto);
        return responseDto;
    }

    public ResponseDto confirmOperation(RequestVerifyDto dto) {
        String id = dto.getOperationId();
        if (!verificationCodeDao.getVerificationDao().containsKey(id)) {
            throw new ErrorInputException("No such operation with this id");
        }
        // имитация проверки кода верификации с дефолтным значением
        if(dto.getCode().equals("0000")){
            confirmOperationId.addAndGet(1);
            responseDto.setOperationId(confirmOperationId.toString());
            verificationCodeDao.getVerificationDao().remove(id);
            LOGGER.info("Transaction from"+" "+verificationCodeDao.getOperationInformation().get(id).getCardFromNumber()
                    +" "+"to"+" "+verificationCodeDao.getOperationInformation().get(id).getCardToNumber()+" "
                    +"amount"+" "+verificationCodeDao.getOperationInformation().get(id).getAmount().getValue()+" "+"succeed");
            return responseDto;
        }
        // Код проверки кода верификации (тестировать с помощью postman)
        String code = dto.getCode();
        if (!verificationCodeDao.getVerificationDao().get(id).equals(code)) {
            throw new ErrorTransferException("Bad verification code");
        }
        confirmOperationId.addAndGet(1);
        responseDto.setOperationId(confirmOperationId.toString());
        verificationCodeDao.getVerificationDao().remove(id);
        LOGGER.info("Transaction from"+" "+verificationCodeDao.getOperationInformation().get(id).getCardFromNumber()
        +" "+"to"+" "+verificationCodeDao.getOperationInformation().get(id).getCardToNumber()+" "
        +"amount"+" "+verificationCodeDao.getOperationInformation().get(id).getAmount().getValue()+" "+"succeed");

        return responseDto;
    }

    public String generateCode() {
        Random rand = new Random();
        int randomNum = rand.nextInt((1000000 - 35) + 1) + 75;
        return Integer.toString(randomNum);
    }
}
