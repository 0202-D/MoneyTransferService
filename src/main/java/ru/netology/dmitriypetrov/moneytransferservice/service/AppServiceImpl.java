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

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AppServiceImpl implements AppService {
    private final VerificationCodeDao verificationCodeDao;
    private ResponseDto responseDto;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppServiceImpl.class);
    private AtomicInteger operationId = new AtomicInteger(0);
    private AtomicInteger confirmOperationId = new AtomicInteger(0);

    public AppServiceImpl(VerificationCodeDao verificationCodeDao) {
        this.verificationCodeDao = verificationCodeDao;
    }

    public ResponseDto transfer(OperationRequestDto operationRequestDto) {
        operationId.addAndGet(1);
        ResponseDto responseDto = ResponseDto.builder().operationId(operationId.toString()).build();
        verificationCodeDao.save(operationId.toString(), operationRequestDto);
        return responseDto;
    }

    public ResponseDto confirmOperation(RequestVerifyDto dto) {
        String id = dto.getOperationId();
        if (!verificationCodeDao.getOperationInformation().containsKey(id)) {
            throw new ErrorInputException("No such operation with this id");
        }
        // имитация проверки кода верификации с дефолтным значением
        if (!dto.getCode().equals("0000")) {
            throw new ErrorTransferException("Wrong verify code");
        }
        confirmOperationId.addAndGet(1);
        responseDto = ResponseDto.builder().operationId(confirmOperationId.toString()).build();
        LOGGER.info("Transaction from" + " " + verificationCodeDao.getOperationInformation().get(id).getCardFromNumber()
                + " " + "to" + " " + verificationCodeDao.getOperationInformation().get(id).getCardToNumber() + " "
                + "amount" + " " + totalAmount(verificationCodeDao.getOperationInformation().get(id).getAmount().getValue()) + " "
                + verificationCodeDao.getOperationInformation().get(id).getAmount().getCurrency() + " " + "succeed");
        verificationCodeDao.getOperationInformation().remove(id);
        return responseDto;
    }

    public double totalAmount(int sum) {
        return (sum - (double) sum / 100) / 100;
    }

}
