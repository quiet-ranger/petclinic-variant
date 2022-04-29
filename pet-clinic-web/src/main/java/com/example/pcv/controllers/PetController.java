package com.example.pcv.controllers;

import com.example.pcv.model.Owner;
import com.example.pcv.model.Pet;
import com.example.pcv.model.PetType;
import com.example.pcv.services.OwnerService;
import com.example.pcv.services.PetService;
import com.example.pcv.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;

@Controller
@Slf4j
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("petTypes")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    private boolean validPetName(Pet pet, Owner owner) {
        return pet.getName() != null &&
                pet.getName().length() > 0 &&  // There is a meaningful pet name with at least one character
                pet.isNew() &&  // This is not an existing pet
                owner.getPet(pet.getName(), true) == null // There isn't another pet with the same name
                ;
    }

    @GetMapping("/pets/new")
    public String createPetFormRequest(@PathVariable Long ownerId, Model model) {
        model.addAttribute("pet", Pet.builder().build());
        return "pets/createOrUpdatePetForm";
    }

//    @PostMapping(value = "/pets/new", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @PostMapping("/pets/new")
    public String createPetForm(Owner owner, @Valid /*@RequestBody*/ Pet pet, BindingResult result, Model model) {

        // Some validation to make sure a valid pet name that does not exist is being used
        if ( ! validPetName(pet, owner) ) {
            result.rejectValue("name", "duplicate", "That name already exists");
        }

        if (result.hasErrors()) {
            log.error("Validation failed: " + result.toString());
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        }
        else {
            pet.setOwner(owner);
            Pet savedPet = petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String editPetFormRequest(@PathVariable Long ownerId, @PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping(value = "/pets/{petId}/edit", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public String editPetForm(Owner owner, @Valid @RequestBody Pet pet, @PathVariable Long petId, @PathVariable Long ownerId, BindingResult result, Model model) {
        // Some validation to make sure a valid pet name that does not exist is being used
        if ( pet.getName() == null || pet.getName().length() == 0 ) {
            result.rejectValue("name", "duplicate", "That pet name is invalid");
        }

        if(result.hasErrors()) {
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        }
        else {
            owner.getPets().add(pet);
            pet.setId(petId); // is this needed?
            petService.save(pet);
            return "redirect:/owners/" + ownerId;
        }
    }
}
