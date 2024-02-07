package com.nedashota.chatsnsapp.repositories;

import com.nedashota.chatsnsapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value="SELECT * FROM users WHERE mail_address = :mail_address LIMIT 1", nativeQuery=true)
    public User findByEmail(@Param("mail_address") String mailAddress);

    @Query(value="SELECT * FROM users WHERE mail_address = :mail_address AND password = :password LIMIT 1", nativeQuery=true)
    public User findByEmailAndPassword(@Param("mail_address") String mailAddress, @Param("password") String password);

    @Query(value="SELECT * FROM users WHERE id != :id", nativeQuery=true)
    public List<User> findAllExcept(@Param("id") Integer id);

    @Query(value="SELECT icon FROM users WHERE id = :id", nativeQuery=true)
    public byte[] findIconById(@Param("id") Integer id);
}
