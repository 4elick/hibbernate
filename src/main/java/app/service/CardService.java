package app.service;

import app.dao.CardAccountsCrudRepository;
import app.dao.CardsCrudRepository;
import app.entity.Card;
import app.entity.CardAccount;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardsCrudRepository cardsCrudRepository;
    private final CardAccountsCrudRepository cardAccountsCrudRepository;

    @Transactional
    public void save(Card card){
        try {
            CardAccount cardAccount = cardAccountsCrudRepository.findById(card.getCardAccount().getId()).get();
            cardAccount.getCards().add(card);
            card.setCardAccount(cardAccount);
            cardsCrudRepository.save(card);

        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void update(long id,Card card){
        try{
            if(!cardsCrudRepository.existsById(id)){
                throw new ChangeSetPersister.NotFoundException();
            }
            Card temp = cardsCrudRepository.findById(id).get();
            temp.setName(card.getName());
            temp.setSecondName(card.getSecondName());
            temp.setNumber(card.getNumber());
            temp.setLogicStatus(card.getLogicStatus());
            temp.setCardAccount(card.getCardAccount());
            card.getCardAccount().getCards().remove(card);
            temp.getCardAccount().getCards().add(temp);
        }catch (HibernateException e){
            e.printStackTrace();
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void delete(long id){
        try {
            if(!cardsCrudRepository.existsById(id)){
                throw new ChangeSetPersister.NotFoundException();
            }
            cardsCrudRepository.deleteById(id);
        }catch(HibernateException e){
            e.printStackTrace();
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public Card findById(long id){
        try {
            if(!cardsCrudRepository.existsById(id)){
                throw new ChangeSetPersister.NotFoundException();
            }
            Card card = cardsCrudRepository.findById(id).get();
            return card;
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
