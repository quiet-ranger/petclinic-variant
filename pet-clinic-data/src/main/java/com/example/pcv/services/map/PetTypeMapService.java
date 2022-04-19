package com.example.pcv.services.map;

import com.example.pcv.model.PetType;
import com.example.pcv.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {
}
