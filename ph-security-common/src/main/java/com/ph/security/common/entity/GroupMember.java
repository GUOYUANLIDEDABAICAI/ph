package com.ph.security.common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "base_group_member")
@Entity
public class GroupMember {

    @Id
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "group_id")
    private String groupId;
}
