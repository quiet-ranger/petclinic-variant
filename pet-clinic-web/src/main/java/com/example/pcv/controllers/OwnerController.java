package com.example.pcv.controllers;

import com.example.pcv.model.Owner;
import com.example.pcv.model.OwnerSorterByFirstName;
import com.example.pcv.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;

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
        Collections.sort(allOwners, new OwnerSorterByFirstName());
        model.addAttribute("owners", allOwners);
        return "owners/index";

    }

}
