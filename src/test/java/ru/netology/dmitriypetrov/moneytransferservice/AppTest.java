package ru.netology.dmitriypetrov.moneytransferservice;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.netology.dmitriypetrov.moneytransferservice.controller.AppController;
import ru.netology.dmitriypetrov.moneytransferservice.dao.VerificationCodeDao;
import ru.netology.dmitriypetrov.moneytransferservice.dto.OperationRequestDto;
import ru.netology.dmitriypetrov.moneytransferservice.dto.RequestVerifyDto;
import ru.netology.dmitriypetrov.moneytransferservice.dto.ResponseDto;
import ru.netology.dmitriypetrov.moneytransferservice.exception.ErrorInputException;
import ru.netology.dmitriypetrov.moneytransferservice.exception.ErrorTransferException;
import ru.netology.dmitriypetrov.moneytransferservice.model.Amount;
import ru.netology.dmitriypetrov.moneytransferservice.service.AppServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppTest {
    @Autowired
    private AppServiceImpl appService;
    @Autowired
    AppController appController;
    @Autowired
    VerificationCodeDao dao;

    OperationRequestDto request = new OperationRequestDto(
            "111111111111111",
            "1212121212345678",
            "06/24",
            "123",
            new Amount(10000, "RUB")
    );

    @Test
    void shouldBePassed() {
        ResponseDto expected = new ResponseDto("1");
        ResponseDto dto = appService.transfer(request);
        Assert.assertEquals(expected, dto);
    }
    @Test
    void shouldBePassedTransfer() throws ErrorInputException {
        dao.getOperationInformation().put("5",request);
        RequestVerifyDto req = new RequestVerifyDto("5","0000");
        ResponseDto actual = appService.confirmOperation(req);
        ResponseDto expected = new ResponseDto("1");
        Assert.assertEquals(expected,actual);
    }
    @Test
    void throwException() throws ErrorInputException {
        dao.getOperationInformation().put("1",new OperationRequestDto());
        String exceptionMessageExpected = "No such operation with this id";
        ErrorInputException thrown = Assert.assertThrows(ErrorInputException.class, () -> appService
                .confirmOperation(new RequestVerifyDto("2", "1111")));
        Assert.assertEquals(exceptionMessageExpected, thrown.getMessage());
    }
    @Test
    void throwExceptionCode() throws ErrorTransferException {
        dao.getOperationInformation().put("1",new OperationRequestDto());
        String exceptionMessageExpected = "Wrong verify code";
        ErrorTransferException thrown = Assert.assertThrows(ErrorTransferException.class, () -> appService
                .confirmOperation(new RequestVerifyDto("1", "1111")));
        Assert.assertEquals(exceptionMessageExpected, thrown.getMessage());
    }
}
