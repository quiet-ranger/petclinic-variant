package com.example.pcv.controllers;

import com.example.pcv.model.Owner;
import com.example.pcv.model.Pet;
import com.example.pcv.model.PetType;
import com.example.pcv.services.OwnerService;
import com.example.pcv.services.PetService;
import com.example.pcv.services.PetTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    PetTypeService petTypeService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @InjectMocks
    PetController controller;

    MockMvc mockMvc;
    Owner owner1;
    Pet pet1;
    Set<PetType> petTypes = new HashSet<>();

    @BeforeEach
    void setUp() {
        owner1 = Owner.builder().id(1L).firstName("Javier").lastName("Barden").build();
        petTypes.add(PetType.builder().id(1L).name("Snake").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());
        petTypes.add(PetType.builder().id(3L).name("Dog").build());
        petTypes.add(PetType.builder().id(4L).name("Tortoise").build());
        petTypes.add(PetType.builder().id(5L).name("Gold Fish").build());
        pet1 = Pet.builder().id(1L).name("Bruno").owner(owner1).build();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void createPetFormRequest() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                ;
    }

    @Disabled
    void createPetFormUsingJSONBody() throws Exception {
        // For this test to pass, the handling method must:
        // + Include consumes = { MediaType.APPLICATION_JSON_VALUE } in the @PostMapping
        // + NOT have annotation @RequestBody
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);
        Pet casper = Pet.builder().name("Gaspar").owner(owner1).build();
        when(petService.save(any())).thenReturn(casper);

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(casper);
        mockMvc.perform(post("/owners/1/pets/new")
                        .characterEncoding("UTF-8")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(view().name("redirect:/owners/1"))
                ;
        verify(petService).save(any());
    }

    @Test
    void createPetFormUsingURLEncoding() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);
//        Pet casper = Pet.builder().name("Gaspar").birthDate(LocalDate.now()).build();
        Pet casper = Pet.builder().name("Gaspar").build();
        when(petService.save(any())).thenReturn(casper);

        mockMvc.perform(post("/owners/1/pets/new")
                        .characterEncoding("UTF-8")
                        .content(ObjectMapperUtils.convertToUrlEncoded(casper))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(view().name("redirect:/owners/1"))
        ;
//        assertEquals(owner1.getId(), casper.getOwner().getId(), "Owner was not set correctly");
        verify(petService).save(any());
    }

    @Test
    void editPetFormRequest() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(pet1);

        mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
        ;
    }

    @Test
    void editPetForm() throws Exception {
        pet1.setName("Fifi");
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.save(any())).thenReturn(pet1);

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(pet1);
        mockMvc.perform(post("/owners/1/pets/1/edit")
                        .characterEncoding("UTF-8")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(view().name("redirect:/owners/1"))
        ;
        verify(petService).save(any());
    }
}