package br.com.zoo.application.useCaseImpl;

import br.com.zoo.domain.core.Animal;
import br.com.zoo.domain.core.HungryLevel;
import br.com.zoo.application.dto.AnimalDto;
import br.com.zoo.repository.AnimalRepository;
import br.com.zoo.service.AnimalService;
import org.modelmapper.ModelMapper;

public class AnimalThread implements Runnable {
    private final AnimalService animalService;

    private final AnimalRepository animalRepository;

    private final ModelMapper mapper;

    private final String animalName;

    private volatile int foodQuantity;

    public AnimalThread(String animalName, int foodQuantity, AnimalService animalService, AnimalRepository animalRepository, ModelMapper mapper) {
        this.animalName = animalName;
        this.foodQuantity = foodQuantity;
        this.animalService = animalService;
        this.animalRepository = animalRepository;
        this.mapper = mapper;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                AnimalDto animal = animalService.findAnimalByName(animalName);
                if (animal == null) {
                    System.err.println("Animal not found: " + animalName);
                    break;
                }

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
                break;
            } catch (Exception e) {
                System.err.println("Error when processing the animal: " + e.getMessage());
            }
        }
    }
}
