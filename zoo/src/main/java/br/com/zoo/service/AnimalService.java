package br.com.zoo.service;

import br.com.zoo.domain.core.Animal;
import br.com.zoo.application.dto.AnimalDto;
import br.com.zoo.repository.AnimalRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ModelMapper mapper;

    @Transactional
    public AnimalDto newAnimal(AnimalDto dto){
        Animal animal = mapper.map(dto, Animal.class);
        animalRepository.save(animal);
        return mapper.map(animal, AnimalDto.class);
    }

    public AnimalDto findAnimalByName(String name){
        Animal animal = animalRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found: " + name));
        return mapper.map(animal, AnimalDto.class);
    }

    public List<AnimalDto> findAllAnimals() {
        return animalRepository.findAll().stream()
                .map(p -> mapper.map(p, AnimalDto.class))
                .collect(Collectors.toList());
    }
}
