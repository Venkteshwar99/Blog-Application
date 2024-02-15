package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer>{

}
