package app.mapping;

import app.dao.CardsCrudRepository;
import app.dto.CardAccountDTO;
import app.entity.Card;
import app.entity.CardAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CardAccountMapper {
    private final EmployeeMapper employeeMapper;
    private final CardsCrudRepository cardsCrudRepository;

    public CardAccountDTO convertToDTO(CardAccount cardAccount) {
        CardAccountDTO cardAccountDTO = new CardAccountDTO();
        cardAccountDTO.setId(cardAccount.getId());
        cardAccountDTO.setAccountNumber(cardAccount.getAccountNumber());
        cardAccountDTO.setValue(cardAccount.getValue());
        cardAccountDTO.setEmployee(employeeMapper.convertToDTO(cardAccount.getEmployee()));
        cardAccountDTO.setStatus(cardAccount.getStatus());
        cardAccountDTO.setCards(new ArrayList<>());
        if (!cardAccount.getCards().isEmpty()) {
            for (Card card : cardAccount.getCards()) {
                cardAccountDTO.getCards().add(card.getId());
            }
        }
        return cardAccountDTO;
    }

    public CardAccount convertFromDTO(CardAccountDTO cardAccountDTO) {
        CardAccount cardAccount = new CardAccount();
        cardAccount.setId(cardAccountDTO.getId());
        cardAccount.setAccountNumber(cardAccountDTO.getAccountNumber());
        cardAccount.setValue(cardAccountDTO.getValue());
        cardAccount.setEmployee(employeeMapper.convertFromDTO(cardAccountDTO.getEmployee()));
        cardAccount.setStatus(cardAccountDTO.getStatus());
        cardAccount.setCards(new ArrayList<>());
        if (!cardAccountDTO.getCards().isEmpty()) {
            for (long card_id : cardAccountDTO.getCards()) {
                cardAccount.getCards().add(cardsCrudRepository.findById(card_id).get());
            }
        }
        return cardAccount;
    }
}
