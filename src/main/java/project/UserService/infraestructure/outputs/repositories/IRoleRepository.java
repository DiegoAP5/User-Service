package project.UserService.infraestructure.outputs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.UserService.infraestructure.outputs.entities.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT roles.* FROM roles " +
            "WHERE id = :id", nativeQuery = true)
    Role findRoleById(Long id);
}
