package com.example.pcv.services.map;

import com.example.pcv.model.Vet;
import com.example.pcv.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
}
