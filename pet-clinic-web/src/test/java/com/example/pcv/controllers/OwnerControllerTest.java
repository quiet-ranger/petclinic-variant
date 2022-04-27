package com.example.pcv.controllers;

import com.example.pcv.model.Owner;
import com.example.pcv.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    MockMvc mockMvc;

    Set<Owner> owners;
    Owner owner1;
    Owner owner2;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Zibidi");
        owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Abel");
        owners.add(owner1);
        owners.add(owner2);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(status().is(200)) // same as isOk()
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", hasSize(2)))
                ;
    }

    @Test
    void listOwnersByIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", hasSize(2)))
                ;
    }

    @Test
    void findOwnersFormRequest() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"))
                ;
        verifyNoInteractions(ownerService);
    }

    @Test
    void findByLastNameReturnsManyResults() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);
        mockMvc.perform(post("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attributeExists("ownersList"))
                .andExpect(model().attribute("ownersList", hasSize(2)))
                ;
    }

    @Test
    void findByLastNameReturnsOneResult() throws Exception {
        owners.remove(owner2);
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);
        mockMvc.perform(post("/owners/find"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                ;
    }


    @Test
    void findOwnerByFirstName() throws Exception {
        when(ownerService.findAllByFirstNameLike(anyString())).thenReturn(owners);
        mockMvc.perform(get("/owners/findByFirstName/Ant"))
                .andExpect(status().isOk())
//                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }


    @Test
    void displayOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))))
                ;
    }

}