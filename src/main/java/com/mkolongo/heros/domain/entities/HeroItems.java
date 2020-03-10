package com.mkolongo.heros.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "hero_items")
public class HeroItems extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "hero_id", referencedColumnName = "id")
    private Hero hero;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
}
