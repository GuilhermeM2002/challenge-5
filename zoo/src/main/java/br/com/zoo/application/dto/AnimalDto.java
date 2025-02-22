package br.com.zoo.application.dto;

import br.com.zoo.domain.core.HungryLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
    private Long id;
    private String name;
    private HungryLevel hungryLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HungryLevel getHungryLevel() {
        return hungryLevel;
    }

    public void setHungryLevel(HungryLevel hungryLevel) {
        this.hungryLevel = hungryLevel;
    }
}
