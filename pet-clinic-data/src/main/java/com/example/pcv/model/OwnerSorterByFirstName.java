package com.example.pcv.model;

import java.util.Comparator;

public class OwnerSorterByFirstName implements Comparator<Person> {
    @Override
    public int compare(Person person, Person t1) {
        return person.getFirstName().compareTo(t1.getFirstName());
    }
}
