package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository  //после Spring 6 или SpringBoot 3.0 и выше - можно не ставить эту аннотацию
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
