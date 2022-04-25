package com.example.pcv.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="types")
public class PetType extends BaseEntity {

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
