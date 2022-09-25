package dao;

import entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedCardsCrudRepository extends CrudRepository<Card, Long> {
}
