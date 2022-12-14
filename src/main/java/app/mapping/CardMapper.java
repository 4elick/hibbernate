package app.mapping;

import app.configuration.LogicStatusProperties;
import app.dao.CardAccountsCrudRepository;
import app.dto.CardDTO;
import app.entity.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CardMapper {
    private final CardAccountMapper cardAccountMapper;
    private final CardAccountsCrudRepository cardAccountsCrudRepository;
    private final LogicStatusMapper logicStatusMapper;

    public CardDTO convertToDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setNumber(card.getNumber());
        cardDTO.setName(card.getName());
        cardDTO.setSecondName(card.getSecondName());
        cardDTO.setLogicStatus(logicStatusMapper.convertLogicStatus(card.getLogicStatus()));
        cardDTO.setCardAccountDTO(cardAccountMapper.convertToDTO(card.getCardAccount()));
        return cardDTO;
    }

    public Card convertFromDTO(CardDTO cardDTO){
        Card card = new Card();
        card.setId(cardDTO.getId());
        card.setNumber(cardDTO.getNumber());
        card.setLogicStatus(cardDTO.getLogicStatus());
        card.setSecondName(cardDTO.getSecondName());
        card.setName(cardDTO.getName());
        card.setCardAccount(cardAccountsCrudRepository.findById(cardDTO.getCardAccountDTO().getId()).get());
        return card;
    }

    /*public List<CardDTO> convertToDTOList(List<Card> cards){

    }*/
}
