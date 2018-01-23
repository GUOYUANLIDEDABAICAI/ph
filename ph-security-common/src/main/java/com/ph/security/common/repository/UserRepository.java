package com.ph.security.common.repository;

import com.ph.security.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where username = ?1")
    User findByUsername(String username);
    //  void updateById(@Param("gateClient") GateClient gateClient);
}
