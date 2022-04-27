package com.example.pcv.repositories;

import com.example.pcv.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Set<Owner> findAllByLastNameLike(String lastName);

    Set<Owner> findByFirstName(String firstName);

    // This is an example of a named query where we take control of what is run against the database
    // @Query("SELECT DISTINCT owner FROM Owner owner left join fecth owner.pets WHERE owner.firstName LIKE :firstName%")
//    @Query("SELECT TELEPHONE FROM OWNERS WHERE TELEPHONE  LIKE ':phoneNumber%'")
//    @Transactional(readOnly = true)
//    Owner findOwnerByTelephone(@Param("phone") String phoneNumber);
}
