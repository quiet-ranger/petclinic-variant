package com.example.pcv.controllers;

import com.example.pcv.model.Owner;
import com.example.pcv.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    MockMvc mockMvc;

    Set<Owner> owners;
    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        Owner o1 = new Owner();
        o1.setId(1L);
        o1.setFirstName("Zibidi");
        Owner o2 = new Owner();
        o2.setId(2L);
        o2.setFirstName("Abel");
        owners.add(o1);
        owners.add(o2);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(status().is(200)) // same as isOk()
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)))
                ;
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/pets"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"))
                ;
        verifyNoInteractions(ownerService);
    }
}