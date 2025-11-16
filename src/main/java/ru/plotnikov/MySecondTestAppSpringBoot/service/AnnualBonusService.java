package ru.plotnikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.plotnikov.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService {

    double calculate(Positions position, double salary, double bonus, int workDays);
}
