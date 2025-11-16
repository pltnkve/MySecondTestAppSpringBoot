package ru.plotnikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.plotnikov.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions position, double salary, double bonus, int workdays) {
        int daysInYear = Year.now().isLeap() ? 366 : 365;
        return salary + bonus * daysInYear * position.getPositionCoefficient() / workdays;
    }
}
