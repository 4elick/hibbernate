package dao;

import entity.Employee;
import entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomizedRolesCrudRepository extends JpaRepository<Role, Long> {
    Role findByEmployees(List<Employee> employees);
}
