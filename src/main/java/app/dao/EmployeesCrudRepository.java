package app.dao;

import app.entity.Employee;
import app.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface EmployeesCrudRepository extends JpaRepository<Employee, Long>{

    @Query(value = "SELECT e FROM Employee e" +
            "  WHERE ( CAST(:birthdateLessThan AS date) is null or" +
            "  e.birthdate < :birthdateLessThan) " +
            " AND (:status is null or :status = e.status)")
    public List<Employee> findAllByFilter(@Param("status")Status status, /*@Param("birthdateMoreThan") Date birthdateMoreThan,*/ @Param("birthdateLessThan") Date birthdateLessThan);

}
