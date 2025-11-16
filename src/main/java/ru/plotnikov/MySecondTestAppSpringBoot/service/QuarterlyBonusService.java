package ru.plotnikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.plotnikov.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface QuarterlyBonusService {

    double calculate(Positions position, double bonus);
}
