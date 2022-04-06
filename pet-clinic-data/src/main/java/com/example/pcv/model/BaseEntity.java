package com.example.pcv.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    private Long id;  // Use boxed types as a Hibernate recommendation

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
