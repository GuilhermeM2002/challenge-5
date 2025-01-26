package br.com.zoo.service;

import br.com.zoo.domain.core.Animal;
import br.com.zoo.domain.core.HungryLevel;
import br.com.zoo.application.dto.AnimalDto;
import br.com.zoo.repository.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AnimalThread implements Runnable {
    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ModelMapper mapper;

    private final String animalName;
    private int foodQuantity;

    public AnimalThread(String animalName, int foodQuantity){
        this.animalName = animalName;
        this.foodQuantity = foodQuantity;
    }

    @Override
    public void run() {
        try {
            AnimalDto animal = animalService.findAnimalByName(animalName);
            HungryLevel currentLevel = animal.getHungryLevel();

            if (currentLevel == HungryLevel.NOT_HUNGRY) {
                Thread.sleep(5000);
                animal.setHungryLevel(currentLevel.getNextLevel());
            } else {
                foodQuantity -= currentLevel.getFoodRequired();
                animal.setHungryLevel(currentLevel.getNextLevel());
            }

            animalRepository.save(mapper.map(animal, Animal.class));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error when processing the animal: " + e.getMessage());
        }
    }
}
