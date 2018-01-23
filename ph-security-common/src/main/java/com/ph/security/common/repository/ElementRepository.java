package com.ph.security.common.repository;

import com.ph.security.common.entity.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementRepository extends JpaRepository<Element,Integer> {

    @Query("select e from Element e where e.crtUser = ?1")
    List<Element> selectAuthorityElementByUserId(@Param("userId") String userId);

    @Query("select e from Element e where e.crtUser = ?1")
    List<Element> selectAuthorityMenuElementByUserId(String userId, String menuId);

   /* @Query("select e from Element e where e.code = ?1")
    List<Element> selectAuthorityElementByClientId(@Param("code") String clientId);*/
}
