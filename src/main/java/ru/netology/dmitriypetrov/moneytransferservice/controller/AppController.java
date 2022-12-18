package ru.netology.dmitriypetrov.moneytransferservice.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.netology.dmitriypetrov.moneytransferservice.dto.OperationRequestDto;
import ru.netology.dmitriypetrov.moneytransferservice.dto.RequestVerifyDto;
import ru.netology.dmitriypetrov.moneytransferservice.dto.ResponseDto;
import ru.netology.dmitriypetrov.moneytransferservice.service.AppServiceImpl;

@RestController
@RequestMapping("/")
public class AppController {
    private AppServiceImpl appService;

    public AppController(AppServiceImpl appService) {
        this.appService = appService;
    }

    @PostMapping("/transfer")
    public ResponseDto transfer(@RequestBody @Valid OperationRequestDto operationRequestDto) {
        return appService.transfer(operationRequestDto);


    }

    @PostMapping("/confirmOperation")
    public ResponseDto confirmOperation(@RequestBody @Valid RequestVerifyDto dto) {
        return appService.confirmOperation(dto);
    }
}
