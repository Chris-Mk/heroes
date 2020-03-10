package com.mkolongo.heros.repository;

import com.mkolongo.heros.domain.entities.Hero;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroRepository extends BaseRepository<Hero> {

    Optional<Hero> findByUser_Username(String user_username);

}
