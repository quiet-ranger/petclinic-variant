package com.example.pcv.controllers;

import com.example.pcv.model.Owner;
import com.example.pcv.model.OwnerSorterByFirstName;
import com.example.pcv.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"", "/", "/index", "/index.html", "/index.htm"})
    public String listOwners(Model model) {
        Set<Owner> result = ownerService.findAll();
        Set<Owner> sortedResult = new TreeSet<>(new OwnerSorterByFirstName());
        sortedResult.addAll(result);
        List<Owner> allOwners = new ArrayList(sortedResult);
        Collections.sort(allOwners, new OwnerSorterByFirstName());
        model.addAttribute("owners", allOwners);
        return "owners/ownersList";

    }

    @GetMapping("/find")
    public String findOnwersFormRequest(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }

    @PostMapping("/find")
    public String findOwnersRequest(Owner owner, BindingResult result, Model model) {

        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        Set<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if ( results.isEmpty() ) {
            result.rejectValue("lastName", "notFound", "Not found");
            return "owners/findOwners";
        }
        else if ( results.size() == 1 ) {
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        }
        else {
            model.addAttribute("ownersList", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/findByFirstName/{firstName}")
    public String findOwnersByFirstName(@PathVariable String firstName, Model model) {
        Set<Owner> owners = ownerService.findAllByFirstNameLike(firstName);
        model.addAttribute("owners", owners);
        return "owners/ownersList";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

}
