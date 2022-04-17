package com.example.pcv.repositories;

import com.example.pcv.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepositiry extends CrudRepository<Pet, Long> {
}
