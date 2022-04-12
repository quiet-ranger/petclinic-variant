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

    /**
     * This version is not really through Spring configuration nor
     * leverages profiles. It is tied in to a specific implementation
     * strategy based on maps.  Also, we have leakabe of entity
     * ids into this class. This will all be refactored later
     */
    public DataInitialiser() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Rafael");
        owner1.setLastName("Nadal");
        owner1.setAddress("Calle Genova, 3");
        owner1.setCity("Madrid");
        owner1.setTelephone("1231231234");

        ownerService.save(1L, owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231234");

        ownerService.save(2L, owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(1L,vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(2L, vet2);

        System.out.println("Loaded vets...");
    }

}
