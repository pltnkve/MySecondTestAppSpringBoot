package ru.plotnikov.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.plotnikov.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuarterlyBonusServiceImplTest {

    @Test
    void calculate() {
        Positions position = Positions.HR;
        double bonus = 2.0;

        double result = new QuarterlyBonusServiceImpl().calculate(position, bonus);

        double expected = 36000;
        assertThat(result).isEqualTo(expected);
    }
}