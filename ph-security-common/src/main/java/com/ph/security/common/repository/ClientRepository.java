package com.ph.security.common.repository;

import com.ph.security.common.entity.AuthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<AuthClient,Integer> {

  //  void updateById(@Param("gateClient") GateClient gateClient);

    @Query("update AuthClient c set c.secret = ?1 where c.id = ?2")
    void updateById(String secret, Integer id);
}
