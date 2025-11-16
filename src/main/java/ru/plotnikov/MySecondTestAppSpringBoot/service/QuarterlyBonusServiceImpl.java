package ru.plotnikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.plotnikov.MySecondTestAppSpringBoot.model.Positions;


@Service
public class QuarterlyBonusServiceImpl implements QuarterlyBonusService {

    @Override
    public double calculate(Positions position, double bonus) {
        if (!position.isManager()) {
            throw new IllegalArgumentException("Квартальная премия доступна только для управленцев");
        }

        return bonus * 90 * position.getPositionCoefficient() * 10000 / 60;
    }
}
