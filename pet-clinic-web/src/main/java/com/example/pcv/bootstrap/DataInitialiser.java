package com.example.pcv.bootstrap;

import com.example.pcv.model.*;
import com.example.pcv.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class DataInitialiser implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataInitialiser(OwnerService ownerService,
                           VetService vetService,
                           PetTypeService petTypeService,
                           SpecialityService specialityService,
                           VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if ( count == 0 ) {
            loadData();
        }
    }

    private void loadData() {
        PetType dogType = new PetType();
        dogType.setName("Dog");
        dogType = petTypeService.save( dogType );

        PetType catType = new PetType();
        catType.setName("Cat");
        catType = petTypeService.save( catType );

        Owner rafael = new Owner();
        rafael.setFirstName("Rafael");
        rafael.setLastName("Nadal");
        rafael.setAddress("Calle Genova, 3");
        rafael.setCity("Madrid");
        rafael.setTelephone("1231231234");

        Pet dog = new Pet();
        dog.setPetType(dogType);
        dog.setOwner(rafael);
        dog.setName("Rusty");
        dog.setBirthDate(LocalDate.now());
        rafael.getPets().add(dog);

        ownerService.save(rafael);

        Owner antonio = new Owner();
        antonio.setFirstName("Antonio");
        antonio.setLastName("Banderas");
        antonio.setAddress("Avenida de la Castellana, 157");
        antonio.setCity("Madrid");
        antonio.setTelephone("1231231234");

        Pet cat = new Pet();
        cat.setPetType(catType);
        cat.setOwner(antonio);
        cat.setName("Pussy");
        cat.setBirthDate(LocalDate.now());
        antonio.getPets().add(cat);

        ownerService.save(antonio);

        Visit catVisit = new Visit();
        catVisit.setPet(cat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Does not eat");
        visitService.save(catVisit);

        log.info("Loaded owners...");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        radiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        surgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        dentistry = specialityService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Pedro");
        vet1.setLastName("Sanchez");
        vet1.getSpecialities().add(radiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Santiago");
        vet2.setLastName("Carrillo");
        vet2.getSpecialities().add(surgery);
        vet2.getSpecialities().add(dentistry);

        vetService.save(vet2);

        log.info("Loaded vets...");
    }

}
