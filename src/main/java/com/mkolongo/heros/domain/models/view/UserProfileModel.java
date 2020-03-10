package com.mkolongo.heros.domain.models.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileModel {

    private String username;
    private String email;
    private String heroName;
    private String heroGender;
}
