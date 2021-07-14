package com.miu.se.user;

import com.miu.se.common.entity.Role;
import com.miu.se.common.entity.UserRole;
import com.miu.se.common.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, UserRoleId> {
    public UserRole findOneByRole(Role role);
}
