package com.example.pcv.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "vets")
public class Vet extends Person implements Comparable<Vet> {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities",
               joinColumns = @JoinColumn(name = "vet_id"),
               inverseJoinColumns = @JoinColumn(name = "speciality_id") )
    private Set<Speciality> specialities = new HashSet<>();

    @Override
    public int compareTo(Vet vet) {
        return this.getId().compareTo(vet.getId());
    }
}
