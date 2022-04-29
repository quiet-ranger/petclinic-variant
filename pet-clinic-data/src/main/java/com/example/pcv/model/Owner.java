package com.example.pcv.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(Long id,
                 String firstName,
                 String lastName,
                 String address,
                 String city,
                 String telephone,
                 Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if (pets != null) {
            this.pets = pets;
        }
    }

    /**
     * Convenience method to retrieve an existing pet by name if it exists
     *
     * @param name
     * @return
     */
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase(Locale.ROOT);
        for (Pet pet : pets) {
            if ( !ignoreNew || !pet.isNew() ) {
                String existingName = pet.getName().toLowerCase(Locale.ROOT);
                if ( existingName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("Owner{firstName=%s, lastName=%s, address=%s, city=%s, telephone=%s, pets=%s",
                getFirstName(),
                getLastName(),
                getAddress(),
                getCity(),
                getTelephone(),
                getPets()
                        .stream()
                        .map( p -> p.getName() )
                        .collect(StringBuilder::new,
                                 StringBuilder::append,
                                 StringBuilder::append).toString()
        );
    }
}
