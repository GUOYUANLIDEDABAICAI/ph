package com.ph.security.common.repository;

import com.ph.security.common.entity.AuthClientService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientServiceRepostitory extends JpaRepository<AuthClientService,Integer> {
}
