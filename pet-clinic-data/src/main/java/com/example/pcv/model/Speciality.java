package com.example.pcv.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "specialities")
public class Speciality extends BaseEntity {

    private String description;

}
