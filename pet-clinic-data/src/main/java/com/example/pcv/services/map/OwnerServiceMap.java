package com.example.pcv.services.map;

import com.example.pcv.model.Owner;
import com.example.pcv.model.Pet;
import com.example.pcv.services.OwnerService;
import com.example.pcv.services.PetService;
import com.example.pcv.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Profile({"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner save(Owner owner) {
        // We must navigate the Entity structure to ensure all known
        // contained entities are persisted
        Set<Pet> ownedPets = owner.getPets();
        ownedPets.forEach(
            pet -> {
                petTypeService.save(pet.getPetType());
                petService.save(pet);
            }
        );
        return super.save(owner);
    }

    @Override
    public Owner findByLastName(String lastName) {

        for (Map.Entry<Long, Owner> entry : map.entrySet()) {
            Owner owner = entry.getValue();
            if (owner.getLastName().equals(lastName)) {
                return owner;
            }
        }
        return null;
    }
}
