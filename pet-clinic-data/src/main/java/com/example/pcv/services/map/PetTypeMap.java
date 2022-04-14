package com.example.pcv.services.map;

import com.example.pcv.model.PetType;
import com.example.pcv.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetTypeMap extends AbstractMapService<PetType, Long> implements PetTypeService {
}
