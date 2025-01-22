package br.com.zoo.useCaseImpl;

import br.com.zoo.domain.useCase.FeedAnimalUseCase;
import br.com.zoo.dto.AnimalDto;
import br.com.zoo.repository.AnimalRepository;
import br.com.zoo.service.AnimalService;
import br.com.zoo.service.AnimalThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public class FeedAnimalUseCaseImpl implements FeedAnimalUseCase {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AnimalService animalService;

    @Override
    @Async
    public void feedAnimal(List<String> animalName) {
        int foodQuantity = 100;

        animalName.forEach((name) -> {
            AnimalThread animalThread = new AnimalThread(name, foodQuantity);
            Thread thread = new Thread(animalThread);
            thread.start();
        });
    }
}
