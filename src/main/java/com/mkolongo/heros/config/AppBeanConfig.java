package com.mkolongo.heros.config;

import com.mkolongo.heros.domain.entities.Hero;
import com.mkolongo.heros.domain.models.view.HeroViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppBeanConfig {

    @Bean
    public ModelMapper modelMapper() {
//        ModelMapper mapper = new ModelMapper();
//        mapper.createTypeMap(Hero.class, HeroViewModel.class)
//                .addMappings(m -> m.map(source -> source.getGender().name(),
//                        (destination, value) -> destination.setGender(((String) value))));
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
