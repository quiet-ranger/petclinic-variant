package com.example.pcv.controllers;

import com.example.pcv.model.Owner;
import com.example.pcv.model.OwnerSorterByFirstName;
import com.example.pcv.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html", "/index.htm", "/find"})
    public String listOwners(Model model) {

        ArrayList<Owner> allOwners = new ArrayList<>(ownerService.findAll());
        List.sort(allOwners, new OwnerSorterByFirstName());
        model.addAttribute("owners", allOwners);
        return "owners/index";

    }

    @RequestMapping("/pets")
    public String findOwners() {
        return "notimplemented";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

}
