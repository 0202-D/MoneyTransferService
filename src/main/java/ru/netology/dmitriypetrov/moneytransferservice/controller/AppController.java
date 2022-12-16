package ru.netology.dmitriypetrov.moneytransferservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.dmitriypetrov.moneytransferservice.dto.RequestDto;
import ru.netology.dmitriypetrov.moneytransferservice.dto.ResponseDto;
import ru.netology.dmitriypetrov.moneytransferservice.service.AppService;

@RestController
@RequestMapping("/")
public class AppController {
    @Autowired
    AppService appService;

    @PostMapping("/transfer")
    public ResponseDto transfer(@RequestBody RequestDto requestDto) {
        System.out.println(requestDto);
        ResponseDto responseDto= appService.transfer();
        System.out.println(responseDto);
        return responseDto;

    }

    @PostMapping("/confirmOperation")
    public ResponseDto confirmOperation() {
        return null;
    }
}
