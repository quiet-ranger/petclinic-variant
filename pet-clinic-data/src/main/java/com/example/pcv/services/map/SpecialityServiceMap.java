package com.example.pcv.services.map;

import com.example.pcv.model.Speciality;
import com.example.pcv.services.SpecialityService;
import org.springframework.stereotype.Service;

@Service
public class SpecialityServiceMap extends AbstractMapService<Speciality, Long> implements SpecialityService {
}
