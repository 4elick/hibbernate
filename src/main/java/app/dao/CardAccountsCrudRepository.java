package app.dao;

import app.entity.CardAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardAccountsCrudRepository extends JpaRepository<CardAccount, Long> {
 /*   List<CardAccount> getAllByEmployee(Employee employee);*/
}
