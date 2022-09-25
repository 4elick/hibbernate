package dao;

import entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedRolesCrudRepository extends CrudRepository<Role, Long> {
}
