package com.mkolongo.heros.domain.models.binding;

import com.mkolongo.heros.domain.entities.Slot;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemCreateModel {

    @NotBlank(message = "Item Name Required!")
    private String name;

    @NotNull(message = "Item Slot Required!")
    private Slot slot;

    private int stamina;
    private int strength;
    private int attack;
    private int defence;

}
