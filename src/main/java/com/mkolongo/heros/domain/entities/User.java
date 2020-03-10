package com.mkolongo.heros.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hero_id", referencedColumnName = "id")
    private Hero hero;

}
