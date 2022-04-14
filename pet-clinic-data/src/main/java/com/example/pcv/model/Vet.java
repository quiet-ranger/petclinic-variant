package com.example.pcv.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person implements Comparable<Vet> {

    private Set<Speciality> specialities = new HashSet<>();

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }

    @Override
    public int compareTo(Vet vet) {
        return this.getId().compareTo(vet.getId());
    }
}
