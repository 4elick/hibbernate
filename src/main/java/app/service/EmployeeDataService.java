package app.service;

import app.dao.EmployeesCrudRepository;
import app.dao.RolesCrudRepository;
import app.dto.EmployeeDTO;
import app.dto.FilterDTO;
import app.entity.Employee;
import app.entity.Role;
import app.mapping.EmployeeMapper;
import liquibase.pro.packaged.E;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EmployeeDataService {
    private final EmployeeMapper employeeMapper;
    private final EmployeesCrudRepository employeesCrudRepository;
    private final RolesCrudRepository rolesCrudRepository;

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
    public void save(EmployeeDTO employeeDTO) {

        Employee employee = employeeMapper.convertFromDTO(employeeDTO);

        if (rolesCrudRepository.existsById(employee.getRole().getId())) {
            Role role = rolesCrudRepository.findById(employee.getRole().getId()).get();
            employee.setRole(role);
            role.getEmployees().add(employee);
        }else{
            Role role = employee.getRole();
            role.getEmployees().add(employee);
            employee.setRole(role);
        }
        employeesCrudRepository.save(employee);


    }

    @Transactional
    public void updateEmployee(long id, EmployeeDTO employeeDTO) {
        try {
            Employee employee1 = employeesCrudRepository.findById(id).orElseThrow(NullPointerException::new);
            Employee employee = employeeMapper.convertFromDTO(employeeDTO);
            employee1.setName(employee.getName());
            employee1.setFatherName(employee.getFatherName());
            employee1.setSecondName(employee.getSecondName());

            if (rolesCrudRepository.existsById(employee.getRole().getId())) {
                Role role = rolesCrudRepository.findById(employee.getRole().getId()).get();
                employee1.setRole(role);
                role.getEmployees().add(employee1);
            } else {
                employee1.setRole(employee.getRole());
            }
            employeesCrudRepository.save(employee1);

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public EmployeeDTO findById(long id) {
        Employee employee = employeesCrudRepository.findById(id).orElseThrow(NullPointerException::new);
        return employeeMapper.convertToDTO(employee);
    }

    @Transactional
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeesCrudRepository.findAll();
        return employees.stream().map(employeeMapper::convertToDTO).collect(toList());

    }

    @Transactional
    public void delete(long id) {
        try {
            employeesCrudRepository.deleteById(id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteAll() {
        try {
            employeesCrudRepository.deleteAll();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<EmployeeDTO> findAllOrByFilter(FilterDTO filterDTO) throws ParseException {
        Specification<Employee> specification = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(filterDTO.getBirthdateLessThan() != null && !"".equals(filterDTO.getBirthdateLessThan())){
                    predicates.add(criteriaBuilder.lessThan(root.get("birthdate"),filterDTO.getBirthdateLessThan()));
                }
                if(filterDTO.getBirthdateMoreThan() != null && !"".equals(filterDTO.getBirthdateMoreThan())){
                    predicates.add(criteriaBuilder.greaterThan(root.get("birthdate"),filterDTO.getBirthdateMoreThan()));
                }
                if(filterDTO.getStatus() != null && !"".equals(filterDTO.getStatus())){
                    predicates.add(criteriaBuilder.equal(root.get("status"),filterDTO.getStatus()));
                }
                if(filterDTO.getPersonalNumber() != null && !"".equals(filterDTO.getBirthdateLessThan())){

                    predicates.add(criteriaBuilder.like(root.get("personalNumber"),"%"+ filterDTO.getPersonalNumber()+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<Employee> employees = employeesCrudRepository.findAll(specification);
        return employees.stream().map(employeeMapper::convertToDTO).collect(toList());
    }

}
