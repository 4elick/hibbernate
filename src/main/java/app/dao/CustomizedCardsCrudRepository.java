package app.dao;

import app.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedCardsCrudRepository extends JpaRepository<Card, Long> {
   /* List<Card> getAllByCardAccount(CardAccount cardAccount);*/
}
