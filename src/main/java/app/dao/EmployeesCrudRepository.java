package app.dao;

import app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesCrudRepository extends JpaRepository<Employee, Long> {
    /*List<Employee> findAllByRole(Role role);*/
}
