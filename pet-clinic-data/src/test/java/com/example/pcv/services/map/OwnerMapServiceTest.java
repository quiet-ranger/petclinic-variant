package com.example.pcv.services.map;

import com.example.pcv.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

    private final String FIRST_NAME = "Joe";
    private final String LAST_NAME = "Bloggs";
    private final Long ID = 1L;

    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
//        ownerMapService.save(Owner.builder().id(1L).build());
        Owner owner = new Owner();
        owner.setId(ID);
        owner.setFirstName( FIRST_NAME );
        owner.setLastName( LAST_NAME );
        ownerMapService.save(owner);
    }

    @Test
    void saveWithIdProvided() {
        Long newId = ID + 1L;
        Owner owner = new Owner();
        owner.setId(newId);
        Owner savedOwner = ownerMapService.save(owner);
        assertEquals(newId, savedOwner.getId(), "Ids do not match" );
    }

    @Test
    void saveWithoutId() {
        Owner owner = new Owner();
        Owner savedOwner = ownerMapService.save(owner);
        assertNotNull(savedOwner,"Entity was not persisted");
        assertNotNull(savedOwner.getId(), "Id cannot be null");
    }

    @Test
    void findByLastName() {
        Set<Owner> owners = ownerMapService.findAllByLastNameLike( LAST_NAME );
        assertEquals(1, owners.size(), "Wrong number of matches");
        assertEquals( FIRST_NAME, owners.iterator().next().getFirstName(), "Wrong person");
    }
}