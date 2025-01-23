package br.com.zoo.domain.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "animal_name", joinColumns = @JoinColumn(name = "animal_id"))
    @Column(name = "name")
    private List<String> name;

    @Enumerated(EnumType.STRING)
    private HungryLevel hungryLevel;
}
