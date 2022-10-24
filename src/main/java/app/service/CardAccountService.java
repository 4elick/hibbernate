package app.service;

import app.dao.CardAccountsCrudRepository;
import app.dao.CardsCrudRepository;
import app.dao.EmployeesCrudRepository;
import app.dto.CardAccountDTO;
import app.entity.CardAccount;
import app.entity.Employee;
import app.mapping.CardAccountMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CardAccountService {
    private final CardAccountsCrudRepository cardAccountsCrudRepository;
    private final EmployeesCrudRepository employeesCrudRepository;
    private final CardAccountMapper cardAccountMapper;

    @Transactional
    public void save(CardAccountDTO cardAccountDTO) {
        CardAccount cardAccount = cardAccountMapper.convertFromDTO(cardAccountDTO);
        Employee employee = employeesCrudRepository.findById(cardAccount.getEmployee().getId()).get();
        employee.getCardAccounts().add(cardAccount);
        cardAccount.setEmployee(employee);
        cardAccountsCrudRepository.save(cardAccount);

    }

    @Transactional
    public CardAccountDTO findById(long id) {

        CardAccount cardAccount = cardAccountsCrudRepository.findById(id).orElseThrow(NullPointerException::new);
        CardAccountDTO cardAccountDTO = cardAccountMapper.convertToDTO(cardAccount);
        return cardAccountDTO;

    }

    @Transactional
    public void updateCardAccount(long id, CardAccountDTO cardAccountDTO) {

        CardAccount temp = cardAccountsCrudRepository.findById(id).orElseThrow(NullPointerException::new);
        CardAccount cardAccount = cardAccountMapper.convertFromDTO(cardAccountDTO);
        temp.setEmployee(cardAccount.getEmployee());
        cardAccount.getEmployee().getCardAccounts().remove(cardAccount);
        temp.getEmployee().getCardAccounts().add(temp);
        temp.setCards(cardAccount.getCards());
        temp.setAccountNumber(cardAccount.getAccountNumber());
        temp.setStatus(cardAccount.getStatus());
        temp.setValue(cardAccount.getValue());


    }

    @Transactional
    public void delete(long id) {
        try {
            if (!cardAccountsCrudRepository.existsById(id)) {
                throw new ChangeSetPersister.NotFoundException();
            }
            cardAccountsCrudRepository.deleteById(id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
    }


}
