package app.service;

import app.dao.CustomizedEmployeesCrudRepository;
import app.dao.CustomizedRolesCrudRepository;
import app.entity.Employee;
import app.entity.Role;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeDataService{

    private final CustomizedEmployeesCrudRepository employeesCrudRepository;
    private final CustomizedRolesCrudRepository rolesCrudRepository;
    /*
    public List<Employee> findByRole(Role role){
        try {
           List<Employee> employees = employeesCrudRepository.findAllByRole(role);
           return employees;
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }
    */
    @Transactional
    public void save(Employee employee){
        try {
            Role role = rolesCrudRepository.findById(employee.getRole().getId()).get();
            employee.setRole(role);
            employeesCrudRepository.save(employee);

        } catch (HibernateException e){
            e.printStackTrace();

        }
    }
    @Transactional
    public void updateEmployee(long id,Employee employee){
        try {
            if(!employeesCrudRepository.existsById(id)){
                throw new ChangeSetPersister.NotFoundException();
            }
            Employee employee1 = employeesCrudRepository.findById(id).get();
            employee1.setName(employee.getName());
            employee1.setFatherName(employee.getFatherName());
            employee1.setSecondName(employee.getSecondName());
            if(rolesCrudRepository.existsById(employee.getRole().getId())) {
                Role role = rolesCrudRepository.findById(employee.getRole().getId()).get();
                employee.setRole(role);
                role.getEmployees().add(employee1);
            } else {

                employee1.setRole(employee.getRole());
            }
            employeesCrudRepository.save(employee1);

        }catch (HibernateException e){
            e.printStackTrace();
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
    }
    @Transactional
    public Employee findById(long id){
        try {
            Employee employee = employeesCrudRepository.findById(id).get();
            return employee;
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
