package com.example.pcv.bootstrap;

import com.example.pcv.model.Owner;
import com.example.pcv.model.Pet;
import com.example.pcv.model.PetType;
import com.example.pcv.model.Vet;
import com.example.pcv.services.OwnerService;
import com.example.pcv.services.VetService;
import com.example.pcv.services.map.OwnerServiceMap;
import com.example.pcv.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitialiser implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataInitialiser(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Rafael");
        owner1.setLastName("Nadal");
        owner1.setAddress("Calle Genova, 3");
        owner1.setCity("Madrid");
        owner1.setTelephone("1231231234");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Eduardo");
        owner2.setLastName("Alonso");
        owner2.setAddress("Avenida de la Castellana, 157");
        owner2.setCity("Madrid");
        owner2.setTelephone("1231231234");

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
