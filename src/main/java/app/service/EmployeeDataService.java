package app.service;

import app.dao.EmployeeSpecification;
import app.dao.EmployeesCrudRepository;
import app.dao.RolesCrudRepository;
import app.dto.EmployeeDTO;
import app.dto.FilterDTO;
import app.entity.Employee;
import app.entity.Role;
import app.entity.Status;
import app.mapping.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    public Page<EmployeeDTO> findAllOrByFilter(FilterDTO filterDTO) throws ParseException {
        EmployeeSpecification specification = new EmployeeSpecification(filterDTO);
        Page<Employee> employeePage;
        if(filterDTO.getSortBy() != null && !"".equals(filterDTO.getPersonalNumber())){
            employeePage = employeesCrudRepository.findAll(specification, PageRequest.of(filterDTO.getPage(), filterDTO.getSize(),Sort.by(filterDTO.getSortBy())));
        } else  {
            employeePage = employeesCrudRepository.findAll(specification, PageRequest.of(filterDTO.getPage(), filterDTO.getSize()));
        }
        Page<EmployeeDTO> employeeDTOS = employeePage.map(employeeMapper::convertToDTO);
        return employeeDTOS;
    }

}
