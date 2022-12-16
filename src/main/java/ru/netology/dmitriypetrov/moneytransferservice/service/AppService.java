package ru.netology.dmitriypetrov.moneytransferservice.service;

import org.springframework.stereotype.Service;
import ru.netology.dmitriypetrov.moneytransferservice.dao.VerificationCodeDao;
import ru.netology.dmitriypetrov.moneytransferservice.dto.ResponseDto;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AppService {
    final
    VerificationCodeDao verificationCodeDao;

    private AtomicInteger id = new AtomicInteger(0);

    public AppService(VerificationCodeDao verificationCodeDao) {
        this.verificationCodeDao = verificationCodeDao;
    }

    public ResponseDto transfer() {
        ResponseDto responseDto = new ResponseDto();
        id.addAndGet(1);
        responseDto.setOperationId(id.toString());
        verificationCodeDao.getVerificationDao().put(id.toString(), generateCode());
        System.out.println(verificationCodeDao.getVerificationDao());
        return responseDto;
    }

    public ResponseDto confirmOperation() {
        return null;
    }

    public String generateCode() {
        Random rand = new Random();
        int randomNum = rand.nextInt((1000000 - 35) + 1) + 75;
        return Integer.toString(randomNum);
    }
}
