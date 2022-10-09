package app.dao;

import app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedRolesCrudRepository extends JpaRepository<Role, Long> {
    /*Role findByEmployees(List<Employee> employees);*/
}
