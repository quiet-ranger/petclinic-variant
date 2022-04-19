package com.example.pcv.services.map;

import com.example.pcv.model.Speciality;
import com.example.pcv.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","map"})
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {
}
