/*
package service;

import dao.CustomizedEmployeesCrudRepository;
import entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static entity.Status.ACTIVE;


public class Action {
    private static EntityManagerFactory factory;
    private static EntityManager session;
    private EmployeeDataService employeeDataService = new EmployeeDataService();
    public void start() {

        try {
            factory = Persistence.createEntityManagerFactory("postgres");
            session = factory.createEntityManager();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Action action = new Action();
        session.getTransaction().begin();

        action.addEmployee("Igor", "pulok", "sanich", "23456",
                new Date(), ACTIVE,new Role("RABOTNIK",new Date()),new ArrayList<CardAccount>());

        session.getTransaction().commit();
    }

    private void addEmployee(String name, String secondName, String fatherName, String personalNumber, Date birthDate, Status status, Role role, List<CardAccount> cardAccounts) {
        EntityManager session = factory.createEntityManager();
        try {
            Employee employee = new Employee();
            employee.setName(name);
            employee.setFatherName(fatherName);
            employee.setSecondName(secondName);
            employee.setPersonalNumber(personalNumber);
            employee.setBirthdate(birthDate);
            employee.setRole(role);
            employee.setStatus(status);
            employee.setCardAccounts(cardAccounts);
            employee.getRole().getEmployees().add(employee);
            employeeDataService.save(employee);
            */
/*session.persist(employee);
            session.getTransaction().begin();
            session.getTransaction().commit();*//*


        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    private void addCard(int number, String logicStatus, String name, String secondName, CardAccount cardAccount) {

        try {
            Card card = new Card();
            card.setNumber(number);
            card.setLogicStatus(logicStatus);
            card.setName(name);
            card.setSecondName(secondName);
            card.setCardAccount(cardAccount);
            if (!cardAccount.getCards().contains(card)) {
                cardAccount.getCards().add(card);
            }

            session.getTransaction().begin();
            session.persist(card);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    private void addCardAccount(String accountNumber, String value, Status status, Employee employee, List<Card> cards) {

        try {
            CardAccount cardAccount = new CardAccount();
            cardAccount.setAccountNumber(accountNumber);
            cardAccount.setValue(value);
            cardAccount.setStatus(status);
            cardAccount.setEmployee(employee);
            if (!employee.getCardAccounts().contains(cardAccount)) {
                employee.getCardAccounts().add(cardAccount);
            }
            cardAccount.setCards(cards);

            session.getTransaction().begin();
            session.persist(cardAccount);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
*/
