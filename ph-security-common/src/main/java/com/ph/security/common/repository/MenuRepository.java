package com.ph.security.common.repository;

import com.ph.security.common.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {
    @Query("select m from Menu m where m.id in ?1")
    List<Menu> findMenusByIds(List<String> ids);
}
