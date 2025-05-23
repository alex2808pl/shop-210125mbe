package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@Repository  //после Spring 6 или SpringBoot 3.0 и выше - можно не ставить эту аннотацию
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Чистый SQL
    @Query(nativeQuery = true, value="SELECT * FROM Users u WHERE u.Email=?1")
    UserEntity findByEmailNativeQuery(String email);

    // HQL
    @Query(nativeQuery = false, value="SELECT ue FROM UserEntity ue WHERE ue.name=?1")
    List<UserEntity> findByNameHql(String name);

}
