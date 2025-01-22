package br.com.zoo.dto;

import br.com.zoo.domain.core.HungryLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
    private Long id;
    private List<String> name;
    private HungryLevel hungryLevel;
}
