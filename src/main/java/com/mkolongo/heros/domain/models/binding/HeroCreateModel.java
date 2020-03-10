package com.mkolongo.heros.domain.models.binding;

import com.mkolongo.heros.domain.entities.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class HeroCreateModel {

    @NotBlank(message = "Name Required!")
    private String name;

    @NotNull(message = "Gender Required!")
    private Gender gender;
}
