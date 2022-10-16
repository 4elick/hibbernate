package app.service;

import app.dao.CardAccountsCrudRepository;
import app.dao.CardsCrudRepository;
import app.dao.EmployeesCrudRepository;
import app.entity.CardAccount;
import app.entity.Employee;
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

    @Transactional
    public void save(CardAccount cardAccount) {
        try {
            Employee employee = employeesCrudRepository.findById(cardAccount.getEmployee().getId()).get();
            employee.getCardAccounts().add(cardAccount);
            cardAccount.setEmployee(employee);
            cardAccountsCrudRepository.save(cardAccount);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public CardAccount findById(long id) {
        try {
            if (!cardAccountsCrudRepository.existsById(id)) {
                throw new ChangeSetPersister.NotFoundException();
            }
            CardAccount cardAccount = cardAccountsCrudRepository.findById(id).get();
            return cardAccount;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void updateCardAccount(long id, CardAccount cardAccount) {
        try {
            if (!cardAccountsCrudRepository.existsById(id)) {
                throw new ChangeSetPersister.NotFoundException();
            }
            CardAccount temp = cardAccountsCrudRepository.findById(id).get();
            temp.setEmployee(cardAccount.getEmployee());
            cardAccount.getEmployee().getCardAccounts().remove(cardAccount);
            temp.getEmployee().getCardAccounts().add(temp);
            temp.setCards(cardAccount.getCards());
            temp.setAccountNumber(cardAccount.getAccountNumber());
            temp.setStatus(cardAccount.getStatus());
            temp.setValue(cardAccount.getValue());

        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void delete(long id){
        try {
            if (!cardAccountsCrudRepository.existsById(id)) {
                throw new ChangeSetPersister.NotFoundException();
            }
            cardAccountsCrudRepository.deleteById(id);
        }catch (HibernateException e){
            e.printStackTrace();
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
    }


}
