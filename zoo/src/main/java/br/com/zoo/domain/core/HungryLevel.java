package br.com.zoo.domain.core;

import lombok.Getter;

@Getter
public enum HungryLevel {
    NOT_HUNGRY(0),
    SLIGHTLY_HUNGRY(2),
    HUNGRY(5),
    VERY_HUNGRY(7),
    STARVING(11);

    private final int foodRequired;

    HungryLevel(int foodRequired) {
        this.foodRequired = foodRequired;
    }

    public HungryLevel getNextLevel() {
        if (this == HungryLevel.NOT_HUNGRY) {
            return SLIGHTLY_HUNGRY;
        }
        return NOT_HUNGRY;
    }
}
