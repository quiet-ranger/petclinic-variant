package com.example.pcv.services.map;

import com.example.pcv.model.Pet;
import com.example.pcv.services.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
}
