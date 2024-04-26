package org.cloud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}