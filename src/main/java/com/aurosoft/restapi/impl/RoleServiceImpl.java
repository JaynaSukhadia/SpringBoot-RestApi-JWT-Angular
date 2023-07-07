package com.aurosoft.restapi.impl;

import com.aurosoft.restapi.dao.RoleDao;
import com.aurosoft.restapi.entity.Role;
import com.aurosoft.restapi.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role createRole(String name) {
        return roleDao.save(new Role(name));
    }
}
