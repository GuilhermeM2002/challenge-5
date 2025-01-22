package br.com.zoo.controller;

import br.com.zoo.dto.AnimalDto;
import br.com.zoo.dto.FeedDto;
import br.com.zoo.service.AnimalService;
import br.com.zoo.service.AnimalThread;
import br.com.zoo.useCaseImpl.FeedAnimalUseCaseImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    @Autowired
    private AnimalService animalService;
    @Autowired
    private FeedAnimalUseCaseImpl feedAnimalUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity<AnimalDto> saveAnimal(UriComponentsBuilder builder, @RequestBody AnimalDto dto){
        var uri = builder.path("/{id}").buildAndExpand(dto.getId()).toUri();
        AnimalDto animal = animalService.newAnimal(dto);
        return ResponseEntity.created(uri).body(animal);
    }

    @PostMapping
    public ResponseEntity feedAnimal(@RequestBody FeedDto dto){
        feedAnimalUseCase.feedAnimal(dto.getAnimalName());
        return ResponseEntity.ok().build();
    }
}
