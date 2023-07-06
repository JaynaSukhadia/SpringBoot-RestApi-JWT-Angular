package com.aurosoft.restapi.dao;

import com.aurosoft.restapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName (String name);

}
