package com.miu.se.user;

import com.miu.se.common.entity.UserRole;
import com.miu.se.common.entity.UserRoleId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Operation(security = @SecurityRequirement(name = "auth"))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserRole> getAll(){
        return roleService.getAll();
    }

    @Operation(security = @SecurityRequirement(name = "auth"))
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRole add(@RequestBody UserRole userRole) {
        return roleService.save(userRole);
    }

    @Operation(security = @SecurityRequirement(name = "auth"))
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UserRoleId id) {
        roleService.delete(id);
    }
}
