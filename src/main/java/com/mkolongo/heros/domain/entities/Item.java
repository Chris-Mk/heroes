package com.mkolongo.heros.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Slot slot;

    @Column
    private int stamina;

    @Column
    private int strength;

    @Column
    private int attack;

    @Column
    private int defence;
}
