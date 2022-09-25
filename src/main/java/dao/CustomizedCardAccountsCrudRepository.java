package dao;

import entity.CardAccount;
import entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedCardAccountsCrudRepository extends CrudRepository<CardAccount, Long> {

}
