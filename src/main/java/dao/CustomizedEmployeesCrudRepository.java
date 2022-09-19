package dao;

import entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedEmployeesCrudRepository extends CrudRepository<Employee, Long> {
}
