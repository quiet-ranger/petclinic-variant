package com.example.pcv.services;

import com.example.pcv.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Set<Owner> findAllByLastNameLike(String lastName);

    Set<Owner> findAllByFirstNameLike(String firstName);

}
