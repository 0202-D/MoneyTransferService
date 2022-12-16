package ru.netology.dmitriypetrov.moneytransferservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@Setter
@AllArgsConstructor
public class VerificationCodeDao {
    private Map<String, String> verificationDao;

    public VerificationCodeDao() {
        verificationDao = new HashMap<>();
    }
}
