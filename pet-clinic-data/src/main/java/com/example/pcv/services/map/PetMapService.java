package com.example.pcv.services.map;

import com.example.pcv.model.Pet;
import com.example.pcv.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
}
