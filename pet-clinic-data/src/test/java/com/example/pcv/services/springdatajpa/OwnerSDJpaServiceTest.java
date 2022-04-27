package com.example.pcv.services.springdatajpa;

import com.example.pcv.model.Owner;
import com.example.pcv.repositories.OwnerRepository;
import com.example.pcv.repositories.PetRepository;
import com.example.pcv.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    private final String FIRST_NAME = "Joe";
    private final String LAST_NAME = "Bloggs";
    private final Long ID = 1L;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner owner;

    @BeforeEach
    void setUp() {
        // Ensure the DB always has one known owner
        owner = new Owner();
        owner.setId(ID);
        owner.setFirstName( FIRST_NAME );
        owner.setLastName( LAST_NAME );
        service.save(owner);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findByLastName() {
        HashSet<Owner> mockedResult = new HashSet<>();
        mockedResult.add(owner);
        when(ownerRepository.findAllByLastNameLike(any())).thenReturn(mockedResult);

        Collection<Owner> result = service.findAllByLastNameLike( LAST_NAME );

        assertEquals(1, result.size(), "Wrong number of matches");
        assertEquals( LAST_NAME, result.stream().iterator().next().getLastName(), "Last name does not match");
        verify( ownerRepository ).findAllByLastNameLike(any());
    }
}