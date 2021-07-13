package com.miu.se.user;

import com.miu.se.common.entity.UserRole;
import com.miu.se.common.entity.UserRoleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public UserRole save(UserRole userRole) {
        return roleRepository.save(userRole);
    }

    public List<UserRole> getAll(){
        return roleRepository.findAll();
    }

    public void delete(UserRoleId id) {
        roleRepository.deleteById(id);
    }

    public UserRole createIfNotExist(UserRole userRole) {
        UserRole local = roleRepository.findOneByRole(userRole.getRole());
        if(local == null) {
            local = roleRepository.save(userRole);
        }
        return local;
    };
}
