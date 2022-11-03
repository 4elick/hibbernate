package app.service;

import app.dao.CardAccountsCrudRepository;
import app.dao.CardsCrudRepository;
import app.dto.CardDTO;
import app.entity.Card;
import app.entity.CardAccount;
import app.mapping.CardMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardsCrudRepository cardsCrudRepository;
    private final CardAccountsCrudRepository cardAccountsCrudRepository;
    private final CardMapper cardMapper;
    @Transactional
    public void save(CardDTO cardDTO){
            Card card = cardMapper.convertFromDTO(cardDTO);
            CardAccount cardAccount = cardAccountsCrudRepository.findById(card.getCardAccount().getId()).get();
            cardAccount.getCards().add(card);
            card.setCardAccount(cardAccount);
            cardsCrudRepository.save(card);

    }

    @Transactional
    public void update(long id,CardDTO cardDTO){

            Card card = cardMapper.convertFromDTO(cardDTO);
            Card temp = cardsCrudRepository.findById(id).get();
            temp.setName(card.getName());
            temp.setSecondName(card.getSecondName());
            temp.setNumber(card.getNumber());
            temp.setLogicStatus(card.getLogicStatus());
            temp.setCardAccount(card.getCardAccount());
            card.getCardAccount().getCards().remove(card);
            temp.getCardAccount().getCards().add(temp);

    }

    @Transactional
    public void delete(long id){
        if (!cardsCrudRepository.existsById(id)){
            throw new NullPointerException();
        }
        cardsCrudRepository.deleteById(id);
    }

    @Transactional
    public List<CardDTO> findAll(){
        return cardsCrudRepository.findAll().stream().map(cardMapper::convertToDTO).collect(Collectors.toList());
    }


    @Transactional
    public CardDTO findById(long id){

            Card card = cardsCrudRepository.findById(id).orElseThrow(NullPointerException::new);
            return cardMapper.convertToDTO(card);

    }
}
