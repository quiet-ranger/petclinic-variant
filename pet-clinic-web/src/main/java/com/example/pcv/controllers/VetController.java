package com.example.pcv.controllers;

import com.example.pcv.model.Vet;
import com.example.pcv.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"/vets.html", "/vets/index", "/vets/index.html", "/vets/index.htm"})
    public String listVets(Model model) {

        ArrayList<Vet> allVets = new ArrayList<>(vetService.findAll());
        Collections.sort(allVets);
        model.addAttribute("vets", allVets );
        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson() {
        return vetService.findAll();
    }
}
