package com.miu.se.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@IdClass(UserRoleId.class)
public class UserRole implements Serializable {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    public UserRole(Role role) {
        this.role = role;
    }
}
