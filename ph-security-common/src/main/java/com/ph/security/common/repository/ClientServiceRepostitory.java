package com.ph.security.common.repository;

import com.ph.security.common.entity.GateClientService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientServiceRepostitory extends JpaRepository<GateClientService,Integer> {
}
