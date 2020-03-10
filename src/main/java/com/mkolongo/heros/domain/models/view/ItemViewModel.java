package com.mkolongo.heros.domain.models.view;

import com.mkolongo.heros.domain.entities.Slot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemViewModel {

    private String id;
    private String name;
    private Slot slot;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;

}
