package com.mkolongo.heros.repository;

import com.mkolongo.heros.domain.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);

}
