package br.com.zoo.repository;

import br.com.zoo.domain.core.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    public Animal findAnimalByName(String name);
}
