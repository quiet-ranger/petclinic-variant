package com.example.pcv.services.map;

import com.example.pcv.model.Speciality;
import com.example.pcv.model.Vet;
import com.example.pcv.services.SpecialityService;
import com.example.pcv.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet save(Vet vet) {

        Set<Speciality> specialities = vet.getSpecialities();
        specialities.forEach(
                speciality -> { specialityService.save(speciality); }
        );
        return super.save(vet);
    }
}
