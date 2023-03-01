package ru.stazaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stazaev.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
//    @Query("select u from User u where u.secret_key = :key")
//    User findBySecretKey(@Param("key") String key);
    @Query("select u from User u where u.secret_key = :key")
    Optional<User> findBySecretKey(@Param("key") String key);

}
