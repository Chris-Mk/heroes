package com.mkolongo.heros.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column
    private int level;

    @Column
    private int stamina;

    @Column
    private int strength;

    @Column
    private int attack;

    @Column
    private int defence;

    @OneToMany(mappedBy = "hero", fetch = FetchType.EAGER)
    private List<HeroItems> heroItems;

    @OneToOne(mappedBy = "hero")
    private User user;
}
