package com.example.pcv.bootstrap;

import com.example.pcv.model.Owner;
import com.example.pcv.model.Pet;
import com.example.pcv.model.PetType;
import com.example.pcv.model.Vet;
import com.example.pcv.services.OwnerService;
import com.example.pcv.services.PetTypeService;
import com.example.pcv.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DataInitialiser implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataInitialiser(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dogType = new PetType();
        dogType.setName("Dog");
        dogType = petTypeService.save( dogType );

        PetType catType = new PetType();
        catType.setName("Cat");
        catType = petTypeService.save( catType );

        Owner owner1 = new Owner();
        owner1.setFirstName("Rafael");
        owner1.setLastName("Nadal");
        owner1.setAddress("Calle Genova, 3");
        owner1.setCity("Madrid");
        owner1.setTelephone("1231231234");

        Pet owner1Pet = new Pet();
        owner1Pet.setPetType(dogType);
        owner1Pet.setOwner(owner1);
        owner1Pet.setName("Rusty");
        owner1Pet.setBirthDate(LocalDate.now());
        owner1.getPets().add(owner1Pet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Antonio");
        owner2.setLastName("Banderas");
        owner2.setAddress("Avenida de la Castellana, 157");
        owner2.setCity("Madrid");
        owner2.setTelephone("1231231234");

        Pet owner2Pet = new Pet();
        owner2Pet.setPetType(catType);
        owner2Pet.setOwner(owner2);
        owner2Pet.setName("Pussy");
        owner2Pet.setBirthDate(LocalDate.now());
        owner2.getPets().add(owner2Pet);

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Pedro");
        vet1.setLastName("Sanchez");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Santiago");
        vet2.setLastName("Carrillo");

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }

}
