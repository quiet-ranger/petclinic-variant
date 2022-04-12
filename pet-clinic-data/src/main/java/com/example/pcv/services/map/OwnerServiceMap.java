package com.example.pcv.services.map;

import com.example.pcv.model.Owner;
import com.example.pcv.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {

        for (Map.Entry<Long, Owner> entry : map.entrySet()) {
            Owner owner = entry.getValue();
            if (owner.getLastName().equals(lastName)) {
                return owner;
            }
        }
        return null;
    }
}
