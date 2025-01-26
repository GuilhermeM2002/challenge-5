package br.com.zoo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class FeedDto {
    private List<String> animalName;

    public List<String> getAnimalName() {
        return animalName;
    }

    public void setAnimalName(List<String> animalName) {
        this.animalName = animalName;
    }
}
