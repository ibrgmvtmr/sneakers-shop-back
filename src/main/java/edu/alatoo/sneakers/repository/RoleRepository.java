package edu.alatoo.sneakers.repository;

import edu.alatoo.sneakers.model.Role;
import edu.alatoo.sneakers.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(Roles name);

}
