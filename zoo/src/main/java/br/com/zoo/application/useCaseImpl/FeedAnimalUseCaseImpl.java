package br.com.zoo.application.useCaseImpl;

import br.com.zoo.domain.useCase.FeedAnimalUseCase;
import br.com.zoo.repository.AnimalRepository;
import br.com.zoo.service.AnimalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedAnimalUseCaseImpl implements FeedAnimalUseCase {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private ModelMapper mapper;

    @Override
    @Async
    public void feedAnimal(List<String> animalName) {
        int foodQuantity = 100;

        animalName.forEach((name) -> {
            AnimalThread animalThread = new AnimalThread(name, foodQuantity, animalService, animalRepository, mapper);
            Thread thread = new Thread(animalThread);
            thread.start();
        });
    }
}
