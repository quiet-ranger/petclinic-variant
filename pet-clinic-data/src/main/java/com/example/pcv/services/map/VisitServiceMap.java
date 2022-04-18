package com.example.pcv.services.map;

import com.example.pcv.model.Pet;
import com.example.pcv.model.Visit;
import com.example.pcv.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","map"})
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Visit save(Visit entity) {
        // Here we take the view that if the entities related to the visit
        // are not present and persisted, we throw a runtime exception
        Pet pet = entity.getPet();
        if (pet == null || pet.getOwner() == null ||
            pet.getId() == null || pet.getOwner().getId() == null) {
            throw new RuntimeException("Inconsistent entity graph");
        }
        return super.save(entity);
    }
}
