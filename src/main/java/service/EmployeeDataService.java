package service;

import dao.CustomizedEmployeesCrudRepository;
import entity.Employee;
import entity.Role;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDataService{
    @Autowired
    CustomizedEmployeesCrudRepository employeesCrudRepository;

    public Employee findByRole(Role role){
        try {
           Employee employee = employeesCrudRepository.findByRole(role);
           return employee;
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void save(Employee employee){
        try {
            employeesCrudRepository.save(employee);
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }
    @Transactional
    public Employee findById(long id){
        try {
            Optional<Employee> employee = employeesCrudRepository.findById(id);
            Employee result = employee.get();
            return result;
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public List<Employee> findAll(){
        List<Employee> employees = new ArrayList<>();
        try {
            Iterable<Employee> employeeIterable = employeesCrudRepository.findAll();
            employeeIterable.forEach(employees ::add);
        }catch (HibernateException e){
            e.printStackTrace();
        }
        if(!employees.isEmpty()){
            return employees;
        } else {
            System.out.println("Employee's list is empty");
            return null;
        }
    }
    @Transactional
    public void delete(Employee employee){
        try {
            employeesCrudRepository.delete(employee);
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteAll(){
        try {
            employeesCrudRepository.deleteAll();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

}
