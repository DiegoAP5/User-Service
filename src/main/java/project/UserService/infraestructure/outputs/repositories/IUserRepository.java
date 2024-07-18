package project.UserService.infraestructure.outputs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.UserService.domain.models.UserModel;
import project.UserService.infraestructure.outputs.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT users.*, role.id AS role_id FROM users "+
                    "INNER JOIN roles ON users.role_id = roles.id "+
                    "WHERE email = :email", nativeQuery = true)
    UserModel getUserByEmail(String email);

}
