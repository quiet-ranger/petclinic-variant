package com.example.pcv.model;

public class Vet extends Person implements Comparable<Vet> {
    @Override
    public int compareTo(Vet vet) {
        return this.getId().compareTo(vet.getId());
    }
}
