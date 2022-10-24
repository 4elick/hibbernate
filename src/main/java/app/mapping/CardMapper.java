package app.mapping;

import app.dao.CardAccountsCrudRepository;
import app.dto.CardDTO;
import app.entity.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardMapper {
    private final CardAccountMapper cardAccountMapper;
    private final CardAccountsCrudRepository cardAccountsCrudRepository;
    public CardDTO convertToDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setLogicStatus(card.getLogicStatus());
        cardDTO.setNumber(card.getNumber());
        cardDTO.setName(card.getName());
        cardDTO.setSecondName(card.getSecondName());
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
}
